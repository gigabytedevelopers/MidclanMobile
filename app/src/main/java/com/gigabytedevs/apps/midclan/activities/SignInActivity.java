package com.gigabytedevs.apps.midclan.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.gigabytedevs.apps.midclan.R;

public class SignInActivity extends AppCompatActivity {

    private AppCompatEditText username, password;
    private MaterialRippleLayout facebook, twitter, signIn;
    private AppCompatTextView createAccount, forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signIn = findViewById(R.id.sign_in_btn);
        facebook = findViewById(R.id.sign_in_with_fb);
        twitter = findViewById(R.id.sign_in_with_tw);
        username = findViewById(R.id.userName);
        password = findViewById(R.id.passWord);
        forgotPassword = findViewById(R.id.action_forgot_password);
        forgotPassword.setOnClickListener(view -> {

        });
        createAccount = findViewById(R.id.action_create_account);
        createAccount.setOnClickListener(view -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
        signIn.setOnClickListener(view -> {

            startActivity(new Intent(this, MainActivity.class));
        });
        facebook.setOnClickListener(view -> {

        });
        twitter.setOnClickListener(view -> {

        });
    }
}
