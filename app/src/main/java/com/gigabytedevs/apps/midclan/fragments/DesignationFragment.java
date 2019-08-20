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
    private MaterialRippleLayout patientRipple;
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
        tinyDb = new TinyDb(getContext());

        patientRipple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //using event bus to send a message to the Register Activity
                EventBus.getDefault().post(new ChangeFrameEvent( "patient"));
                //save the category the user has choosen
                tinyDb.putString("category", "patient");
                EventBus.getDefault().post(new ButtonVisibilityEvent(1));
            }
        });

    }
}
