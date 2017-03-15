package com.example.YourPc.myapplication.backend.restuarant.menu;

import com.example.YourPc.myapplication.backend.helpers.IdHelper;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by Muhammad Saeed on 2/11/2017.
 */
@Entity
public class Choice {
    @Id
    public String id;
    public String name;
    public double price;
    public boolean addToPrice;
    public String description; //Extras
    public boolean avaliable;

    public Choice() {
    }//default constructor for Entit initalization


    public Choice(String name, double price, boolean addToPrice,
                  String description, boolean avaliable) {
        this.name = name;
        this.price = price;
        this.addToPrice = addToPrice;
        this.description = description;
        this.avaliable = avaliable;
        this.id = IdHelper.generateChoiceKey(name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAddToPrice() {
        return addToPrice;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvaliable() {
        return avaliable;
    }
}
