package com.example.YourPc.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by Muhammad Saeed on 2/11/2017.
 */
@Entity
public class Location {
    @Id String id;
    double longitude=0;
    double latitude=0;
    String city ="Nsr";
    String address="default";//specific description of the address

    public Location() {}//default constructor for Entit initalization


    public Location(double longitude, double latitude,String city, String address) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.city = city;
        this.id ="Location "+address+longitude+" "+latitude;
    }

    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }
}
