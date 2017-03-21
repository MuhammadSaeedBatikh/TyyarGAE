package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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
        String regToken = request.getParameter("cWDKF4SdJIM:APA91bHBp3Y421OB1CSQ4-HM6-gh8jZMtYR1aSeRuDhzCC15Djc2Mx0y6nWimqlw0M-bV_oNy1Jopwx7PLv5AWX8x_y5nh41l1oCLUwP5hd70FAI5lBh9nXRGgnfDDfQ8G8GPj_Bc7ma");
        String body = "aaa";
        System.out.println(body);
        String rawData = createJSONNotification(regToken,body).toString();
        String type = "application/json";
        System.out.println(rawData);
        String encodedData = URLEncoder.encode(rawData,"UTF-8");
        System.out.println(encodedData);
        URL u = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty( "Content-Type", type );
        conn.setRequestProperty( "Content-Length", String.valueOf(encodedData.length()));
        conn.setRequestProperty("","");
        OutputStream os = conn.getOutputStream();
        System.out.println(os);
        os.write(encodedData.getBytes());
        System.out.println(conn.getResponseMessage());
    }

    public JSONObject createJSONNotification(String regToken, String body) {
        JSONObject json = new JSONObject();
        JSONObject dataJson = new JSONObject();
        dataJson.put("body", body);
        dataJson.put("title", "thi is a title");
        json.put("notification", dataJson);
        json.put("to", regToken);
        return json;
    }
}
