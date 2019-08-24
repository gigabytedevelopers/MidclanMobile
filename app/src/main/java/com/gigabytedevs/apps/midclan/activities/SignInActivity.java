package com.gigabytedevs.apps.midclan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.balysv.materialripple.MaterialRippleLayout;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.utils.TinyDb;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

public class SignInActivity extends AppCompatActivity {

    private AppCompatEditText username, password;
    private MaterialRippleLayout facebook, twitter, signIn;
    private AppCompatTextView createAccount, forgotPassword;
    String mRequestBody;

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

        signIn.setOnClickListener(view -> {
            sendSignInRequest();
        });
    }

    private void sendSignInRequest(){
        String base_url_signin = getResources().getString(R.string.base_url_sign_in) + "user";
        JSONObject params = new JSONObject();

        //Creating the body of the request
        try {
            params.put("email", String.valueOf(username.getText()).toLowerCase());
            params.put("password", String.valueOf(password.getText()).toLowerCase());
            mRequestBody = params.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Making the login request using volley library
        JsonObjectRequest signIn = new JsonObjectRequest(Request.Method.POST, base_url_signin, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //the response gotten when the request has been sent
                Toast.makeText(SignInActivity.this, "User Logged In", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Getting the error body from the server
                try {
                    String responseBody = new String(error.networkResponse.data, "utf-8");
                    JSONObject jsonObject = new JSONObject(responseBody);

                    //Getting the message from the json
                    String message = jsonObject.getJSONObject("error").getString("message");

                    Toast.makeText(SignInActivity.this, message, Toast.LENGTH_LONG).show();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }){
            @Override
            public byte[] getBody() {
                //Sending the user parameters as a body to the server

                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(signIn);
    }
}
