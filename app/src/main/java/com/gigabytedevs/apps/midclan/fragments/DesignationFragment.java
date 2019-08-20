package com.gigabytedevs.apps.midclan.fragments;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.balysv.materialripple.MaterialRippleLayout;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.activities.RegisterActivity;
import com.gigabytedevs.apps.midclan.utils.TinyDb;
import com.gigabytedevs.apps.midclan.utils.events.ButtonVisibilityEvent;
import com.gigabytedevs.apps.midclan.utils.events.ChangeFrameEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class DesignationFragment extends Fragment {
    private MaterialRippleLayout patientRipple, doctorRipple, nurseRipple,
                                    hospitalRipple,labTechRipple, pharmRipple;
    private TinyDb tinyDb;

    public DesignationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_designation, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        patientRipple = view.findViewById(R.id.patient_ripple);
        doctorRipple = view.findViewById(R.id.doctor_ripple);
        nurseRipple = view.findViewById(R.id.nurse_ripple);
        pharmRipple = view.findViewById(R.id.pharm_ripple);
        labTechRipple = view.findViewById(R.id.lab_tech_ripple);
        hospitalRipple = view.findViewById(R.id.hospital_ripple);
        tinyDb = new TinyDb(getContext());

        patientRipple.setOnClickListener(view1 -> {
            fireEventAndSaveCategory("patient");

        });

        doctorRipple.setOnClickListener(view12 -> {
            fireEventAndSaveCategory("doctor");
        });

        nurseRipple.setOnClickListener(view1->{
            fireEventAndSaveCategory("nurse");
        });

        hospitalRipple.setOnClickListener(view1->{
            fireEventAndSaveCategory("hospital");
        });

        labTechRipple.setOnClickListener(view1->{
            fireEventAndSaveCategory("lab_tech");
        });

        pharmRipple.setOnClickListener(view1->{
            fireEventAndSaveCategory("pharm");
        });

    }

    private void fireEventAndSaveCategory(String category){
        //using event bus to send a message to the Register Activity
        EventBus.getDefault().post(new ChangeFrameEvent( category));
        //save the category the user has choosen
        tinyDb.putString("category", category);
        //for the next session and previous session buttons to come into view
        EventBus.getDefault().post(new ButtonVisibilityEvent(1));
    }
}
