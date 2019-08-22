package com.gigabytedevs.apps.midclan.fragments;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.balysv.materialripple.MaterialRippleLayout;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.models.events_models.CountEvent;
import com.gigabytedevs.apps.midclan.utils.TinyDb;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;


/**
 * A simple {@link Fragment} subclass.
 */
public class DesignationFragment extends Fragment {
    private TinyDb tinyDb;
    public DesignationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_designation, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialRippleLayout patientRipple = view.findViewById(R.id.patient_ripple);
        MaterialRippleLayout doctorRipple = view.findViewById(R.id.doctor_ripple);
        MaterialRippleLayout nurseRipple = view.findViewById(R.id.nurse_ripple);
        MaterialRippleLayout pharmRipple = view.findViewById(R.id.pharm_ripple);
        MaterialRippleLayout labTechRipple = view.findViewById(R.id.lab_tech_ripple);
        MaterialRippleLayout hospitalRipple = view.findViewById(R.id.hospital_ripple);
        tinyDb = new TinyDb(getContext());



        patientRipple.setOnClickListener(view1 -> {
            tinyDb.putString("category", "patient");
            switchFragment();

        });

        doctorRipple.setOnClickListener(view12 -> {
            tinyDb.putString("category", "doctor");
            switchFragment();

        });

        nurseRipple.setOnClickListener(view1->{
            tinyDb.putString("category", "nurse");
            switchFragment();
        });

        hospitalRipple.setOnClickListener(view1->{
            tinyDb.putString("category", "hospital");
            switchFragment();

        });

        labTechRipple.setOnClickListener(view1->{
            tinyDb.putString("category", "labTech");
            switchFragment();
        });

        pharmRipple.setOnClickListener(view1->{
            tinyDb.putString("category", "pharm");
            switchFragment();
        });

    }

    private void switchFragment(){
        //This event bus gives an int telling the Register Activity that this is the
        // first fragment thereby changing the dots on top
        EventBus.getDefault().post(new CountEvent(2));

        UserAccountInfoFragment userAccountInfoFragment = new UserAccountInfoFragment();
        FragmentTransaction userAccountInfoTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        userAccountInfoTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        userAccountInfoTransaction.replace(R.id.frame_content, userAccountInfoFragment);
        userAccountInfoTransaction.commit();

    }



}
