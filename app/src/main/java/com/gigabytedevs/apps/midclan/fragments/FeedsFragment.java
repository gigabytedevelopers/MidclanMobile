package com.gigabytedevs.apps.midclan.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.activities.MainActivity;
import com.gigabytedevs.apps.midclan.activities.NotificationActivity;
import com.gigabytedevs.apps.midclan.activities.PostPreviewActivity;
import com.gigabytedevs.apps.midclan.activities.SignInActivity;
import com.gigabytedevs.apps.midclan.adapters.TimelineAdapter;
import com.gigabytedevs.apps.midclan.models.TimelineModel;
import com.gigabytedevs.apps.midclan.models.events_models.RequestDoneEvent;
import com.gigabytedevs.apps.midclan.service.SendVolleyRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedsFragment extends Fragment {
    private AppCompatTextView appTitle;
    private AppCompatImageView appNotify;
    private RecyclerView recyclerView;
    private ArrayList<TimelineModel> list;
    private TimelineAdapter adapter;
    private ArrayList<String> responseArray;
    private Bitmap decodedByte;

    public FeedsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feeds, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appTitle = view.findViewById(R.id.app_bar);
        appNotify = view.findViewById(R.id.notification_btn);
        appTitle.setText(getString(R.string.nav_feeds));
        appNotify.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), NotificationActivity.class));
        });

        recyclerView = view.findViewById(R.id.feeds_list);
        list = new ArrayList<>();

        responseArray = new ArrayList<>();

        responseArray = SendVolleyRequest.SendRequest(
                getResources().getString(R.string.base_url) + "posts/all",
                "",
                "GET",
                requireContext()
        );

//        TimelineModel timelineModel = new TimelineModel(R.drawable.img_plant_9,getResources().getString(R.string.dummy_title),getResources().getString(R.string.dummy_text),"dennisrichtie","11:00pm",R.drawable.test);
//        list.add(timelineModel);
//
//        TimelineModel timelineModel2 = new TimelineModel(R.drawable.test,getResources().getString(R.string.dummy_title),getResources().getString(R.string.dummy_text),"mezueceejay","Today",R.drawable.test);
//        list.add(timelineModel2);

//        adapter = new TimelineAdapter(getContext(), list, ((view1, position) -> {
//            switch (position){
//                case 0:
//                    startActivity(new Intent(getActivity(), PostPreviewActivity.class));
//                    return;
//            }
//        }));
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(RequestDoneEvent event){
        String responseString = responseArray.get(0);
        Log.i("Response", responseString);

        try {
            JSONObject responseObject = new JSONObject(responseString);

            //If the success is true or false
            if (responseObject.getBoolean("success")){
                JSONArray jsonArray = responseObject.getJSONObject("payload").getJSONArray("data");

                //Getting the data object
                for (int i =0; i< jsonArray.length(); i++){
                    JSONObject dataObject = jsonArray.getJSONObject(i);
                    Log.i("DataObject", dataObject.toString());

                    String postId = dataObject.getString("_id");
                    String profileUrl = dataObject.getJSONObject("author").getString("imageUrl");
                    String name = dataObject.getJSONObject("author").getString("name");
                    String title = dataObject.getString("title");
                    String body = dataObject.getString("body");


                    JSONArray mainImageArray = dataObject.getJSONArray("postImages");
                    for (int j =0; j < mainImageArray.length(); j++ ){
                        String postImage = mainImageArray.getString(0);
                        Log.i("PostImage", postImage);
                        byte[] decodedString = Base64.decode(postImage, Base64.DEFAULT);
                        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    }

                    updateTimeLine(postId,decodedByte,title,body,name,"11:00",profileUrl);
                }

            }else {

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateTimeLine(String postId,Bitmap mainImageBitmap, String title, String description, String name, String time, String profileImageUrl){

        TimelineModel timelineModel = new TimelineModel(postId,mainImageBitmap,title,description,name,time,profileImageUrl);
        list.add(timelineModel);

        adapter = new TimelineAdapter(getContext(), list, ((view1, position) -> {
            switch (position){
                case 0:
                    startActivity(new Intent(getActivity(), PostPreviewActivity.class));
                    return;
            }
        }));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
