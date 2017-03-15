package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import com.example.YourPc.myapplication.backend.apiMethods.RestaurantAPI;
import com.example.YourPc.myapplication.backend.restuarant.menu.Category;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Muhammad Saeed on 2/25/2017.
 */
public class CategoryServlet extends HttpServlet {
    String restaurantId="";
    String categoryId="";
    RestaurantAPI restaurantAPI = RestaurantAPI.getApiSinglton();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getAttribute("restaurantId")!=null){
            restaurantId = request.getAttribute("restaurantId").toString();
            response.sendRedirect("Category.html");
        }
        else {
            String name = request.getParameter("name");
            Category category = restaurantAPI.createCategory(name);
            categoryId=category.id;
            System.out.println("restaurantId "+restaurantId);
            System.out.println("categoryId   "+categoryId);
            restaurantAPI.addCategoryToRestaurant(restaurantId,categoryId);
            request.setAttribute("categoryId",categoryId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("ItemServlet");
            requestDispatcher.forward(request,response);
        }
    }
}
