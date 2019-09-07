package com.gigabytedevs.apps.midclan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.models.PostImageModel;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class PostImageAdapter extends RecyclerView.Adapter<PostImageAdapter.ViewHolder> {
    private Context context;
    private List<PostImageModel> list;

    public PostImageAdapter(Context context, List<PostImageModel> list){
        this.context = context;
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CircularImageView imageView;
        public ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.image_picked);
        }
    }
    @NonNull
    @Override
    public PostImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picked_images, parent, false);
        final PostImageAdapter.ViewHolder viewHolder = new PostImageAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostImageAdapter.ViewHolder holder, int position) {
        PostImageModel postImageModel = list.get(position);
        holder.imageView.setImageBitmap(postImageModel.getImgResource());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
