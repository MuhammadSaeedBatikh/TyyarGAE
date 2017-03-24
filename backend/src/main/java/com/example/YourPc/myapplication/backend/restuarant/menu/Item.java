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
public class Item {
    @Id
    public String id;
    public String name;
    //todo item description .. change UI and constructor
    public List<Option> options = new ArrayList<>();
    public Item() {
    } //default constructor for Entit initalization


    public Item(String name, List<Option> options) {
        this.name = name;
        this.options = options;
        this.id = IdHelper.generateItemKey(name);
    }
    public Item(String name) {
        this.name = name;
        this.id = IdHelper.generateItemKey(name);
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Option> getChoices() {
        return options;
    }
    public Item addOption(Option option){
        this.options.add(option);
        return this;
    }
}
