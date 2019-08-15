package com.gigabytedevs.apps.midclan.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentTransaction;

import com.balysv.materialripple.MaterialRippleLayout;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.fragments.DesignationFragment;
import com.gigabytedevs.apps.midclan.fragments.UserAccountInfoFragment;

public class RegisterActivity extends AppCompatActivity {
    private MaterialRippleLayout backBtn, nextSession, previousSession;
    private AppCompatImageView firstDotDesignation, secondDot,thirdDot,fourthDot;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        backBtn = findViewById(R.id.back_button_register);
        firstDotDesignation = findViewById(R.id.first_dot_designation);
        secondDot = findViewById(R.id.second_dot_user_account_info);
        thirdDot = findViewById(R.id.third_dot_user_info);
        fourthDot = findViewById(R.id.final_dot_subscription);
        nextSession = findViewById(R.id.next_session);
        previousSession = findViewById(R.id.previous_session);
        backBtn.setOnClickListener(view -> {
            onBackPressed();
        });

        nextSession.setOnClickListener(view -> {
          switchFragments("next");

        });

        previousSession.setOnClickListener(view -> {
            switchFragments("previous");
        });

        DesignationFragment designationFragment = new DesignationFragment();
        FragmentTransaction designationTransaction = getSupportFragmentManager().beginTransaction();
        designationTransaction.replace(R.id.frame_content,designationFragment);
        designationTransaction.commit();


    }


    private void switchFragments(String whichButton){
        if (whichButton.equals("next")){
            if (count <= 4 ){
                count++;
            }

            switchDots();

            if (count == 0){
                DesignationFragment designationFragment = new DesignationFragment();
                FragmentTransaction designationTransaction = getSupportFragmentManager().beginTransaction();
                designationTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                designationTransaction.replace(R.id.frame_content,designationFragment);
                designationTransaction.commit();
                previousSession.setVisibility(View.INVISIBLE);

            }else if(count == 1){
                UserAccountInfoFragment userAccountInfoFragment = new UserAccountInfoFragment();
                FragmentTransaction userAccountInfoTransaction = getSupportFragmentManager().beginTransaction();
                userAccountInfoTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                userAccountInfoTransaction.replace(R.id.frame_content, userAccountInfoFragment);
                userAccountInfoTransaction.commit();
                previousSession.setVisibility(View.VISIBLE);
            }
        }else{
            if (count > 0 ){
                count--;
            }

            if (count == 0){
                DesignationFragment designationFragment = new DesignationFragment();
                FragmentTransaction designationTransaction = getSupportFragmentManager().beginTransaction();
                designationTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                designationTransaction.replace(R.id.frame_content,designationFragment);
                designationTransaction.commit();
            }else if(count == 1){
                UserAccountInfoFragment userAccountInfoFragment = new UserAccountInfoFragment();
                FragmentTransaction userAccountInfoTransaction = getSupportFragmentManager().beginTransaction();
                userAccountInfoTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                userAccountInfoTransaction.replace(R.id.frame_content, userAccountInfoFragment);
                userAccountInfoTransaction.commit();
                previousSession.setVisibility(View.VISIBLE);
            }
        }

    }

    private void switchDots() {
        if (count == 0) {
            firstDotDesignation.setImageResource(R.drawable.shape_round_primary);
        } else {
            firstDotDesignation.setImageResource(R.drawable.shape_round_outline_primary);
        }

        if (count == 1){
            secondDot.setImageResource(R.drawable.shape_round_primary);
        }else {
            secondDot.setImageResource(R.drawable.shape_round_outline_primary);
        }

        if (count == 2){
            thirdDot.setImageResource(R.drawable.shape_round_primary);
        }else {
            thirdDot.setImageResource(R.drawable.shape_round_outline_primary);
        }

        if (count == 3){
            fourthDot.setImageResource(R.drawable.shape_round_primary);
        }else {
            fourthDot.setImageResource(R.drawable.shape_round_outline_primary);
        }
    }
}
