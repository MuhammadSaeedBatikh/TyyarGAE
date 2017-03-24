package com.example.YourPc.myapplication.backend.profiles;

import com.example.YourPc.myapplication.backend.Location;

/**
 * Created by Muhammad Saeed on 3/13/2017.
 */
public class DriverView {
    String driverID;
    public String name ;
    public Location currentLocation;
    public DriverView(String driverID, String name, Location currentLocation) {
        this.driverID = driverID;
        this.name = name;
        this.currentLocation = currentLocation;
    }

    public DriverView(Driver driver) {
        driverID = driver.driverID;
        name =driver.name;
        currentLocation = driver.currentLocation;
    }
}
