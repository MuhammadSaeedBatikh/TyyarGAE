package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import com.example.YourPc.myapplication.backend.apiMethods.RestaurantAPI;
import com.example.YourPc.myapplication.backend.restuarant.menu.Option;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Muhammad Saeed on 2/25/2017.
 */
public class OptionServlet extends HttpServlet {
    String optionID = "";
    String itemID = "";
    RestaurantAPI restaurantAPI = RestaurantAPI.getApiSinglton();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("itemID") != null) {
            itemID = request.getAttribute("itemID").toString();
            response.sendRedirect("Option.html");
        } else {
            String name = request.getParameter("name");
            boolean required = true;
            if (request.getParameter("required") == null) {
                required = false;
            }
            Option option = restaurantAPI.createOption(name, required);
            optionID = option.id;
            restaurantAPI.addOptionToItem(itemID, optionID);
            request.setAttribute("optionID", optionID);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("ChoiceServlet");
            requestDispatcher.forward(request, response);
        }
    }

}
