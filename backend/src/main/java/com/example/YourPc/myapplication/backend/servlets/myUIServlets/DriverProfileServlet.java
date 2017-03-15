package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import com.example.YourPc.myapplication.backend.apiMethods.OrderAPI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Muhammad Saeed on 3/13/2017.
 */

public class DriverProfileServlet extends HttpServlet {
    OrderAPI orderAPI= OrderAPI.getApiSinglton();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("driverName");
        String password =request.getParameter("driverName");
        String email =request.getParameter("driverEmail");
        String city = request.getParameter("driverCity");
        String phone = request.getParameter("driverPhone");
        String vehicle =request.getParameter("vehicle");
        String imageURL=request.getParameter("imageURL");
        orderAPI.createDriver(name,password,email,city,phone,vehicle,imageURL);
        response.sendRedirect("DriverProfile.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
