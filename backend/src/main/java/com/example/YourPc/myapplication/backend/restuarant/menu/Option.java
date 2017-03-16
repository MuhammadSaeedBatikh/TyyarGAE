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
public class Option {
    public
    @Id
    String id;
    public String name;//size , spicy etc
    public boolean required;
    public List<Choice> choices = new ArrayList<>();

    public Option() {
    }

    public Option(String name, boolean required, List<Choice> choices) {
        this.name = name;
        this.required = required;
        this.choices = choices;
        this.id = IdHelper.generateOptionKey(name);
    }

    public Option(String name, boolean required) {
        this.id = IdHelper.generateOptionKey(name);
        this.name = name;
        this.required = required;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isRequired() {
        return required;
    }

    public List<Choice> getChoices() {
        return choices;
    }
}
