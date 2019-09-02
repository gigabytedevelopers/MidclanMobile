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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.activities.DoctorNearByActivity;
import com.gigabytedevs.apps.midclan.activities.NotificationActivity;
import com.gigabytedevs.apps.midclan.activities.SearchActivity;
import com.gigabytedevs.apps.midclan.adapters.DiscoverAdapter;
import com.gigabytedevs.apps.midclan.models.DiscoverModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<DiscoverModel> list;
    private DiscoverAdapter adapter;

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
        recyclerView = view.findViewById(R.id.list_discover);
        list = new ArrayList<>();

        DiscoverModel discoverModel0 = new DiscoverModel(getString(R.string.text_discover_find_hospital),R.drawable.ic_hospital);
        list.add(discoverModel0);


        DiscoverModel discoverModel1 = new DiscoverModel(getString(R.string.text_discover_doctor), R.drawable.ic_doctor);
        list.add(discoverModel1);

        DiscoverModel discoverModel2 = new DiscoverModel(getString(R.string.text_discover_pharmacy), R.drawable.ic_pharmacist);
        list.add(discoverModel2);

        DiscoverModel discoverModel3 = new DiscoverModel(getString(R.string.text_discover_lab_near_you), R.drawable.ic_medical);
        list.add(discoverModel3);

        DiscoverModel discoverModel4 = new DiscoverModel(getString(R.string.text_discover_drugs), R.drawable.ic_medicine);
        list.add(discoverModel4);

        DiscoverModel discoverModel5 = new DiscoverModel(getString(R.string.text_discover_shop_medical_equipment), R.drawable.ic_flask);
        list.add(discoverModel5);

        DiscoverModel discoverModel6 = new DiscoverModel(getString(R.string.text_discover_insurance), R.drawable.ic_medical_history);
        list.add(discoverModel6);

        DiscoverModel discoverModel7 = new DiscoverModel(getString(R.string.text_discover_medical_aid), R.drawable.ic_first_aid_kit);
        list.add(discoverModel7);


        adapter = new DiscoverAdapter(getContext(), list, (view1, position)->{
            switch (position){
                case 0:
                case 1:
                    startActivity(new Intent(getActivity(), DoctorNearByActivity.class));
                    return;
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }
}

