package com.example.YourPc.myapplication.backend.pharmacy;

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
public class PharmacyCategory {
    @Id
    public String id;
    @Index
    public String name;
    public String description;
    public List<Product> productList = new ArrayList<>();

    public PharmacyCategory(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = IdHelper.generatePharmacyCategoryID(name);
    }

    public PharmacyCategory() {}
}
