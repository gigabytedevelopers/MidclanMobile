package com.gigabytedevs.apps.midclan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.gigabytedevs.apps.midclan.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);
        ProgressBar splashProgressBar = findViewById(R.id.splash_progress_bar);
        AppCompatImageView splashLogo = findViewById(R.id.logo_splash_id);
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }
}
