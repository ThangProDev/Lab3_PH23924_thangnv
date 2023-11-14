package com.example.lab3_ph23924.Bai4;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceB4 {
    @GET("/json_data.json")
    Call<ContactListB4> getMyJSON();
}
