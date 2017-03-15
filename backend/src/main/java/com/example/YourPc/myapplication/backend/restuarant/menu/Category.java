package com.example.YourPc.myapplication.backend.restuarant.menu;

import com.example.YourPc.myapplication.backend.helpers.IdHelper;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Saeed on 2/11/2017.
 */
@Entity
public class Category {
    @Id
    public String id;
    public String name;
    public List<Item> items = new ArrayList<>();

    public Category(String name, List<Item> items) {
        this.name = name;
        this.items = items;
        this.id = IdHelper.generateRestaurantCategoryKey(name);
    }

    public Category(String name) {
        this.name = name;
        this.id = IdHelper.generateRestaurantCategoryKey(name);
    }

    public Category() {
    }//default constructor for Entit initalization

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }
}
