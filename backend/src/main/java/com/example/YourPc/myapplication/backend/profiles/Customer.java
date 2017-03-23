package com.example.YourPc.myapplication.backend.profiles;

import com.example.YourPc.myapplication.backend.Location;
import com.example.YourPc.myapplication.backend.Review;
import com.example.YourPc.myapplication.backend.helpers.IdHelper;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Saeed on 2/9/2017.
 */
@Entity
public class Customer {
    @Id
    public String id;
    @Index
    public String name;
    public String password;
    public String email;
    public String mainAdress;
    public String phone;
    public List<Review> reviews = new ArrayList<>();
    public List<Location> locations = new ArrayList<>();
    public String regToken;

    public Customer() {
        System.out.println("created by calling empty constructor");
        //empty constructor
    }//default constructor for Entit initalization


    public Customer(String name, String password, String email,
                    String mainAdress, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.mainAdress = mainAdress;
        this.phone = phone;
        this.id = IdHelper.generateCustomerKey(name, email);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMainAdress() {
        return mainAdress;
    }

    public String getPhone() {
        return phone;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public String getRegToken() {
        return regToken;
    }
}

