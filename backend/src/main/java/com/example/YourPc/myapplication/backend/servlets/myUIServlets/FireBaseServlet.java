package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import com.example.YourPc.myapplication.backend.helpers.FireBaseHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Muhammad Saeed on 3/20/2017.
 */

public class FireBaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String regToken = req.getParameter("RegToken");
        String body = req.getParameter("body");
        FireBaseHelper.sendNotification(regToken,body);
    }
}
