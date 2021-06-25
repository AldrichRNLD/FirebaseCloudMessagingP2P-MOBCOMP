package com.homework3.p2pchat.model;

import com.google.gson.annotations.SerializedName;

public class ChatRetrofit {
    @SerializedName("to")
    public String to;
    @SerializedName("data")
    public ChatDataRetrofit data;
}
