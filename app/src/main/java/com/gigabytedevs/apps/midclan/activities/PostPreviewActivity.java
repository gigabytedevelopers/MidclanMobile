package com.gigabytedevs.apps.midclan.activities;

import android.os.Bundle;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.gigabytedevs.apps.midclan.R;

public class PostPreviewActivity extends AppCompatActivity {
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_preview);
    }
}
