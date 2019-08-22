package com.gigabytedevs.apps.midclan.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.balysv.materialripple.MaterialRippleLayout;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.models.events_models.CountEvent;
import com.gigabytedevs.apps.midclan.utils.TinyDb;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends Fragment {
    private AppCompatEditText address,dob;
    private AppCompatAutoCompleteTextView state, country,gender;
    private TinyDb tinyDb;
    private ArrayList<String> stateList, countryList, genderList;

    public UserInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
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
        MaterialRippleLayout nextSession = view.findViewById(R.id.next_session);
        MaterialRippleLayout previousSession = view.findViewById(R.id.previous_session);
        stateList = new ArrayList<>();
        countryList = new ArrayList<>();
        genderList = new ArrayList<>();

        //Calling the methods to populate the arrays for the autocomplete textviews
        populateCountryList();
        populateGenderList();
        populateStateList();


        //Get the saved strings from tinydb if they exist if they don't exist
        //then it will be empty
        address.setText(tinyDb.getString("addressUser"));
        state.setText(tinyDb.getString("state"));
        country.setText(tinyDb.getString("country"));
        dob.setText(tinyDb.getString("dob"));
        gender.setText(tinyDb.getString("gender"));

        //defining the adapters for the autocomplete textviews
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, stateList);
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, countryList);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, genderList);

        //setting the adapters for the autocomplete textviews
        state.setAdapter(stateAdapter);
        country.setAdapter(countryAdapter);
        gender.setAdapter(genderAdapter);

        nextSession.setOnClickListener(view12 -> {

            //This event bus gives an int telling the Register Activity that this is the
            // third fragment thereby changing the dots on top
            EventBus.getDefault().post(new CountEvent(4));

            tinyDb.putString("addressUser", String.valueOf(address.getText()));
            tinyDb.putString("state", String.valueOf(state.getText()));
            tinyDb.putString("country", country.getText().toString());
            tinyDb.putString("dob", String.valueOf(dob.getText()));
            tinyDb.putString("gender", gender.getText().toString());
            SubscriptionFragment subscriptionFragment = new SubscriptionFragment();
            FragmentTransaction subscriptionTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            subscriptionTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            subscriptionTransaction.replace(R.id.frame_content, subscriptionFragment);
            subscriptionTransaction.commit();

        });

        previousSession.setOnClickListener(view1 -> {
            //This event bus gives an int telling the Register Activity that this is the
            // third fragment thereby changing the dots on top
            EventBus.getDefault().post(new CountEvent(2));

            UserAccountInfoFragment userInfoFragment = new UserAccountInfoFragment();
            FragmentTransaction userInfoInfoTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            userInfoInfoTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            userInfoInfoTransaction.replace(R.id.frame_content, userInfoFragment);
            userInfoInfoTransaction.commit();
        });

    }

    private void populateStateList(){
        stateList.add("Edo");
        stateList.add("Delta");
    }

    private void populateCountryList(){
        countryList.add("Nigeria");
        countryList.add("Ghana");
        countryList.add("Dubai");
    }

    private void populateGenderList(){
        genderList.add("Male");
        genderList.add("Female");
    }

}
