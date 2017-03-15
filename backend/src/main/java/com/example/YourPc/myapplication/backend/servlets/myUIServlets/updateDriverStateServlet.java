package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import com.example.YourPc.myapplication.backend.apiMethods.OrderAPI;
import com.example.YourPc.myapplication.backend.profiles.Driver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Muhammad Saeed on 3/13/2017.
 */
public class updateDriverStateServlet extends HttpServlet {
    OrderAPI orderAPI = OrderAPI.getApiSinglton();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("driverName");
        boolean idle = true;
        if (request.getParameter("idle")==null){
            idle=false;
        }
        Driver driver = orderAPI.getDriverByName(name);
        driver.idle = idle;
        orderAPI.updateDriverState(idle,driver.driverID);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
