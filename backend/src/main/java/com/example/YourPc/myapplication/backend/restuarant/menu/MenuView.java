package com.example.YourPc.myapplication.backend.restuarant.menu;


import com.example.YourPc.myapplication.backend.restuarant.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Saeed on 3/24/2017.
 */

public class MenuView {
    List<MenuItem> menuItemList = new ArrayList<>();

    public MenuView(Restaurant restaurant) {
        List<Category> categoryList= restaurant.getCategories();
        for (Category category : categoryList) {
            menuItemList.add(new MenuItem(category.getId(),category.getName()));
        }
    }
    /*public HashMap<String,String> menu = new HashMap<>();
    public MenuView(Restaurant restaurant) {
       List<Category> categoryList= restaurant.getCategories();
        for (Category category : categoryList) {
         menu.put(category.getId(),category.getName());
        } 
    }*/
}
