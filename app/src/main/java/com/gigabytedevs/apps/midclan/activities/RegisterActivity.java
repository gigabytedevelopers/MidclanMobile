package com.gigabytedevs.apps.midclan.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentTransaction;

import com.balysv.materialripple.MaterialRippleLayout;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.fragments.DesignationFragment;
import com.gigabytedevs.apps.midclan.fragments.SubscriptionFragment;
import com.gigabytedevs.apps.midclan.fragments.UserAccountInfoFragment;
import com.gigabytedevs.apps.midclan.fragments.UserInfoFragment;
import com.gigabytedevs.apps.midclan.utils.TinyDb;
import com.gigabytedevs.apps.midclan.utils.events.ButtonVisibilityEvent;
import com.gigabytedevs.apps.midclan.utils.events.ChangeFrameEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class RegisterActivity extends AppCompatActivity {
    private MaterialRippleLayout backBtn, nextSession, previousSession;
    private AppCompatImageView firstDotDesignation, secondDot,thirdDot;
    private AppCompatTextView nextSessionText;
    public static int count = 0;
    private TinyDb tinyDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        backBtn = findViewById(R.id.back_button_register);
        firstDotDesignation = findViewById(R.id.first_dot_designation);
        secondDot = findViewById(R.id.second_dot_user_account_info);
        thirdDot = findViewById(R.id.third_dot_user_info);
        nextSession = findViewById(R.id.next_session);
        nextSessionText = findViewById(R.id.next_session_text);
        previousSession = findViewById(R.id.previous_session);
        tinyDb = new TinyDb(this);
        backBtn.setOnClickListener(view -> {
            onBackPressed();
            count = 0;
        });

        DesignationFragment designationFragment = new DesignationFragment();
        FragmentTransaction designationTransaction = getSupportFragmentManager().beginTransaction();
        designationTransaction.replace(R.id.frame_content,designationFragment);
        designationTransaction.commit();

        nextSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //category gotten from the designation fragment
                switchFragments(tinyDb.getString("category"), 1);
            }
        });

        previousSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragments(tinyDb.getString("category"), 0);
            }
        });
    }

    /**
     *
     * @param whichAccount this is the string that holds which account or cateory the user has selected
     * @param whichClicked this is the int which determines if the next button was clicked or the previous
     *                     was clicked 1 representing that the next button was clicked and 0 representing
     *                     that the previous button was clicked
     */
    private void switchFragments(String whichAccount, int whichClicked){
        Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
        if (whichAccount.equals("patient")){
            if (count <= 3 && whichClicked == 1){
                count++;
            }else if (whichClicked == 0){
                count--;
            }

            switchDots();

            if (count == 0){
                DesignationFragment designationFragment = new DesignationFragment();
                FragmentTransaction designationTransaction = getSupportFragmentManager().beginTransaction();
                designationTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                designationTransaction.replace(R.id.frame_content,designationFragment);
                designationTransaction.commit();
                nextSession.setVisibility(View.INVISIBLE);
                previousSession.setVisibility(View.INVISIBLE);
            }else if(count == 1){
                UserAccountInfoFragment userAccountInfoFragment = new UserAccountInfoFragment();
                FragmentTransaction userAccountInfoTransaction = getSupportFragmentManager().beginTransaction();
                userAccountInfoTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                userAccountInfoTransaction.replace(R.id.frame_content, userAccountInfoFragment);
                userAccountInfoTransaction.commit();
                firstDotDesignation.setVisibility(View.VISIBLE);
                secondDot.setVisibility(View.VISIBLE);
                thirdDot.setVisibility(View.VISIBLE);
                previousSession.setVisibility(View.INVISIBLE);
                nextSessionText.setText(getString(R.string.action_continue));
            }else if(count == 2){
                UserInfoFragment userInfoFragment = new UserInfoFragment();
                FragmentTransaction userInfoInfoTransaction = getSupportFragmentManager().beginTransaction();
                userInfoInfoTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                userInfoInfoTransaction.replace(R.id.frame_content, userInfoFragment);
                userInfoInfoTransaction.commit();
                previousSession.setVisibility(View.VISIBLE);
                nextSessionText.setText(getString(R.string.action_continue));
            }else if(count == 3) {
                SubscriptionFragment subscriptionFragment = new SubscriptionFragment();
                FragmentTransaction subscriptionTransaction = getSupportFragmentManager().beginTransaction();
                subscriptionTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                subscriptionTransaction.replace(R.id.frame_content, subscriptionFragment);
                subscriptionTransaction.commit();
                previousSession.setVisibility(View.VISIBLE);
                nextSessionText.setText(getString(R.string.action_finish));
            }
        }

    }

    /**
     * The switchDots method is for switching the dots depending on the parameter whichclicked
     * if it was the next button or the previous button
     *
     */
    private void switchDots() {
        if (count == 1) {
            firstDotDesignation.setImageResource(R.drawable.shape_round_primary);
        } else {
            firstDotDesignation.setImageResource(R.drawable.shape_round_outline_primary);
        }

        if (count == 2){
            secondDot.setImageResource(R.drawable.shape_round_primary);
        }else {
            secondDot.setImageResource(R.drawable.shape_round_outline_primary);
        }

        if (count == 3){
            thirdDot.setImageResource(R.drawable.shape_round_primary);
        }else {
            thirdDot.setImageResource(R.drawable.shape_round_outline_primary);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(ButtonVisibilityEvent event){
        if (event.getVisible() == 0){
            nextSession.setVisibility(View.INVISIBLE);
            previousSession.setVisibility(View.INVISIBLE);
        }else {
            nextSession.setVisibility(View.VISIBLE);
            previousSession.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe
    public void onEvent(ChangeFrameEvent event){
        if (event.getChoose().equals("patient")){
            count++;
            Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
            UserAccountInfoFragment userAccountInfoFragment = new UserAccountInfoFragment();
            FragmentTransaction userAccountInfoTransaction = getSupportFragmentManager().beginTransaction();
            userAccountInfoTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            userAccountInfoTransaction.replace(R.id.frame_content, userAccountInfoFragment);
            userAccountInfoTransaction.commit();
        }
    }

}
