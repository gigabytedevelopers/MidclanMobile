package com.gigabytedevs.apps.midclan.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.balysv.materialripple.MaterialRippleLayout;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.models.events_models.CountEvent;
import com.gigabytedevs.apps.midclan.utils.TinyDb;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAccountInfoFragment extends Fragment {
    private AppCompatEditText firstName, lastName,userName,emailAddress,phone,password;
    private TinyDb tinyDb;
    private MaterialRippleLayout nextSession, previousSession;

    public UserAccountInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_account_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstName = view.findViewById(R.id.create_new_firstname);
        lastName = view.findViewById(R.id.create_new_lastname);
        userName = view.findViewById(R.id.create_new_username);
        emailAddress = view.findViewById(R.id.create_new_email_address);
        phone = view.findViewById(R.id.create_new_phone);
        password = view.findViewById(R.id.create_new_password);
        nextSession = view.findViewById(R.id.next_session);
        previousSession = view.findViewById(R.id.previous_session);
        tinyDb = new TinyDb(getContext());


        nextSession.setOnClickListener(view12 -> {
            //This event bus gives an int telling the Register Activity that this is the
            // first fragment thereby changing the dots on top
            EventBus.getDefault().post(new CountEvent(3));

                UserInfoFragment userInfoFragment = new UserInfoFragment();
                FragmentTransaction userInfoInfoTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                userInfoInfoTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                userInfoInfoTransaction.replace(R.id.frame_content, userInfoFragment);
                userInfoInfoTransaction.commit();

        });

        previousSession.setOnClickListener(view1 -> {
            //This event bus gives an int telling the Register Activity that this is the
            // first fragment thereby changing the dots on top
            EventBus.getDefault().post(new CountEvent(1));

            DesignationFragment designationFragment = new DesignationFragment();
            FragmentTransaction designationTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            designationTransaction.replace(R.id.frame_content,designationFragment);
            designationTransaction.commit();
        });

    }

}
