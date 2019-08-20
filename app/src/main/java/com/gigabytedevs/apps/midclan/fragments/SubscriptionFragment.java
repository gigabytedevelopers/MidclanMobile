package com.gigabytedevs.apps.midclan.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.adapters.ProfileAdapter;
import com.gigabytedevs.apps.midclan.adapters.SubscriptionUserAdapter;
import com.gigabytedevs.apps.midclan.models.SubscriptionUserModel;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubscriptionFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<SubscriptionUserModel> list;
    private ProfileAdapter adapter;


    public SubscriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subscription, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.subscription_list);
        recyclerView.setLayoutManager(new CardSliderLayoutManager(1,750,14));

        new CardSnapHelper().attachToRecyclerView(recyclerView);

        list = new ArrayList<>();

        SubscriptionUserModel subscriptionUserModel = new SubscriptionUserModel(getString(R.string.text_subscription_user_feature_free),
                "0.0",
                getString(R.string.text_subscription_user_feature_free_1),
                getString(R.string.text_subscription_user_feature_free_2),
                getString(R.string.text_subscription_user_feature_free_3));
        list.add(subscriptionUserModel);
        SubscriptionUserModel subscriptionUserModel1 = new SubscriptionUserModel(getString(R.string.text_subscription_user_feature_intermediate),
                "$1,000",
                getString(R.string.text_subscription_user_feature_free_1),
                getString(R.string.text_subscription_user_feature_free_2),
                getString(R.string.text_subscription_user_feature_free_3));
        list.add(subscriptionUserModel1);
        SubscriptionUserModel subscriptionUserModel2 = new SubscriptionUserModel(getString(R.string.text_subscription_user_feature_professional),
                "5,000",
                getString(R.string.text_subscription_user_feature_free_1),
                getString(R.string.text_subscription_user_feature_free_2),
                getString(R.string.text_subscription_user_feature_free_3));
        list.add(subscriptionUserModel2);

        SubscriptionUserAdapter adapter = new SubscriptionUserAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
    }
}
