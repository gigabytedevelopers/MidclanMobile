package com.gigabytedevs.apps.midclan.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.adapters.ProfileAdapter;
import com.gigabytedevs.apps.midclan.adapters.SubscriptionUserAdapter;
import com.gigabytedevs.apps.midclan.models.SubscriptionUserModel;
import com.gigabytedevs.apps.midclan.models.api_models.PatientModel;
import com.gigabytedevs.apps.midclan.models.events_models.CountEvent;
import com.gigabytedevs.apps.midclan.service.PatientClient;
import com.gigabytedevs.apps.midclan.utils.TinyDb;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import swipeable.com.layoutmanager.OnItemSwiped;
import swipeable.com.layoutmanager.SwipeableLayoutManager;
import swipeable.com.layoutmanager.SwipeableTouchHelperCallback;
import swipeable.com.layoutmanager.touchelper.ItemTouchHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubscriptionFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<SubscriptionUserModel> list;
    private ProfileAdapter adapter;
    private TinyDb tinyDb;


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
//            PatientModel patientModel = new PatientModel(
//                    tinyDb.getString("firstNameUser"),
//                    tinyDb.getString("lastNameUser"),
//                    tinyDb.getString("userNameUser"),
//                    tinyDb.getString("emailAddress"),
//                    tinyDb.getString("phoneUser"),
//                    tinyDb.getString("passwordUser"),
//                    tinyDb.getString("addressUser"),
//                    tinyDb.getString("state"),
//                    tinyDb.getString("country"),
//                    tinyDb.getString("dob"),
//                    tinyDb.getString("gender")
//            );
//
//            sendSignUpRequest(patientModel);
        });

        previousSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This event bus gives an int telling the Register Activity that this is the
                // first fragment thereby changing the dots on top
                EventBus.getDefault().post(new CountEvent(3));

                UserInfoFragment userInfoFragment = new UserInfoFragment();
                FragmentTransaction userInfoInfoTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                userInfoInfoTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                userInfoInfoTransaction.replace(R.id.frame_content, userInfoFragment);
                userInfoInfoTransaction.commit();
            }
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

        }else {
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

    private void sendSignUpRequest(PatientModel patient){
        String base_url = getResources().getString(R.string.base_url_sign_up);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        PatientClient client = retrofit.create(PatientClient.class);
        Call<PatientModel> call = client.createAccount(patient);

        call.enqueue(new Callback<PatientModel>() {
            @Override
            public void onResponse(@NotNull Call<PatientModel> call, @NotNull Response<PatientModel> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), String.valueOf(response.body()), Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(@NotNull Call<PatientModel> call, @NotNull Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
