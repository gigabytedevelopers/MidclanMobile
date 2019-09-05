package com.gigabytedevs.apps.midclan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.models.CommentsModel;
import com.gigabytedevs.apps.midclan.utils.ClickListener;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private List<CommentsModel> list;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private AppCompatImageView profileImage;
        private AppCompatTextView name, comment;
        public ViewHolder(View view){
            super(view);
            profileImage = view.findViewById(R.id.user_comment_image);
            name = view.findViewById(R.id.user_name_comment);
            comment = view.findViewById(R.id.user_comment_text);
        }
    }

    public CommentsAdapter(Context context, List<CommentsModel> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        final CommentsAdapter.ViewHolder viewHolder = new CommentsAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder holder, int position) {
        CommentsModel commentsModel = list.get(position);
        Glide.with(context)
                .load(commentsModel.getImageUrl())
                .into(holder.profileImage);

        holder.name.setText(commentsModel.getName());
        holder.name.setTextColor(commentsModel.getColor());
        holder.comment.setText(commentsModel.getComment());
        holder.comment.setTextColor(commentsModel.getColor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
