package com.gigabytedevs.apps.midclan.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.activities.NotificationActivity;
import com.gigabytedevs.apps.midclan.activities.PostPreviewActivity;
import com.gigabytedevs.apps.midclan.adapters.TimelineAdapter;
import com.gigabytedevs.apps.midclan.models.TimelineModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedsFragment extends Fragment {
    private AppCompatTextView appTitle;
    private AppCompatImageView appNotify;
    private RecyclerView recyclerView;
    private ArrayList<TimelineModel> list;
    private TimelineAdapter adapter;

    public FeedsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feeds, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appTitle = view.findViewById(R.id.app_bar);
        appNotify = view.findViewById(R.id.notification_btn);
        appTitle.setText(getString(R.string.nav_feeds));
        appNotify.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), NotificationActivity.class));
        });

        recyclerView = view.findViewById(R.id.feeds_list);
        list = new ArrayList<>();

        TimelineModel timelineModel = new TimelineModel(R.drawable.img_plant_9,getResources().getString(R.string.dummy_title),getResources().getString(R.string.dummy_text),"dennisrichtie","11:00pm",R.drawable.test);
        list.add(timelineModel);

        TimelineModel timelineModel2 = new TimelineModel(R.drawable.test,getResources().getString(R.string.dummy_title),getResources().getString(R.string.dummy_text),"mezueceejay","Today",R.drawable.test);
        list.add(timelineModel2);

        adapter = new TimelineAdapter(getContext(), list, ((view1, position) -> {
            switch (position){
                case 0:
                    startActivity(new Intent(getActivity(), PostPreviewActivity.class));
                    return;
            }
        }));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
