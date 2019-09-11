package com.gigabytedevs.apps.midclan.activities;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.adapters.DoctorNearbyAdapter;
import com.gigabytedevs.apps.midclan.fragments.DoctorNearbyInfoFragment;
import com.gigabytedevs.apps.midclan.models.discover_model.DoctorNearByModel;

import java.util.ArrayList;

public class DoctorNearByActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<DoctorNearByModel> list;
    DoctorNearbyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_near_by);
        setUpToolBar();
        recyclerView = findViewById(R.id.doctor_nearby_list);
        list = new ArrayList<>();

        DoctorNearByModel doctorNearByModel = new DoctorNearByModel(R.drawable.photo_male_2,3.5f, "Dr Charles","0 miles away", "location");
        list.add(doctorNearByModel);

        DoctorNearByModel doctorNearByModel1 = new DoctorNearByModel(R.drawable.photo_male_7, 4.5f,"Dr James","20 miles away", "location");
        list.add(doctorNearByModel1);

        DoctorNearByModel doctorNearByModel2 = new DoctorNearByModel(R.drawable.photo_female_7, 5.0f,"Dr Sarah","30 miles away", "location");
        list.add(doctorNearByModel2);

        DoctorNearByModel doctorNearByModel3 = new DoctorNearByModel(R.drawable.photo_male_3, 3.5f,"Dr Georgy","50 miles away", "location");
        list.add(doctorNearByModel3);

        adapter = new DoctorNearbyAdapter(this, list, (view1, position)->{
            switch (position){
                case 0:
                    DoctorNearbyInfoFragment fragment = new DoctorNearbyInfoFragment();
                    fragment.show(getSupportFragmentManager(), fragment.getTag());
                case 1:
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private void setUpToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) throw new AssertionError();

        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle(R.string.text_discover_doctor);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}