package com.example.YourPc.myapplication.backend.restuarant;


import com.example.YourPc.myapplication.backend.Location;
import com.example.YourPc.myapplication.backend.Review;
import com.example.YourPc.myapplication.backend.helpers.IdHelper;
import com.example.YourPc.myapplication.backend.restuarant.menu.Category;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Saeed on 2/11/2017.
 */
@Entity
public class Restaurant {
    @Id
    public String id;
    @Index
    public String name;
    public String password;
    public String email;
    public String phone;
    public List<Review> reviews = new ArrayList<>();
    public Location location;
    public List<Category> categories = new ArrayList<>();
    public String imageURL;
    public List<String>regTokenList = new ArrayList<>();
    @Index
    public int pricing;
    public int rating;
    public boolean active;

    public Restaurant() {
    }

    public Restaurant(String name, String password, String email, String phone,
                      Location location, List<Category> categories, int pricing) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.categories = categories;
        this.pricing = pricing;
        this.id = IdHelper.generateRestaurantKey(name, email);
    }

    public Restaurant(String name, String password, String email, String phone,
                      Location location, int pricing, String imageURL) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.pricing = pricing;
        this.id = IdHelper.generateRestaurantKey(name, email);
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Location getLocation() {
        return location;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public int getPricing() {
        return pricing;
    }

    public int getRating() {
        return rating;
    }

    public Restaurant addCategory(Category category) {
        this.categories.add(category);
        return this;
    }

    public List<String> getRegTokenList() {
        return regTokenList;
    }

}


