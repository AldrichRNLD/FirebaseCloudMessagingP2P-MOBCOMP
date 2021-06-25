package com.homework3.p2pchat.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.homework3.p2pchat.R;
import com.homework3.p2pchat.model.Chat;
import com.homework3.p2pchat.model.ChatDataRetrofit;
import com.homework3.p2pchat.model.ChatRetrofit;
import com.homework3.p2pchat.repo.ChatSender;
import com.homework3.p2pchat.view.adapter.AdapterChat;
import com.homework3.p2pchat.viewmodel.MainViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String fcmTopic = "1";
    public static String deviceToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    deviceToken = task.getResult();
                });

        FirebaseMessaging.getInstance().subscribeToTopic(fcmTopic);

        MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);
        LiveData<Chat> ld = vm.getChatLd();
        ArrayList<Chat> chatData = new ArrayList<>();

        EditText chatText = findViewById(R.id.chat);
        Button sendText = findViewById(R.id.send);
        RecyclerView chatList = findViewById(R.id.chat_list);
        chatList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AdapterChat chatAdapt = new AdapterChat(chatData);
        chatList.setAdapter(chatAdapt);

        ld.observe(this, chat -> {
            int latestPos = chatData.size();
            chatData.add(chat);
            chatAdapt.notifyItemInserted(latestPos);
        });

        sendText.setOnClickListener(v -> {
            Chat chat = new Chat();
            chat.isi = chatText.getText().toString();
            chat.isMe = true;
            if(chat.isi.length() > 0) {
                ChatRetrofit cr = new ChatRetrofit();
                cr.to = "/topics/" + fcmTopic;
                ChatDataRetrofit cdr = new ChatDataRetrofit();
                cdr.chat = chat.isi;
                cdr.token = deviceToken;
                cr.data = cdr;
                ChatSender.sendChat(cr);

                int latestPos = chatData.size();
                chatData.add(chat);
                chatAdapt.notifyItemInserted(latestPos);

                chatText.setText("");
            } else {
                Toast.makeText(this, "Chat is empty!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}