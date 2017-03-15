package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import com.example.YourPc.myapplication.backend.apiMethods.RestaurantAPI;
import com.example.YourPc.myapplication.backend.restuarant.menu.Item;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Muhammad Saeed on 2/25/2017.
 */
public class ItemServlet extends HttpServlet {
    String itemID = "";
    String categoryId = "";
    RestaurantAPI restaurantAPI = RestaurantAPI.getApiSinglton();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getAttribute("categoryId") != null) {
            categoryId = req.getAttribute("categoryId").toString();
            resp.sendRedirect("Item.html");
        } else {
            String name = req.getParameter("name");
            Item item = restaurantAPI.createItem(name);
            itemID = item.id;
            restaurantAPI.addItemToCategory(categoryId, itemID);
            req.setAttribute("itemID", itemID);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("OptionServlet");
            requestDispatcher.forward(req, resp);
        }
    }
}
