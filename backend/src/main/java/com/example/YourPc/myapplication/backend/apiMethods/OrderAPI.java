package com.example.YourPc.myapplication.backend.apiMethods;


import com.example.YourPc.myapplication.backend.helpers.Constants;
import com.example.YourPc.myapplication.backend.helpers.FireBaseHelper;
import com.example.YourPc.myapplication.backend.helpers.ProfileType;
import com.example.YourPc.myapplication.backend.pharmacy.Pharmacy;
import com.example.YourPc.myapplication.backend.profiles.Customer;
import com.example.YourPc.myapplication.backend.profiles.Driver;
import com.example.YourPc.myapplication.backend.restuarant.Restaurant;
import com.example.YourPc.myapplication.backend.restuarant.order.DeliveryRequest;
import com.example.YourPc.myapplication.backend.restuarant.order.Order;
import com.example.YourPc.myapplication.backend.returnWrappers.StringWrapper;
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

        List<Driver> driverList = driverQuery.list();
        List<String> driverIDsList= new ArrayList<>();
        //getting list of all active drivers IDs
        for (Driver driver : driverList) {
            driverIDsList.add(driver.driverID);
        }
        List<String> driversWhoRefuesdIDs= deliveryRequest.driversWhoRefusedIDs;
        //filtering out drivers who refused
        for (String id : driversWhoRefuesdIDs) {
            driverIDsList.remove(id);
        }
        //perform Checking here
        //use google Maps API
        try {
            String driverID = driverIDsList.get(0);
            updateDriverState(false,driverID);
            deliveryRequest.driverID = driverID;
            ObjectifyService.ofy().save().entity(deliveryRequest).now();
            sendNotificationByID(driverID,"pick up",ProfileType.DRIVER);
            return deliveryRequest;
        } catch (Exception e) {
            return null;
        }
    }

    @ApiMethod(name = "driverRefuseOrder", path = "driverRefuseOrder", httpMethod = ApiMethod.HttpMethod.GET)
    public DeliveryRequest driverRefuseOrder(@Named("deliveryRequestID") String deliveryRequestID,
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

    @ApiMethod(name = "driverConfirmPickUp", path = "driverConfirmPickUp", httpMethod = ApiMethod.HttpMethod.GET)
    public StringWrapper driverConfirmPickUp(@Named("deliveryRequestID") String deliveryRequestID) {
        DeliveryRequest deliveryRequest = getDeliveryRequestByID(deliveryRequestID);
        deliveryRequest.driverConfirmPickUP= true;
        updateDriverState(false, deliveryRequest.driverID);
        ObjectifyService.ofy().save().entity(deliveryRequest).now();//apply changes
        return new StringWrapper("ok");
    }
    @ApiMethod(name = "customerConfirmPickUp", path = "customerConfirmPickUp", httpMethod = ApiMethod.HttpMethod.GET)
    public StringWrapper customerConfirmPickUp(@Named("deliveryRequestID") String deliveryRequestID){
        DeliveryRequest deliveryRequest = getDeliveryRequestByID(deliveryRequestID);
        deliveryRequest.driverConfirmPickUP=true;
        updateDriverState(true,deliveryRequestID);
        ObjectifyService.ofy().save().entity(deliveryRequest).now();
        return new StringWrapper("ok");
    }
    //API method wrapper for sendNOtification method in firebase helper
    @ApiMethod(name = "sendNotificationByID", path = "sendNotificationByID", httpMethod = ApiMethod.HttpMethod.GET)
    public StringWrapper sendNotificationByID(@Named("profileID") String profileID,
                                       @Named("message") String message,
                                       @Named("profileType") ProfileType type){

        List<String> regTokenList = getRegistrationTokenList(type, profileID);
        for (String regToken : regTokenList) {
            try {
                sendNotificationByRegToken(regToken,message);
            } catch (IOException e) {
                return new StringWrapper(e.getMessage());
            }
        }
        return new StringWrapper("ok");
    }

    //passing unsupported parameter !!
    @ApiMethod(name = "sendDeliveryRequest", path = "sendDeliveryRequest", httpMethod = ApiMethod.HttpMethod.GET)
    public StringWrapper sendDeliveryRequest(DeliveryRequest deliveryRequest) {
        StringWrapper stringWrapper = new StringWrapper("ok");
        List<String> regTokenList = getRegistrationTokenList(deliveryRequest.merchantType,
                deliveryRequest.merchantID);
        for (String regToken : regTokenList) {
            try {
                sendNotificationByRegToken(regToken,"Order");
            } catch (IOException e) {
                return new StringWrapper(e.getMessage());
             }
        }
        return stringWrapper;
    }

    @ApiMethod(name = "sendNotificationByRegToken", path = "sendNotificationByRegToken", httpMethod = ApiMethod.HttpMethod.GET)
    public Object sendNotificationByRegToken(@Named("regToken") String regToken,
                                             @Named("message") String message) throws IOException {
        FireBaseHelper.sendNotification(regToken, message);
        return new Object();
    }
    private List<String> getRegistrationTokenList(ProfileType type, String profileID) {
        List<String> regTokenList = null;
        switch (type) {
            case RESTAURANT:
                regTokenList = restaurantAPI.getRestaurantByID(profileID).getRegTokenList();
                break;
            case PHARMACY:
                regTokenList = pharmacyAPI.getPharmacyByID(profileID).getRegTokenList();
                break;
            case CUSTOMER:
                regTokenList = restaurantAPI.getCustomerByID(profileID).getRegTokenList();
                break;
        }
        return regTokenList;
    }

    @ApiMethod(name = "addRegTokenToProfile", path = "addRegTokenToProfile", httpMethod = ApiMethod.HttpMethod.GET)
    public StringWrapper addRegTokenToProfile(@Named("profileType") ProfileType type,
                                              @Named("profileID") String profileID,
                                              @Named("regToken") String regToken) {
            StringWrapper stringWrapper = new StringWrapper("ok");
        switch (type) {
            case RESTAURANT:
                Restaurant restaurant = restaurantAPI.getRestaurantByID(profileID);
                restaurant.regTokenList.add(regToken);
                ObjectifyService.ofy().save().entity(restaurant).now();
                break;
            case PHARMACY:
                Pharmacy pharmacy = pharmacyAPI.getPharmacyByID(profileID);
                pharmacy.regTokenList.add(regToken);
                ObjectifyService.ofy().save().entity(pharmacy).now();
                break;
            case CUSTOMER:
                Customer customer = restaurantAPI.getCustomerByID(profileID);
                customer.regTokenList.add(regToken);
                ObjectifyService.ofy().save().entity(customer).now();
                break;
            case DRIVER:
                Driver driver = getDriverByID(profileID);
                driver.regTokenList.add(regToken);
                ObjectifyService.ofy().save().entity(driver).now();
                break;
        }
        return stringWrapper;
    }
    @ApiMethod(name = "removeRegTokenByID", path = "removeRegTokenByID", httpMethod = ApiMethod.HttpMethod.GET)
    public StringWrapper removeRegToken(@Named("profileType") ProfileType type,
                                        @Named("profileID") String profileID,
                                        @Named("regToken") String regToken){
            StringWrapper stringWrapper = new StringWrapper("ok");
            switch (type) {
                case RESTAURANT:
                    Restaurant restaurant = restaurantAPI.getRestaurantByID(profileID);
                    restaurant.regTokenList.remove(regToken);
                    ObjectifyService.ofy().save().entity(restaurant).now();
                    break;
                case PHARMACY:
                    Pharmacy pharmacy = pharmacyAPI.getPharmacyByID(profileID);
                    pharmacy.regTokenList.remove(regToken);
                    ObjectifyService.ofy().save().entity(pharmacy).now();
                    break;
                case CUSTOMER:
                    Customer customer = restaurantAPI.getCustomerByID(profileID);
                    customer.regTokenList.remove(regToken);
                    ObjectifyService.ofy().save().entity(customer).now();
                    break;
                case DRIVER:
                    Driver driver = getDriverByID(profileID);
                    driver.regTokenList.remove(regToken);
                    ObjectifyService.ofy().save().entity(driver).now();
                    break;
            }
            return stringWrapper;

    }
}