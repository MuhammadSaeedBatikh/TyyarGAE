package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Muhammad Saeed on 3/20/2017.
 */

public class FireBaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String regToken = request.getParameter("RegToken");
        String body = request.getParameter("body");
        JSONObject jsonObject = createJSONNotification(regToken, body);
        String type = "application/json";
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        String webApiKey = "key=AAAAPWYElsU:APA91bEqM1sidTSZZU064AZYZyUFeYBBD05Sr0nTlWGECo5T9OfnMMA9-xPVdtU-UhzgVDuitJFgh5DUxHrwkNxQiKZeqQ5X3zqkZOCuuGCfj_Gh1Hied0bdTS85etKycJ4oonOiX_E0";
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Content-Type", type);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Authorization",webApiKey);
        httpURLConnection.setDoOutput(true);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
        outputStreamWriter.write(jsonObject.toString());
        outputStreamWriter.close();
        System.out.println(httpURLConnection.getResponseCode() + "  "
                + httpURLConnection.getResponseMessage());
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
