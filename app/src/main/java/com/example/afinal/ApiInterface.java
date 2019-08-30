package com.example.afinal;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({"Authorization: key=AIzaSyCNe2SEeMuFjaTS5nhMkWa_eu06hb7hiWM",
            "Content-Type:application/json"})
    @POST("fcm/send")
    Call<JsonObject> sendNotification(@Body JsonObject send);


}
