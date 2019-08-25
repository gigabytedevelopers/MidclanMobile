package com.gigabytedevs.apps.midclan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.models.TimelineModel;
import com.gigabytedevs.apps.midclan.utils.ClickListener;

import java.util.ArrayList;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {
 private Context context;
 private ArrayList<TimelineModel> list;
 private ClickListener clickListener;

 public class ViewHolder extends RecyclerView.ViewHolder{
     private AppCompatImageView mainImage;
     private AppCompatTextView title;
     private AppCompatTextView description;
     private AppCompatImageView profileImage;
     private AppCompatTextView name;
     private AppCompatTextView time;
     public ViewHolder(View view){
         super(view);
         mainImage = view.findViewById(R.id.post_text);
         title = view.findViewById(R.id.post_text_title);
         description = view.findViewById(R.id.post_text_body);
         profileImage = view.findViewById(R.id.user_text_post_profile);
         name = view.findViewById(R.id.user_text_username);
         time = view.findViewById(R.id.user_text_time);
     }
 }


         public TimelineAdapter(Context context, ArrayList<TimelineModel> list, ClickListener clickListener){
                this.context = context;
                this.list = list;
                this.clickListener = clickListener;
            }



    @NonNull
    @Override
    public TimelineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_post_text, parent, false);
        final TimelineAdapter.ViewHolder myViewHolder = new TimelineAdapter.ViewHolder(view);
        view.setOnClickListener(view1 -> clickListener.onItemClick(view1, myViewHolder.getAdapterPosition()));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineAdapter.ViewHolder holder, int position) {
        TimelineModel timelineModel = list.get(position);
        holder.profileImage.setImageResource(timelineModel.getMainImageResource());
        holder.title.setText(timelineModel.getTitle());
        holder.description.setText(timelineModel.getDescription());
        holder.profileImage.setImageResource(timelineModel.getProfileImage());
        holder.name.setText(timelineModel.getName());
        holder.time.setText(timelineModel.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
