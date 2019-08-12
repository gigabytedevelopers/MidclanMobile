package com.gigabytedevs.apps.midclan.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.balysv.materialripple.MaterialRippleLayout;
import com.gigabytedevs.apps.midclan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DesignationFragment extends Fragment {
    private MaterialRippleLayout patients, doctors, labTechnicians, pharmacist,nurse, hospital;


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
        patients = view.findViewById(R.id.user_picker_card);
        doctors = view.findViewById(R.id.doctor_picker_card);
        labTechnicians = view.findViewById(R.id.lab_technician_picker_card);
        pharmacist = view.findViewById(R.id.pharmacist_picker_card);
        nurse = view.findViewById(R.id.nurse_picker_card);
        hospital = view.findViewById(R.id.hospital_picker_card);
    }
}
