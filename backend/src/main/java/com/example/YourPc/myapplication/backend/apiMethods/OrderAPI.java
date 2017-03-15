package com.example.YourPc.myapplication.backend.apiMethods;


import com.example.YourPc.myapplication.backend.helpers.Constants;
import com.example.YourPc.myapplication.backend.profiles.Driver;
import com.example.YourPc.myapplication.backend.restuarant.order.DeliveryRequest;
import com.example.YourPc.myapplication.backend.restuarant.order.ListOfOrders;
import com.example.YourPc.myapplication.backend.restuarant.order.Order;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

/**
 * Created by Muhammad Saeed on 3/3/2017.
 */
@Api(name = "orderAPI",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
public class OrderAPI {
    RestaurantAPI restaurantAPI = RestaurantAPI.getApiSinglton();
    private static OrderAPI orderAPIInstance;

    public OrderAPI() {
    }

    public static OrderAPI getApiSinglton() {
        if (orderAPIInstance == null) {
            orderAPIInstance = new OrderAPI();
            return orderAPIInstance;
        }
        return orderAPIInstance;
    }

    @ApiMethod(name = "createOrder", path = "createOrder", httpMethod = ApiMethod.HttpMethod.POST)
    public Order createOrder(@Named("itemID") String itemID,
                             @Named("instructions") String instructions,
                             @Named("itemsNumber") int itemsNumaber,
                             @Named("choicesIDsArray") String[] choicesIDsArray) {
        List<String> choicesIDs = Arrays.asList(choicesIDsArray);
        Order order = new Order(itemID, instructions, choicesIDs, itemsNumaber);
        return order;
    }


    //Order[] raises and exception
    @ApiMethod(name = "sendDeliveryRequest", path = "sendDeliveryRequest", httpMethod = ApiMethod.HttpMethod.POST)
    public DeliveryRequest sendDeliveryRequest(ListOfOrders listOfOrders,
                                               @Named("customerID") String customerID,
                                               @Named("customerLocationID") String customerLocationID,
                                               @Named("restaurantID") String restaurantID
    ) {
        DeliveryRequest deliveryRequest = new DeliveryRequest(listOfOrders, customerID, restaurantID,
                customerLocationID);
        System.out.println("list of orders " + listOfOrders);
        System.out.println("customer Id " + customerID);
        System.out.println("restaurant id" + restaurantID);
        return deliveryRequest;
    }

    @ApiMethod(name = "getTheNearestDriver", path = "getTheNearestDriver", httpMethod = ApiMethod.HttpMethod.POST)
    public DeliveryRequest getTheNearestDriver(@Named("deliveryRequestID") String deliveryRequestID) {
        DeliveryRequest deliveryRequest = getDeliveryRequestByID(deliveryRequestID);
        String city = RestaurantAPI.getApiSinglton().getRestaurantByID(deliveryRequest.restaurantID).
                location.getCity();
        Query<Driver> driverQuery = ObjectifyService.ofy().load().type(Driver.class).filter("city =", city)
                .filter("idle =", true);

        List<Driver> driverList = new ArrayList<>();
        for (Driver driver : driverList) {
            driverQuery.filter("driverID !=", driver.driverID);
        }
        //perform Checking here
        //use google Maps API
        try {
            Driver driver = driverList.get(0);
            deliveryRequest.driverID = driver.driverID;
            ObjectifyService.ofy().save().entity(deliveryRequest).now();
            return deliveryRequest;
        } catch (Exception e) {
            return null;
        }
    }

    @ApiMethod(name = "driverRefusesOrder", path = "driverRefusesOrder", httpMethod = ApiMethod.HttpMethod.POST)
    public DeliveryRequest driverRefusesOrder(@Named("deliveryRequestID") String deliveryRequestID,
                                              @Named("driverID") String driverID) {
        DeliveryRequest deliveryRequest = getDeliveryRequestByID(deliveryRequestID);
        deliveryRequest.driversWhoRefusedIDs.add(driverID);
        ObjectifyService.ofy().save().entity(deliveryRequest).now();
        return deliveryRequest;
    }

    @ApiMethod(name = "getDeliveryRequestByID", path = "getDeliveryRequestByID", httpMethod = ApiMethod.HttpMethod.POST)
    public DeliveryRequest getDeliveryRequestByID(@Named("deliveryRequestID") String deliveryRequestID) {
        Key<DeliveryRequest> deliveryRequestKey = Key.create(DeliveryRequest.class, deliveryRequestID);
        DeliveryRequest deliveryRequest = ObjectifyService.ofy().load().key(deliveryRequestKey).now();
        return deliveryRequest;
    }

    @ApiMethod(name = "getDriverByID", path = "getDriverByID", httpMethod = ApiMethod.HttpMethod.POST)
    public Driver getDriverByID(@Named("driverID") String driverID) {
        Key<Driver> driverKey = Key.create(Driver.class, driverID);
        Driver driver = ObjectifyService.ofy().load().key(driverKey).now();
        return driver;
    }

    @ApiMethod(name = "createDriver", path = "createDriver", httpMethod = ApiMethod.HttpMethod.POST)
    public Driver createDriver(@Named("name") String name, @Named("password") String password,
                               @Named("email") String email, @Named("city") String city,
                               @Named("phone") String phone, @Named("vehicle") String vehicle,
                               @Named("imageURL") String imageURL) {
        Driver driver = new Driver(name, password, email, city, phone, vehicle, imageURL);
        ObjectifyService.ofy().save().entity(driver).now();
        return driver;
    }

    @ApiMethod(name = "getDriverByName", path = "getDriverByName", httpMethod = ApiMethod.HttpMethod.POST)
    public Driver getDriverByName(@Named("name") String name) {
        Driver driver;
        Query<Driver> driverQuery = ObjectifyService.ofy().load().type(Driver.class);
        List<Driver> driverList = driverQuery.list();
        System.out.println(driverList);
        driver = driverList.get(0);
        return driver;
    }

    @ApiMethod(name = "updateDriverState", path = "updateDriverState", httpMethod = ApiMethod.HttpMethod.POST)
    public Driver updateDriverState(@Named("idle") boolean idle, @Named("driverID") String driverID) {
        Driver driver = getDriverByID(driverID);
        driver.idle = idle;
        ObjectifyService.ofy().save().entity(driver).now();
        return driver;
    }

    @ApiMethod(name = "confirmPickUp", path = "confirmPickUp", httpMethod = ApiMethod.HttpMethod.POST)
    public DeliveryRequest confirmPickUp(@Named("deliveryRequestID") String deliveryRequestID) {
        DeliveryRequest deliveryRequest = getDeliveryRequestByID(deliveryRequestID);
        deliveryRequest.confirmPickUP = true;
        updateDriverState(false, deliveryRequest.driverID);
        return deliveryRequest;
    }

}
