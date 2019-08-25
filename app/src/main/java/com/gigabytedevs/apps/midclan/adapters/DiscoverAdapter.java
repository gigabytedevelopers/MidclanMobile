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
import com.gigabytedevs.apps.midclan.models.DiscoverModel;
import com.gigabytedevs.apps.midclan.utils.ClickListener;

import java.util.ArrayList;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DiscoverModel> list;
    private ClickListener clickListener;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private AppCompatImageView discoverImage;
        private AppCompatTextView discoverTitle;
        public ViewHolder(View view){
            super(view);
            discoverImage = view.findViewById(R.id.discover_image);
            discoverTitle = view.findViewById(R.id.discover_title);
        }
    }


    public DiscoverAdapter(Context context, ArrayList<DiscoverModel> list, ClickListener clickListener){
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }



    @NonNull
    @Override
    public DiscoverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discover, parent, false);
        final DiscoverAdapter.ViewHolder myViewHolder = new DiscoverAdapter.ViewHolder(view);
        view.setOnClickListener(view1 -> clickListener.onItemClick(view1, myViewHolder.getAdapterPosition()));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverAdapter.ViewHolder holder, int position) {
        DiscoverModel discoverModel = list.get(position);
        holder.discoverImage.setImageResource(discoverModel.getDiscoverImage());
        holder.discoverTitle.setText(discoverModel.getDiscoverTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
