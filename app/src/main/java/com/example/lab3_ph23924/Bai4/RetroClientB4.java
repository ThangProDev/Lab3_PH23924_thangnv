package com.example.lab3_ph23924.Bai4;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClientB4 {
    private static final String ROOT_URL = "http://192.168.1.90:80/thangnv_ph23924/";
    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiServiceB4 getApiService() {
        return getRetrofitInstance().create(ApiServiceB4.class);
    }
}
