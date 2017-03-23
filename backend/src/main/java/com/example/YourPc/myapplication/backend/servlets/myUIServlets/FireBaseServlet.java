package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import com.example.YourPc.myapplication.backend.apiMethods.OrderAPI;

import org.json.JSONObject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Muhammad Saeed on 3/20/2017.
 */

public class FireBaseServlet extends HttpServlet {
    OrderAPI mOrderAPI = OrderAPI.getApiSinglton();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String regToken = request.getParameter("regToken");
        String body = request.getParameter("body");
        mOrderAPI.sendNotificationByRegToken(regToken,body);

    }

    public JSONObject createJSONNotification(String regToken, String body) {
        JSONObject json = new JSONObject();
        JSONObject dataJson = new JSONObject();
        dataJson.put("body", body);
        dataJson.put("title", "this is a title");
        json.put("notification", dataJson);
        json.put("to", regToken);
        return json;
    }
}
