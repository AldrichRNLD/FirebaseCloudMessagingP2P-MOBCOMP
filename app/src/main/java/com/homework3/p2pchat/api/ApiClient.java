package com.homework3.p2pchat.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String ServerURL = "https://fcm.googleapis.com/";

    public static Retrofit.Builder getApiClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(logging);
        okHttpClient.readTimeout(30, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS);

        return new Retrofit.Builder()
                .baseUrl(ServerURL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create());
    }

    public static ApiInterface getApiInterface() {
        return getApiClient().build().create(ApiInterface.class);
    }
}
