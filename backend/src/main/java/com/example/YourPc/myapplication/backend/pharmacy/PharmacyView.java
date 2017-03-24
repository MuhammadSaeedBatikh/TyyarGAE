package com.example.YourPc.myapplication.backend.pharmacy;

/**
 * Created by Muhammad Saeed on 3/14/2017.
 */
public class PharmacyView {
    public String name;
    public String pharmacyID;
    public String imageURL;
    public int pricing;
    public int rating;
    public boolean active;
    public PharmacyView(String name, String pharmacyID, String imageURL) {
        this.name = name;
        this.pharmacyID = pharmacyID;
        this.imageURL = imageURL;
    }

    public PharmacyView(String name, String pharmacyID, String imageURL, int pricing, int rating, boolean active) {
        this.name = name;
        this.pharmacyID = pharmacyID;
        this.imageURL = imageURL;
        this.pricing = pricing;
        this.rating = rating;
        this.active = active;
    }

    public PharmacyView(Pharmacy pharmacy) {
        this.name =pharmacy.name;
        this.pharmacyID=pharmacy.pharmacyID;
        this.imageURL=pharmacy.imageURL;
        this.active=pharmacy.active;
        this.pricing=pharmacy.pricing;
        this.rating=pharmacy.rating;
    }


}
