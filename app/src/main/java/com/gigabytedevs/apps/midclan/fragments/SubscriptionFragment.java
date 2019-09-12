package com.gigabytedevs.apps.midclan.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.balysv.materialripple.MaterialRippleLayout;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.adapters.ProfileAdapter;
import com.gigabytedevs.apps.midclan.adapters.SubscriptionUserAdapter;
import com.gigabytedevs.apps.midclan.models.SubscriptionUserModel;
import com.gigabytedevs.apps.midclan.models.events_models.CountEvent;
import com.gigabytedevs.apps.midclan.utils.TinyDb;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubscriptionFragment extends Fragment{
    private RecyclerView recyclerView;
    private ArrayList<SubscriptionUserModel> list;
    private ProfileAdapter adapter;
    private TinyDb tinyDb;
    private String mRequestBody;
    private String base_url_signup;

    public SubscriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subscription, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.subscription_list);
        recyclerView.setLayoutManager(new CardSliderLayoutManager(1,700,30));

        new CardSnapHelper().attachToRecyclerView(recyclerView);
        list = new ArrayList<>();
        MaterialRippleLayout nextSession = view.findViewById(R.id.next_session);
        MaterialRippleLayout previousSession = view.findViewById(R.id.previous_session);
        tinyDb = new TinyDb(requireContext());

        nextSession.setOnClickListener(view1 -> {
            Toast.makeText(getContext(), "SignUp being done", Toast.LENGTH_SHORT).show();

            sendSignUpRequest();
        });

        previousSession.setOnClickListener(view12 -> {
            //This event bus gives an int telling the Register Activity that this is the
            // first fragment thereby changing the dots on top
            EventBus.getDefault().post(new CountEvent(3));

            UserInfoFragment userInfoFragment = new UserInfoFragment();
            FragmentTransaction userInfoInfoTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            userInfoInfoTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            userInfoInfoTransaction.replace(R.id.frame_content, userInfoFragment);
            userInfoInfoTransaction.commit();
        });
        //category gotten from designation fragment
        if (tinyDb.getString("category").equals("doctor") || tinyDb.getString("category").equals("nurse")||
                tinyDb.getString("category").equals("pharm")){
            SubscriptionUserModel subscriptionUserModel = new SubscriptionUserModel("Free","500 Naira","10 Patients", "Monthly");
            list.add(subscriptionUserModel);

            SubscriptionUserModel subscriptionUserModel1 = new SubscriptionUserModel("Pass", "300 Naira", "10 Patients", "Monthly");
            list.add(subscriptionUserModel1);

            SubscriptionUserModel subscriptionUserModel2 = new SubscriptionUserModel("Prime", "400 Naira", "50 Patients", "Monthly");
            list.add(subscriptionUserModel2);

            SubscriptionUserModel subscriptionUserModel3 = new SubscriptionUserModel("Silver", "500 Naira", "100 Patients", "Monthly");
            list.add(subscriptionUserModel3);

            SubscriptionUserModel subscriptionUserModel4 = new SubscriptionUserModel("Gold", "1000 Naira", "500 Patients", "Monthly");
            list.add(subscriptionUserModel4);

            SubscriptionUserModel subscriptionUserModel5 = new SubscriptionUserModel("Premium", "Unlimited");
            list.add(subscriptionUserModel5);

            SubscriptionUserModel subscriptionUserModel6 = new SubscriptionUserModel("Diamond", "Lifetime");
            list.add(subscriptionUserModel6);

        } else {
            SubscriptionUserModel subscriptionUserModel = new SubscriptionUserModel(getString(R.string.text_subscription_user_feature_free),
                    "0.0",
                    getString(R.string.text_subscription_user_feature_free_1),
                    getString(R.string.text_subscription_user_feature_free_2),
                    getString(R.string.text_subscription_user_feature_free_3));
            list.add(subscriptionUserModel);
            SubscriptionUserModel subscriptionUserModel1 = new SubscriptionUserModel(getString(R.string.text_subscription_user_feature_intermediate),
                    "$1,000",
                    getString(R.string.text_subscription_user_feature_free_1),
                    getString(R.string.text_subscription_user_feature_free_2),
                    getString(R.string.text_subscription_user_feature_free_3));
            list.add(subscriptionUserModel1);
            SubscriptionUserModel subscriptionUserModel2 = new SubscriptionUserModel(getString(R.string.text_subscription_user_feature_professional),
                    "5,000",
                    getString(R.string.text_subscription_user_feature_free_1),
                    getString(R.string.text_subscription_user_feature_free_2),
                    getString(R.string.text_subscription_user_feature_free_3));
            list.add(subscriptionUserModel2);
        }


        SubscriptionUserAdapter adapter = new SubscriptionUserAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);


    }

    /**
     *  This method is used to send the person's signup details to the server
     *  to sign up for the app
     */
    private void sendSignUpRequest()  {
        ///category key gotten from Designation Fragment

        switch (tinyDb.getString("category")) {
            case "patient":
                base_url_signup = getResources().getString(R.string.base_url_sign_up) + "user";
                break;
            case "doctor":
                base_url_signup = getResources().getString(R.string.base_url_sign_up) + "doctor";
                break;
            case "pharm":
                base_url_signup = getResources().getString(R.string.base_url_sign_up) + "pharmacist";
                break;
            case "labTech":
                base_url_signup = getResources().getString(R.string.base_url_sign_up) + "labtech";
                break;
        }

        JSONObject params = new JSONObject();

        //Creating the body of the request
        try {
            params.put("firstname", tinyDb.getString("firstNameUser"));
            params.put("lastname", tinyDb.getString("lastNameUser"));
            params.put("username", tinyDb.getString("userNameUser"));
            params.put("email", tinyDb.getString("emailAddress"));
            params.put("mobileno", tinyDb.getString("phoneUser"));
            params.put("password", tinyDb.getString("passwordUser"));
            params.put("address", tinyDb.getString("addressUser"));
            params.put("state", tinyDb.getString("state"));
            params.put("country", tinyDb.getString("country"));
            params.put("dob", tinyDb.getString("dob"));
            params.put("gender", tinyDb.getString("gender"));
            params.put("profilepicture",profilePicture());
            mRequestBody = params.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest signUp = new JsonObjectRequest(Request.Method.POST, base_url_signup, null, response -> {
            //Called when the server gives a valid response
            try {
                if (response.getBoolean("success")){
                    Toast.makeText(requireContext(), "User Created" , Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            //Called when the server gives an error
            try {
                String responseBody = new String(error.networkResponse.data, "utf-8");
                Log.i("Responsee", responseBody);
                JSONObject jsonObject = new JSONObject(responseBody);

                //Getting the message from the json
                String message = jsonObject.getJSONObject("error").getString("message");

                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
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

        //Queuing the data so as to be async
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(signUp);
    }

    private JSONObject profilePicture(){
        JSONObject params = new JSONObject();
        String imagePath = tinyDb.getString("image_path");
        String imageNameWithExt = imagePath.substring(imagePath.lastIndexOf("/")+1);

        String[] imageNameArray = imageNameWithExt.split(".");
        String imageName = imageNameArray[0];
        String imageExt = imageNameArray[1];

        try {
            params.put("str", tinyDb.getString("profile64String"));
            params.put("image_ext", imageExt);
            params.put("filename", imageName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }
}
