package com.gigabytedevs.apps.midclan.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentTransaction;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.fragments.DiscoverFragment;
import com.gigabytedevs.apps.midclan.fragments.FeedsFragment;
import com.gigabytedevs.apps.midclan.fragments.ChatsFragment;
import com.gigabytedevs.apps.midclan.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private AppCompatTextView title;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_feeds:
                    FeedsFragment feedsFragment = new FeedsFragment();
                    FragmentTransaction feedsTransaction = getSupportFragmentManager().beginTransaction();
                    feedsTransaction.replace(R.id.mainContent,feedsFragment);
                    feedsTransaction.commit();
                    return true;
                case R.id.nav_discover:
                    DiscoverFragment discoverFragment = new DiscoverFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.mainContent,discoverFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.nav_chats:
                    ChatsFragment chatsFragment = new ChatsFragment();
                    FragmentTransaction notificationTransaction = getSupportFragmentManager().beginTransaction();
                    notificationTransaction.replace(R.id.mainContent, chatsFragment);
                    notificationTransaction.commit();
                    return true;
                case R.id.nav_profile:
                    ProfileFragment profileFragment = new ProfileFragment();
                    FragmentTransaction profileTransaction = getSupportFragmentManager().beginTransaction();
                    profileTransaction.replace(R.id.mainContent,profileFragment);
                    profileTransaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FeedsFragment feedsFragment = new FeedsFragment();
        FragmentTransaction feedsTransaction = getSupportFragmentManager().beginTransaction();
        feedsTransaction.replace(R.id.mainContent,feedsFragment);
        feedsTransaction.commit();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
