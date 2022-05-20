package com.example.nschat.Chatting;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nschat.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private List<Message> messageList;
    private Activity activity;

    //리사이클러 뷰에 사용되는 어뎁터
    public ChatAdapter(List<Message> messageList, Activity activity) {
        this.messageList = messageList;
        this.activity = activity;
    }

    @NonNull @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.adapter_message_one, parent, false);
        return new MyViewHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String message = messageList.get(position).getMessage();
        boolean isReceived = messageList.get(position).getIsReceived();
        if(isReceived){
            holder.messageReceive.setVisibility(View.VISIBLE);
            holder.ImageReceive.setVisibility(View.VISIBLE);
            holder.messageSend.setVisibility(View.GONE);
            holder.messageReceive.setText(message);
        }else {
            holder.messageSend.setVisibility(View.VISIBLE);
            holder.messageReceive.setVisibility(View.GONE);
            holder.messageSend.setText(message);
        }
    }

    @Override public int getItemCount() {
        return messageList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView messageSend;
        TextView messageReceive;
        ImageView ImageReceive;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            messageSend = itemView.findViewById(R.id.message_send);
            messageReceive = itemView.findViewById(R.id.message_receive);
            ImageReceive = itemView.findViewById(R.id.chatBotImageView);
        }
    }

}
