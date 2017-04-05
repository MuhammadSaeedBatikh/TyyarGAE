package com.example.YourPc.myapplication.backend.restuarant.menu;

import com.example.YourPc.myapplication.backend.restuarant.Restaurant;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Muhammad Saeed on 3/24/2017.
 */

public class MenuView {
    public HashMap<String,String> menu = new HashMap<>();
    public MenuView(Restaurant restaurant) {
       List<Category> categoryList= restaurant.getCategories();
        for (Category category : categoryList) {
         menu.put(category.getId(),category.getName());
        } 
    }
}
