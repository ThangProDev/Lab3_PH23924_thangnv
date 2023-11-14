package com.example.lab3_ph23924.Bai1;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }
    public  String makeServiceCall(String reqUrl){
        String response= null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //đọc response
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            response = convertStreamToString(inputStream);
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }
    private String convertStreamToString(InputStream inputStream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        try {
            while ((line = reader.readLine()) != null){
                stringBuffer.append(line).append("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }
}
