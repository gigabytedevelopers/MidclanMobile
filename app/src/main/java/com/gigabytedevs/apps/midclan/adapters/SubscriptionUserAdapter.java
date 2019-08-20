package com.gigabytedevs.apps.midclan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.models.SubscriptionUserModel;

import java.util.ArrayList;

public class SubscriptionUserAdapter extends RecyclerView.Adapter<SubscriptionUserAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SubscriptionUserModel> list;

    public SubscriptionUserAdapter(Context context, ArrayList<SubscriptionUserModel> list){
        this.context = context;
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private AppCompatTextView subscriptionType;
        private AppCompatTextView subscriptionAmount;
        private AppCompatTextView featureInfo1;
        private AppCompatTextView featureInfo2;
        private AppCompatTextView featureInfo3;
        public ViewHolder(View view){
            super(view);
            subscriptionType = view.findViewById(R.id.user_subscription_type);
            subscriptionAmount = view.findViewById(R.id.user_subscription_amount);
            featureInfo1 = view.findViewById(R.id.user_subscription_feature_1);
            featureInfo2 = view.findViewById(R.id.user_subscription_feature_2);
            featureInfo3 = view.findViewById(R.id.user_subscription_feature_3);
        }
    }
    @NonNull
    @Override
    public SubscriptionUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subscription_user, parent, false);
        final ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubscriptionUserAdapter.ViewHolder holder, int position) {
        SubscriptionUserModel subscriptionUserModel = list.get(position);
        holder.subscriptionType.setText(subscriptionUserModel.getSubscriptionType());
        holder.subscriptionAmount.setText(subscriptionUserModel.getSubscriptionAmount());
        holder.featureInfo1.setText(subscriptionUserModel.getFeatureInfo1());
        holder.featureInfo2.setText(subscriptionUserModel.getFeatureInfo2());
        holder.featureInfo3.setText(subscriptionUserModel.getFeatureInfo3());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
