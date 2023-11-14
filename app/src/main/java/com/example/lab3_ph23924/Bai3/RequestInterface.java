package com.example.lab3_ph23924.Bai3;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
    @GET("jsonandroid.html")
    Call<JSONResponse> getJSON();
}
