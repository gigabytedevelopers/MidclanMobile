package com.gigabytedevs.apps.midclan.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentTransaction;
import com.balysv.materialripple.MaterialRippleLayout;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.fragments.DesignationFragment;
import com.gigabytedevs.apps.midclan.models.events_models.CountEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class RegisterActivity extends AppCompatActivity {
    private AppCompatImageView firstDotDesignation, secondDot,thirdDot,finalDot;
    public static int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MaterialRippleLayout backBtn = findViewById(R.id.back_button_register);
        firstDotDesignation = findViewById(R.id.first_dot_designation);
        secondDot = findViewById(R.id.second_dot_user_account_info);
        thirdDot = findViewById(R.id.third_dot_user_info);
        finalDot = findViewById(R.id.final_dot_user_info);
        backBtn.setOnClickListener(view -> {
            onBackPressed();
            count = 0;
        });

        DesignationFragment designationFragment = new DesignationFragment();
        FragmentTransaction designationTransaction = getSupportFragmentManager().beginTransaction();
        designationTransaction.replace(R.id.frame_content,designationFragment);
        designationTransaction.commit();
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

    /**
     * This is a method that is used by the eventbus library for events
     * @param event this param is representing the event that was passed to this method from the
     *              DesignationFragment,UserAccountInfoFragment,UserInfoFragment,SubscriptionFragment
     *              so as to know which dot to fill in the register activity
     */
    @Subscribe
    public void onEvent(CountEvent event){
        if (event.getCount() == 1) {
            firstDotDesignation.setImageResource(R.drawable.shape_round_primary);
        } else {
            firstDotDesignation.setImageResource(R.drawable.shape_round_outline_primary);
        }

        if (event.getCount() == 2){
            secondDot.setImageResource(R.drawable.shape_round_primary);
        }else {
            secondDot.setImageResource(R.drawable.shape_round_outline_primary);
        }

        if (event.getCount() == 3){
            thirdDot.setImageResource(R.drawable.shape_round_primary);
        }else {
            thirdDot.setImageResource(R.drawable.shape_round_outline_primary);
        }

        if (event.getCount() == 4){
            finalDot.setImageResource(R.drawable.shape_round_primary);
        }else {
            finalDot.setImageResource(R.drawable.shape_round_outline_primary);
        }
    }

}
