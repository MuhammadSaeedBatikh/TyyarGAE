package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import com.example.YourPc.myapplication.backend.apiMethods.RestaurantAPI;
import com.example.YourPc.myapplication.backend.restuarant.menu.Choice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Muhammad Saeed on 2/25/2017.
 */
public class ChoiceServlet extends HttpServlet {
    String optionID = "";
    String choiceID = "";
    RestaurantAPI restaurantAPI = RestaurantAPI.getApiSinglton();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("optionID") != null) {
            optionID = request.getAttribute("optionID").toString();
            response.sendRedirect("Choice.html");
        }
        else {
            String name = request.getParameter("name");
            double price=Double.valueOf(request.getParameter("price"));
            String description= request.getParameter("description");
            String addToPriceInStringFormat=request.getParameter("addToPrice");
            System.out.println("addToPriceInStringFormat "+addToPriceInStringFormat);
            boolean addToPrice = true;
            if (request.getParameter("addToPrice")==null){
                addToPrice=false;
            }
            boolean avaliable=true;
            if (request.getParameter("avaliable")==null){
                avaliable =false;
            }
            Choice choice = restaurantAPI.createChoice(name,price,addToPrice,description,avaliable);
            choiceID =choice.id;
            restaurantAPI.addChoiceToOption(optionID,choiceID);
        }
    }


}
