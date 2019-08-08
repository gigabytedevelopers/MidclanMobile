package com.gigabytedevs.apps.midclan.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.balysv.materialripple.MaterialRippleLayout;
import com.gigabytedevs.apps.midclan.R;

public class RegisterActivity extends AppCompatActivity {
    private MaterialRippleLayout backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        backBtn = findViewById(R.id.back_button_register);
        backBtn.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}