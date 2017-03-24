package com.example.YourPc.myapplication.backend.pharmacy;

import com.example.YourPc.myapplication.backend.Location;
import com.example.YourPc.myapplication.backend.Review;
import com.example.YourPc.myapplication.backend.helpers.IdHelper;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Saeed on 3/14/2017.
 */
@Entity
public class Pharmacy {
    @Id
    public String pharmacyID;
   @Index
    public String name;
    public String password;
    public String email;
    public String phone;
    public String city;
    public List<Review> reviews = new ArrayList<>();
    public Location location;
    public List<PharmacyCategory> categories = new ArrayList<>();
    public String imageURL;
    public List<String> regTokenList = new ArrayList<>();
    @Index
    public int pricing;
    public int rating;
    public boolean active;

    public Pharmacy(String name, String password, String email,
                    String phone,String city,String imageURL) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.imageURL=imageURL;
        this.city = city;
        this.pharmacyID= IdHelper.generatePharmacyID(name,email);
    }

    public Pharmacy() {}

    public List<String> getRegTokenList() {
        return regTokenList;
    }
}
