package com.gigabytedevs.apps.midclan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.models.ProfileModel;
import com.gigabytedevs.apps.midclan.utils.ClickListener;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProfileModel> list;
    private ClickListener clickListener;

    public ProfileAdapter(Context context, ArrayList<ProfileModel> list, ClickListener clickListener){
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private AppCompatTextView profileTitle;
        private AppCompatTextView profileCaption;
        public ViewHolder(View view){
            super(view);
            profileTitle = view.findViewById(R.id.profile_title);
            profileCaption = view.findViewById(R.id.profile_caption);
        }
    }
    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_card, parent, false);
        final ProfileAdapter.ViewHolder myViewHolder = new ProfileAdapter.ViewHolder(view);
        view.setOnClickListener(view1 -> clickListener.onItemClick(view1, myViewHolder.getAdapterPosition()));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        ProfileModel profileModel = list.get(position);

        holder.profileTitle.setText(profileModel.getProfileTitle());
        holder.profileCaption.setText(profileModel.getProfileCaption());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}