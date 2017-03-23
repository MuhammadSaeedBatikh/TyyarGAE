package com.example.YourPc.myapplication.backend.profiles;

import com.example.YourPc.myapplication.backend.Location;
import com.example.YourPc.myapplication.backend.helpers.IdHelper;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by Muhammad Saeed on 2/11/2017.
 */
@Entity
public class Driver {
    @Id
    public String driverID;
    @Index
    public String name ;
    public String password;
    public String email;
    public String city;
    public String phone ;
    public String vehicle;
    public double balance;

    public int rate;

    @Index
    public Location currentLocation;//TODO check if indexing Locations allowed
    public String imageURL;
    @Index
    public boolean idle;
    public Driver() {
    }

    public Driver(String name, String password, String email,
                  String city, String phone, String vehicle,String imageURL) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.city = city;
        this.phone = phone;
        this.vehicle = vehicle;
        this.balance = 0.0;
        this.rate = -1;
        this.imageURL =imageURL;
        this.driverID = IdHelper.generateDriverKey(name,email);
    }

    public String getDriverID() {
        return driverID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getVehicle() {
        return vehicle;
    }

    public double getBalance() {
        return balance;
    }

    public int getRate() {
        return rate;
    }
}
