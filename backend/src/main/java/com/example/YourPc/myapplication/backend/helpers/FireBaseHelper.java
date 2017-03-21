package com.example.YourPc.myapplication.backend.helpers;

import java.io.IOException;

import okhttp3.MediaType;

/**
 * Created by Muhammad Saeed on 3/19/2017.
 */

public class FireBaseHelper {
    public static final MediaType JSON =
            MediaType.parse("application/json; charset=utf-8");
    public static final String WEB_API_KEY = "AIzaSyBea8gLbowPRcQCG-MvcQ_V2FbfW1Bmhho";

    static {
        System.out.println("hiiiiiiiiii");
    }

    public static void sendNotification(final String regΤoken, String body) throws IOException {


        /*      System.out.println("Progressssssssssssss");
        String message = URLEncoder.encode("my message", "UTF-8");
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        JSONObject json = new JSONObject();
        JSONObject dataJson = new JSONObject();
        dataJson.put("body", body);
        dataJson.put("title", "dummy title");
        json.put("notification", dataJson);
        json.put("to", regΤoken);
        System.out.println(json);
        HttpPost request = new HttpPost();
        String rawData = "id=10";
        String type = "application/x-www-form-urlencoded";
        String encodedData = URLEncoder.encode( rawData );
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty( "Content-Type", type );
        conn.setRequestProperty( "Content-Length", String.valueOf(encodedData.length()));
        OutputStream os = conn.getOutputStream();
        os.write(encodedData.getBytes());
*/
        /*try {
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer json = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();
            JSONObject jsonObject = new JSONObject();

        OkHttpClient client = new OkHttpClient();
        JSONObject json = new JSONObject();
        JSONObject dataJson = new JSONObject();
        dataJson.put("body", body);
        dataJson.put("title", "dummy title");
        json.put("notification", dataJson);
        json.put("to", regΤoken);
        RequestBody requestBody = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder()
                .header("Authorization", "key=" + Constants.LEGACY_SERVER_KEY)
                .url("https://fcm.googleapis.com/fcm/send")
                .post(requestBody)
                .build();

       try {
            Response response = client.newCall(request).execute();
            String finalResponse = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
