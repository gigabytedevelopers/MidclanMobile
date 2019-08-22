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

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.activities.NotificationActivity;
import com.gigabytedevs.apps.midclan.activities.SearchActivity;

import org.jetbrains.annotations.NotNull;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatTextView appTitle = view.findViewById(R.id.app_bar);
        AppCompatImageView appNotify = view.findViewById(R.id.notification_btn);
        AppCompatImageView appSearch = view.findViewById(R.id.search_btn);
        appTitle.setText(getString(R.string.nav_discover));
        appNotify.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), NotificationActivity.class)));
        appSearch.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), SearchActivity.class)));
    }
}
