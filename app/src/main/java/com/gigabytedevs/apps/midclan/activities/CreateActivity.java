package com.gigabytedevs.apps.midclan.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.gigabytedevs.apps.midclan.R;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        AppCompatImageView closeCreate = findViewById(R.id.close_create);
        closeCreate.setOnClickListener(v -> {
            finish();
        });
    }
}
