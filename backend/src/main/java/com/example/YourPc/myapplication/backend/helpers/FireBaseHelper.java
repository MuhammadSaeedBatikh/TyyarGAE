package com.example.YourPc.myapplication.backend.helpers;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Muhammad Saeed on 3/19/2017.
 */

public class FireBaseHelper {
    public static String type = "application/json";
    static URL url;

    static {
        try {
            url = new URL("https://fcm.googleapis.com/fcm/send");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    static String webApiKey = "key=AAAAPWYElsU:APA91bEqM1sidTSZZU064AZYZyUFeYBBD05Sr0nTlWGECo5T9OfnMMA9-xPVdtU-UhzgVDuitJFgh5DUxHrwkNxQiKZeqQ5X3zqkZOCuuGCfj_Gh1Hied0bdTS85etKycJ4oonOiX_E0";

    public FireBaseHelper() throws MalformedURLException {
    }

    public static void sendNotification(String registerationToken,
                                        String message) throws IOException {

        String regToken = registerationToken;
        String body = message;
        JSONObject jsonObject = createJSONNotification(regToken, body);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Content-Type", type);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Authorization", webApiKey);
        httpURLConnection.setDoOutput(true);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
        outputStreamWriter.write(jsonObject.toString());
        outputStreamWriter.close();
        System.out.println(httpURLConnection.getResponseCode() + "  "
                + httpURLConnection.getResponseMessage());
    }

    public static JSONObject createJSONNotification(String regToken, String body) {
        JSONObject jsonObject = new JSONObject();
        JSONObject dataJson = new JSONObject();
        dataJson.put("body", body);
        dataJson.put("title", "this is a title");
        jsonObject.put("notification", dataJson);
        jsonObject.put("to", regToken);
        return jsonObject;
    }
}