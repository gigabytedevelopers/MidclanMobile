package com.gigabytedevs.apps.midclan.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.adapters.ProfileAdapter;
import com.gigabytedevs.apps.midclan.models.ProfileModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private AppCompatTextView appTitle;
    private AppCompatImageView appNotify;
    private RecyclerView recyclerView;
    private ArrayList<ProfileModel> list;
    private ProfileAdapter adapter;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appTitle = view.findViewById(R.id.app_bar);
        appNotify = view.findViewById(R.id.notification_btn);
        appTitle.setText(getString(R.string.nav_profile));
        appNotify.setOnClickListener(view1 -> {

        });
        recyclerView = view.findViewById(R.id.profile_list);
        list = new ArrayList<>();

        ProfileModel profileModel = new ProfileModel(getString(R.string.text_profile_edit_profile), getString(R.string.text_profile_edit_profile_hint));
        list.add(profileModel);
        ProfileModel profileModel2 = new ProfileModel(getString(R.string.text_profile_bookmark), getString(R.string.text_profile_bookmark_hint));
        list.add(profileModel2);
        ProfileModel profileModel3 = new ProfileModel(getString(R.string.text_profile_subscriptions), getString(R.string.text_profile_subscriptions_hint));
        list.add(profileModel3);


        adapter = new ProfileAdapter(getContext(), list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(adapter);
    }
}
