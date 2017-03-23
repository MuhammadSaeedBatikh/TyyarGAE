package com.example.YourPc.myapplication.backend.pharmacy;

/**
 * Created by Muhammad Saeed on 3/14/2017.
 */
public class PharmacyView {
    public String name;
    public String pharmacyID;
    public String imageURL;
    public String regToken;
    public PharmacyView(String name, String pharmacyID, String imageURL) {
        this.name = name;
        this.pharmacyID = pharmacyID;
        this.imageURL = imageURL;
    }

    public PharmacyView(Pharmacy pharmacy) {
        this.name =pharmacy.name;
        this.pharmacyID=pharmacy.pharmacyID;
        this.imageURL=pharmacy.imageURL;
    }

    public PharmacyView(String regToken) {
        this.regToken = regToken;
    }
}
