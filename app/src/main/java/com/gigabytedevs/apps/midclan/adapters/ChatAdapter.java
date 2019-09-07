package com.gigabytedevs.apps.midclan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.models.ChatModel;
import com.gigabytedevs.apps.midclan.utils.ClickListener;

import java.util.ArrayList;


public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ChatModel> list;
    private ClickListener clickListener;

    public ChatAdapter(Context context, ArrayList<ChatModel> list, ClickListener clickListener){
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView chatAvatar;
        private AppCompatTextView chatUserName;
        private AppCompatTextView chatMsg;
        private AppCompatTextView chatTime;
        public ViewHolder(View view){
            super(view);
            chatAvatar = view.findViewById(R.id.chat_user_avatar);
            chatUserName = view.findViewById(R.id.chat_user_name);
            chatMsg = view.findViewById(R.id.chat_msg);
            chatTime = view.findViewById(R.id.time);
        }
    }
    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        final ChatAdapter.ViewHolder myViewHolder = new ChatAdapter.ViewHolder(view);
        view.setOnClickListener(view1 -> clickListener.onItemClick(view1, myViewHolder.getAdapterPosition()));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        ChatModel chatModel = list.get(position);

        //holder.chatAvatar.setImageResource(chatModel.getSenderImage());
        Glide.with(context)
                .load(chatModel.getSenderImageUrl())
                .into(holder.chatAvatar);
        holder.chatUserName.setText(chatModel.getSenderName());
        holder.chatMsg.setText(chatModel.getSenderText());
        holder.chatTime.setText(chatModel.getSenderTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
