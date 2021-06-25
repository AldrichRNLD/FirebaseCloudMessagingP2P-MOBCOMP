package com.homework3.p2pchat.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.homework3.p2pchat.R;
import com.homework3.p2pchat.model.Chat;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.VHChat> {
    private ArrayList<Chat> chatList;

    public AdapterChat(ArrayList<Chat> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public AdapterChat.VHChat onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_bubble, parent, false);
        return new VHChat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull AdapterChat.VHChat holder, int position) {
        Chat chat = chatList.get(position);
        if(chat.isMe) {
            holder.op.setVisibility(View.GONE);
            holder.me.setVisibility(View.VISIBLE);
            holder.me.setText(chat.isi);
        } else {
            holder.me.setVisibility(View.GONE);
            holder.op.setVisibility(View.VISIBLE);
            holder.op.setText(chat.isi);
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    class VHChat extends RecyclerView.ViewHolder {
        public TextView me;
        public TextView op;
        public VHChat(@NonNull @NotNull View itemView) {
            super(itemView);
            me = itemView.findViewById(R.id.me);
            op = itemView.findViewById(R.id.oposite);
        }
    }
}
