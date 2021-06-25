package com.homework3.p2pchat.api;

import android.content.Context;

import com.homework3.p2pchat.model.ChatRetrofit;
import com.homework3.p2pchat.view.MainActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @Headers({"Authorization: key=AAAAd_uFUTA:APA91bEkIGUQhooogPyCPOFpGzxerenBvExVvlrTZl__qi5EzzpN9TaLz3_ZFk5xGjhDrW_5oFSouIpbXare1EaW8qn_du1q6oHl2oDMRvz065aMeodaZYLiFqfoh9obMTjmH_IzrZNB",
            "Content-Type:application/json"})
    @POST("fcm/send")
    Call<ResponseBody> sendChat(
        @Body ChatRetrofit chatRetrofit
    );
}
