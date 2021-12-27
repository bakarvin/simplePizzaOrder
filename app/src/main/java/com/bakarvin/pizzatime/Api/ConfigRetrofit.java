package com.bakarvin.pizzatime.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.bakarvin.pizzatime.BuildConfig.BASE_URL;

public class ConfigRetrofit {

    public static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static ApiService service = retrofit.create(ApiService.class);
}
