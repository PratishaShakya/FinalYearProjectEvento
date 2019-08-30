package com.example.afinal;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit retrofit = null;

    // public static String url = "http://192.168.10.68:81/api/";
    public static String url = " https://fcm.googleapis.com/";

    public static Retrofit getFormData(){

        if (retrofit ==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
