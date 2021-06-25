package com.homework3.p2pchat.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.homework3.p2pchat.model.Chat;
import com.homework3.p2pchat.service.Fcm;

public class MainViewModel extends ViewModel {
    public LiveData<Chat> getChatLd() {
        return Fcm.getCurrentChatMld();
    }
}
