package com.example.YourPc.myapplication.backend.helpers;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Muhammad Saeed on 3/19/2017.
 */

public class FireBaseHelper {
    public static final MediaType JSON =
            MediaType.parse("application/json; charset=utf-8");
    public static final String WEB_API_KEY = "AIzaSyBea8gLbowPRcQCG-MvcQ_V2FbfW1Bmhho";

    private void sendNotification(final String regΤoken) {
        OkHttpClient client = new OkHttpClient();
        JSONObject json = new JSONObject();
        JSONObject dataJson = new JSONObject();
        dataJson.put("body", "Hi this is sent from device to device");
        dataJson.put("title", "dummy title");
        json.put("notification", dataJson);
        json.put("to", regΤoken);
        RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder()
                .header("Authorization", "key=" + Constants.LEGACY_SERVER_KEY)
                .url("https://fcm.googleapis.com/fcm/send")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String finalResponse = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
