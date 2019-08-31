package com.gigabytedevs.apps.midclan.activities;

import android.os.Bundle;
import android.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.utils.TinyDb;
import com.mikhaellopez.circularimageview.CircularImageView;


public class PostPreviewActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private AppCompatTextView postUserNameTopBar,title,userName,time,body,numberOfLikes;
    private AppCompatImageView postImage;
    private CircularImageView profileImage;
    private TinyDb tinyDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_preview);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        postUserNameTopBar = findViewById(R.id.post_user_name);
        title = findViewById(R.id.user_post_preview_title);
        userName = findViewById(R.id.user_name_post_preview);
        time = findViewById(R.id.user_time_post_preview);
        body = findViewById(R.id.user_text_post_preview);
        numberOfLikes = findViewById(R.id.post_preview_like_txt);
        postImage = findViewById(R.id.image_preview);
        profileImage = findViewById(R.id.user_post_preview_image);
        tinyDb = new TinyDb(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpToolBar();

        postUserNameTopBar.setText(tinyDb.getString("nameItem"));
        title.setText(tinyDb.getString("titeItem"));
        userName.setText(tinyDb.getString("nameItem"));
        time.setText(tinyDb.getString("timeItem"));
        body.setText(tinyDb.getString("descriptionItem"));
        numberOfLikes.setText(tinyDb.getString("likesCountItem"));
        Glide.with(this)
                .load(tinyDb.getString("profileImageUrlItem"))
                .into(profileImage);
    }

    private void setUpToolBar() {
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) throw new AssertionError();

        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle(null);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }


}
