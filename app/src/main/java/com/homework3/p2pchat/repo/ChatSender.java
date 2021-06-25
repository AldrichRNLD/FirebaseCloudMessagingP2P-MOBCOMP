package com.homework3.p2pchat.repo;

import android.util.Log;

import com.homework3.p2pchat.api.ApiClient;
import com.homework3.p2pchat.model.ChatRetrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatSender {
    public static void sendChat(ChatRetrofit chat) {
        Call<ResponseBody> response = ApiClient.getApiInterface().sendChat(chat);

        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.body() != null) {
                    if (response.isSuccessful())
                        Log.v("success", response.body().toString());
                    else
                        Log.v("failed", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("failed", t.getMessage());
            }
        });
    }
}
