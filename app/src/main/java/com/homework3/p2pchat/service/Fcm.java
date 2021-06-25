package com.homework3.p2pchat.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.homework3.p2pchat.model.Chat;
import com.homework3.p2pchat.view.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Fcm extends FirebaseMessagingService {
    private static MutableLiveData<Chat> chatMld = new MutableLiveData<>();

    public static MutableLiveData<Chat> getCurrentChatMld() {
        return chatMld;
    }

    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        if(!data.get("token").equals(MainActivity.deviceToken)) {
            Chat chat = new Chat();
            chat.isi = data.get("chat");
            chat.isMe = false;
            chatMld.postValue(chat);
        }
    }
}
