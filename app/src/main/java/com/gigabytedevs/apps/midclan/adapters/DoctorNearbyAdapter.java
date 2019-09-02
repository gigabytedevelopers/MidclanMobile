package com.gigabytedevs.apps.midclan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.models.discover_model.DoctorNearByModel;
import com.gigabytedevs.apps.midclan.utils.ClickListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class DoctorNearbyAdapter extends RecyclerView.Adapter<DoctorNearbyAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DoctorNearByModel> list;
    private ClickListener clickListener;

    public DoctorNearbyAdapter(Context context, ArrayList<DoctorNearByModel> list, ClickListener clickListener){
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CircularImageView doctorImage;
        private AppCompatTextView doctorName;
        private AppCompatTextView doctorLocation;
        private AppCompatTextView doctorAddress;
        private RatingBar doctorRating;
        public ViewHolder(View view){
            super(view);
            doctorName = view.findViewById(R.id.doctor_nearby_name);
            doctorLocation = view.findViewById(R.id.doctor_nearby_location);
            doctorAddress = view.findViewById(R.id.doctor_nearby_address);
            doctorImage = view.findViewById(R.id.doctor_nearby_image);
            doctorRating = view.findViewById(R.id.doctor_nearby_rating);
        }
    }
    @NonNull
    @Override
    public DoctorNearbyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor_nearby, parent, false);
        final DoctorNearbyAdapter.ViewHolder myViewHolder = new DoctorNearbyAdapter.ViewHolder(view);
        view.setOnClickListener(view1 -> clickListener.onItemClick(view1, myViewHolder.getAdapterPosition()));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorNearbyAdapter.ViewHolder holder, int position) {
        DoctorNearByModel doctorNearByModel = list.get(position);

        holder.doctorName.setText(doctorNearByModel.getDoctorName());
        holder.doctorAddress.setText(doctorNearByModel.getDoctorAddress());
        holder.doctorLocation.setText(doctorNearByModel.getDoctorLocation());
        holder.doctorImage.setImageResource(doctorNearByModel.getDoctorImage());
        holder.doctorRating.setRating(doctorNearByModel.getDoctorRating());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}