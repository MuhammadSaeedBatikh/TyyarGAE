package com.example.YourPc.myapplication.backend.apiMethods;


import com.example.YourPc.myapplication.backend.helpers.Constants;
import com.example.YourPc.myapplication.backend.helpers.FireBaseHelper;
import com.example.YourPc.myapplication.backend.helpers.ProfileType;
import com.example.YourPc.myapplication.backend.pharmacy.Pharmacy;
import com.example.YourPc.myapplication.backend.profiles.Customer;
import com.example.YourPc.myapplication.backend.profiles.Driver;
import com.example.YourPc.myapplication.backend.restuarant.Restaurant;
import com.example.YourPc.myapplication.backend.restuarant.RestaurantView;
import com.example.YourPc.myapplication.backend.restuarant.order.DeliveryRequest;
import com.example.YourPc.myapplication.backend.restuarant.order.Order;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    PharmacyAPI pharmacyAPI = PharmacyAPI.getApiSinglton();
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

    @ApiMethod(name = "createOrder", path = "createOrder", httpMethod = ApiMethod.HttpMethod.GET)
    public Order createOrder(@Named("itemID") String itemID,
                             @Named("instructions") String instructions,
                             @Named("itemsNumber") int itemsNumaber,
                             @Named("choicesIDsArray") String[] choicesIDsArray) {
        List<String> choicesIDs = Arrays.asList(choicesIDsArray);
        Order order = new Order(itemID, instructions, choicesIDs, itemsNumaber);
        return order;
    }


    //Order[] raises and exception


    @ApiMethod(name = "getTheNearestDriver", path = "getTheNearestDriver", httpMethod = ApiMethod.HttpMethod.GET)
    public DeliveryRequest getTheNearestDriver(@Named("deliveryRequestID") String deliveryRequestID) {
        DeliveryRequest deliveryRequest = getDeliveryRequestByID(deliveryRequestID);
        String city = restaurantAPI.getRestaurantByID(deliveryRequest.merchantID).
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

    @ApiMethod(name = "driverRefusesOrder", path = "driverRefusesOrder", httpMethod = ApiMethod.HttpMethod.GET)
    public DeliveryRequest driverRefusesOrder(@Named("deliveryRequestID") String deliveryRequestID,
                                              @Named("driverID") String driverID) {
        DeliveryRequest deliveryRequest = getDeliveryRequestByID(deliveryRequestID);
        deliveryRequest.driversWhoRefusedIDs.add(driverID);
        ObjectifyService.ofy().save().entity(deliveryRequest).now();
        return deliveryRequest;
    }

    @ApiMethod(name = "getDeliveryRequestByID", path = "getDeliveryRequestByID", httpMethod = ApiMethod.HttpMethod.GET)
    public DeliveryRequest getDeliveryRequestByID(@Named("deliveryRequestID") String deliveryRequestID) {
        Key<DeliveryRequest> deliveryRequestKey = Key.create(DeliveryRequest.class, deliveryRequestID);
        return ObjectifyService.ofy().load().key(deliveryRequestKey).now();
    }

    @ApiMethod(name = "getDriverByID", path = "getDriverByID", httpMethod = ApiMethod.HttpMethod.GET)
    public Driver getDriverByID(@Named("driverID") String driverID) {
        Key<Driver> driverKey = Key.create(Driver.class, driverID);
        return ObjectifyService.ofy().load().key(driverKey).now();
    }

    @ApiMethod(name = "createDriver", path = "createDriver", httpMethod = ApiMethod.HttpMethod.GET)
    public Driver createDriver(@Named("name") String name, @Named("password") String password,
                               @Named("email") String email, @Named("city") String city,
                               @Named("phone") String phone, @Named("vehicle") String vehicle,
                               @Named("imageURL") String imageURL) {
        Driver driver = new Driver(name, password, email, city, phone, vehicle, imageURL);
        ObjectifyService.ofy().save().entity(driver).now();
        return driver;
    }

    @ApiMethod(name = "getDriverByName", path = "getDriverByName", httpMethod = ApiMethod.HttpMethod.GET)
    public Driver getDriverByName(@Named("name") String name) {
        Driver driver;
        Query<Driver> driverQuery = ObjectifyService.ofy().load().type(Driver.class);
        List<Driver> driverList = driverQuery.list();
        System.out.println(driverList);
        driver = driverList.get(0);
        return driver;
    }

    @ApiMethod(name = "updateDriverState", path = "updateDriverState", httpMethod = ApiMethod.HttpMethod.GET)
    public Driver updateDriverState(@Named("idle") boolean idle, @Named("driverID") String driverID) {
        Driver driver = getDriverByID(driverID);
        driver.idle = idle;
        ObjectifyService.ofy().save().entity(driver).now();
        return driver;
    }

    @ApiMethod(name = "confirmPickUp", path = "confirmPickUp", httpMethod = ApiMethod.HttpMethod.GET)
    public DeliveryRequest confirmPickUp(@Named("deliveryRequestID") String deliveryRequestID) {
        DeliveryRequest deliveryRequest = getDeliveryRequestByID(deliveryRequestID);
        deliveryRequest.confirmPickUP = true;
        updateDriverState(false, deliveryRequest.driverID);
        return deliveryRequest;
    }

    @ApiMethod(name = "sendNotificationByID", path = "sendNotificationByID", httpMethod = ApiMethod.HttpMethod.GET)
    public Object sendNotificationByID(@Named("profileID") String profileID,
                                       @Named("message") String message,
                                       @Named("profileType") ProfileType type) throws IOException {
        String regToken = getRegistrationToken(type, profileID);
        FireBaseHelper.sendNotification(regToken, message);
        return new Object();
    }

    //=====================
    //passing unsupported parameter !!
    @ApiMethod(name = "sendDeliveryRequest", path = "sendDeliveryRequest", httpMethod = ApiMethod.HttpMethod.GET)
    public Object sendDeliveryRequest(DeliveryRequest deliveryRequest) throws IOException {
        String regToken = getRegistrationToken(deliveryRequest.merchantType,
                deliveryRequest.merchantID);
        return sendNotificationByRegToken(regToken, "Order");
    }

    //=====================
    @ApiMethod(name = "sendNotificationByRegToken", path = "sendNotificationByRegToken", httpMethod = ApiMethod.HttpMethod.GET)
    public Object sendNotificationByRegToken(@Named("regToken") String regToken,
                                             @Named("message") String message) throws IOException {
        FireBaseHelper.sendNotification(regToken, message);
        return new Object();
    }

    @ApiMethod(name = "sendNotificationByRegToken2", path = "sendNotificationByRegToken2", httpMethod = ApiMethod.HttpMethod.GET)
    public RestaurantView sendNotificationByRegToken2(@Named("zregTok") String regTok,
                                                      @Named("amess") String mess) throws IOException {
        FireBaseHelper.sendNotification(regTok, mess);
        return new RestaurantView("sd", "jjj", "dksajk");
    }

    private String getRegistrationToken(ProfileType type, String profileID) {
        String regToken = null;
        switch (type) {
            case RESTAURANT:
                regToken = restaurantAPI.getRestaurantByID(profileID).getRegToken();
                break;
            case PHARMACY:
                regToken = pharmacyAPI.getPharmacyByID(profileID).getRegToke();
            case CUSTOMER:
                regToken = restaurantAPI.getCustomerByID(profileID).getRegToken();
        }
        return regToken;
    }

    @ApiMethod(name = "addRegTokenToProfile", path = "addRegTokenToProfile", httpMethod = ApiMethod.HttpMethod.GET)
    public void addRegTokenToProfile(@Named("profileType") ProfileType type,
                                     @Named("profileID") String profileID,
                                     @Named("regToken") String regToken) {

        switch (type) {
            case RESTAURANT:
                Restaurant restaurant = restaurantAPI.getRestaurantByID(profileID);
                restaurant.regToken = regToken;
                ObjectifyService.ofy().save().entity(restaurant).now();
                break;
            case PHARMACY:
                Pharmacy pharmacy = pharmacyAPI.getPharmacyByID(profileID);
                pharmacy.regToke = regToken;
                ObjectifyService.ofy().save().entity(pharmacy).now();

            case CUSTOMER:
                Customer customer = restaurantAPI.getCustomerByID(profileID);
                customer.regToken = regToken;
                ObjectifyService.ofy().save().entity(customer).now();
        }
    }
}