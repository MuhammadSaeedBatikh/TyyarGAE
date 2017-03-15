package com.example.YourPc.myapplication.backend.restuarant;

/**
 * Created by Muhammad Saeed on 3/10/2017.
 */
public class RestaurantView {
    public String name;
    public String restaurantID;
    public String imageURL;

    public RestaurantView(String name, String restaurantID, String imageURL) {
        this.name = name;
        this.restaurantID = restaurantID;
        this.imageURL = imageURL;
    }
    public RestaurantView (Restaurant restaurant){
        this.name = restaurant.name;
        this.restaurantID= restaurant.id;
        this.imageURL = restaurant.imageURL;
    }
}
