package com.example.YourPc.myapplication.backend.pharmacy;

import com.example.YourPc.myapplication.backend.helpers.IdHelper;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by Muhammad Saeed on 3/14/2017.
 */
@Entity
public class Product {
    @Id
    public String productID;
    public String name;
    public double price;
    public String description;
    public boolean available = true;
    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.productID= IdHelper.generatePharmacyProductID(name);
    }

    public Product() {}
}
