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
public class UserInfoFragment extends Fragment {
    private AppCompatEditText address,state,country,dob,gender;
    private TinyDb tinyDb;
    private MaterialRippleLayout nextSession, previousSession;

    public UserInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        address = view.findViewById(R.id.create_user_address);
        state = view.findViewById(R.id.create_user_state);
        country = view.findViewById(R.id.create_user_country);
        dob = view.findViewById(R.id.create_user_dob);
        gender = view.findViewById(R.id.create_user_gender);
        tinyDb = new TinyDb(getContext());
        nextSession = view.findViewById(R.id.next_session);
        previousSession = view.findViewById(R.id.previous_session);

        nextSession.setOnClickListener(view12 -> {

            //This event bus gives an int telling the Register Activity that this is the
            // first fragment thereby changing the dots on top
            EventBus.getDefault().post(new CountEvent(4));

            SubscriptionFragment subscriptionFragment = new SubscriptionFragment();
            FragmentTransaction subscriptionTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            subscriptionTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            subscriptionTransaction.replace(R.id.frame_content, subscriptionFragment);
            subscriptionTransaction.commit();

        });

        previousSession.setOnClickListener(view1 -> {
            //This event bus gives an int telling the Register Activity that this is the
            // first fragment thereby changing the dots on top
            EventBus.getDefault().post(new CountEvent(2));

            UserAccountInfoFragment userInfoFragment = new UserAccountInfoFragment();
            FragmentTransaction userInfoInfoTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            userInfoInfoTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            userInfoInfoTransaction.replace(R.id.frame_content, userInfoFragment);
            userInfoInfoTransaction.commit();
        });

    }

}
