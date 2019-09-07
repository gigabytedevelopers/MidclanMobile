package com.gigabytedevs.apps.midclan.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.utils.TinyDb;
import com.mikhaellopez.circularimageview.CircularImageView;

public class UserChatActivity extends AppCompatActivity {
    private CircularImageView profileImage;
    private AppCompatTextView userName;
    TinyDb tinyDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_chat);
        setUpToolBar();
        profileImage = findViewById(R.id.sender_user_profile);
        userName = findViewById(R.id.sender_user_name);
        tinyDb = new TinyDb(this);

        Glide.with(this)
                .load(tinyDb.getString("recipientImage"))
                .into(profileImage);

        userName.setText(tinyDb.getString("recipientName"));
        Toast.makeText(this, tinyDb.getString("recipientId"), Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_add_attachment:

            case R.id.nav_chat_public_chat:

            case R.id.nav_private_chat:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void setUpToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) throw new AssertionError();

        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle(null);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

}
