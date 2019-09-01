package com.gigabytedevs.apps.midclan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.fragments.FeedsFragment;
import com.gigabytedevs.apps.midclan.models.TimelineModel;
import com.gigabytedevs.apps.midclan.service.SendVolleyRequest;
import com.gigabytedevs.apps.midclan.utils.ClickListener;
import com.gigabytedevs.apps.midclan.utils.TinyDb;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {
 private Context context;
 private ArrayList<TimelineModel> list;
 private ClickListener clickListener;
 private TinyDb tinyDb;

 public class ViewHolder extends RecyclerView.ViewHolder{
     private AppCompatImageView mainImage;
     private AppCompatToggleButton bookMark;
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
         bookMark = view.findViewById(R.id.post_text_bookmark);
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
        tinyDb = new TinyDb(context);
        view.setOnClickListener(view1 -> clickListener.onItemClick(view1, myViewHolder.getAdapterPosition()));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineAdapter.ViewHolder holder, int position) {
        TimelineModel timelineModel = list.get(position);
        holder.mainImage.setImageResource(timelineModel.getMainImageResource());
//        Glide.with(context)
//                .load(timelineModel.getMainImageUrl())
//                .into(holder.mainImage);
        holder.title.setText(timelineModel.getTitle());
        holder.description.setText(timelineModel.getDescription());
        holder.profileImage.setImageResource(timelineModel.getProfileImage());
//        Glide.with(context)
//                .load(timelineModel.getProfileImageUrl())
//                .into(holder.profileImage);
        holder.name.setText(timelineModel.getName());
        holder.time.setText(timelineModel.getTime());

        //setting the click function of the bookmark
        holder.bookMark.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked){
                Toast.makeText(context, String.valueOf(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
//                //Creating the body of the request
//                JSONObject params = new JSONObject();
//                String mRequestBody ="";
//
//                try {
//                    params.put("postId", "5d68ee42d0e6e50004933984");
//                    mRequestBody = params.toString();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                /**
                 * When the bookmark of a paricular card is clicked, the code sets the
                 * drawable of the bookmark to the filled color and sends a request to the
                 * SendVolleyRequest class and puts the request result into the static response array
                 * from the FeedsFragment which is this code below
                 *  FeedsFragment.responseArray = SendVolleyRequest.SendRequest(context.getResources().
                 *                  getString(R.string.base_url)+ "bookmark/add",mRequestBody,"POST-HEAD",context);
                 *  For the else block
                 *  the code sets the bookmark icon to the outline icon that it the icon unfilled
                 *  then it sends a request to the SendVolleyRequest class and puts the response
                 *  in the response array from the FeedsFragment
                 */
                holder.bookMark.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_bookmark));
//                FeedsFragment.responseArray = SendVolleyRequest.SendRequest(context.getResources().getString(R.string.base_url)+ "bookmark/add",mRequestBody,"POST-HEAD",context);
//
            }else{
                holder.bookMark.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_bookmark_outline));
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

}
