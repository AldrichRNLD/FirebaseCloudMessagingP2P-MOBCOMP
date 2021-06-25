package com.homework3.p2pchat.model;

import com.google.gson.annotations.SerializedName;

public class ChatDataRetrofit {
    @SerializedName("chat")
    public String chat;
    @SerializedName("token")
    public String token;
}
