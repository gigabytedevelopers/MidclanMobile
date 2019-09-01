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
import com.gigabytedevs.apps.midclan.utils.TinyDb;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedsFragment extends Fragment {
    private AppCompatTextView appTitle;
    private AppCompatImageView appNotify;
    private RecyclerView recyclerView;
    public static ArrayList<TimelineModel> list;
    private TimelineAdapter adapter;
    public static ArrayList<String> responseArray;
    private Bitmap decodedByte;
    private TinyDb tinyDb;

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
        tinyDb = new TinyDb(requireContext());
        appNotify.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), NotificationActivity.class));
        });

        recyclerView = view.findViewById(R.id.feeds_list);
        list = new ArrayList<>();
//
        responseArray = new ArrayList<>();

//        responseArray = SendVolleyRequest.SendRequest(
//                getResources().getString(R.string.base_url) + "posts/all",
//                "",
//                "GET",
//                requireContext()
//        );

        TimelineModel timelineModel = new TimelineModel(R.drawable.img_plant_9,getResources().getString(R.string.dummy_title),getResources().getString(R.string.dummy_text),"dennisrichtie","11:00pm",R.drawable.test);
        list.add(timelineModel);

        TimelineModel timelineModel2 = new TimelineModel(R.drawable.test,getResources().getString(R.string.dummy_title),getResources().getString(R.string.dummy_text),"mezueceejay","Today",R.drawable.test);
        list.add(timelineModel2);

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

    //Registering the eventbus library
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    //Unregistering the eventbus library
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * This event method is gotten from the SendVolleyRequest class after a success response or an
     * error response has been gotten, the code in this method gets the first String from the responeArray
     * global variable and converts it to a JSONObject so as to do something depending on whether success(From the response)
     * is false or not
     * @param event
     */
    @Subscribe
    public void onEvent(RequestDoneEvent event){
        if (event.getRequest().equals("TIMELINE")){
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

                        //Getting some data from the data object
                        String postId = dataObject.getString("_id");
                        String profileUrl = dataObject.getJSONObject("author").getString("imageUrl");
                        String name = dataObject.getJSONObject("author").getString("name");
                        String title = dataObject.getString("title");
                        String body = dataObject.getString("body");
                        int likeCount = dataObject.getJSONObject("meta").getInt("likesCount");
                        String likesCountString = String.valueOf(likeCount);


                        //Getting the main images from the imagearray
//                        JSONArray mainImageArray = dataObject.getJSONArray("postImages");
//                        for (int j =0; j < mainImageArray.length(); j++ ){
//                            String postImage = mainImageArray.getString(0);
//                            Log.i("PostImage", postImage);
//                            byte[] decodedString = Base64.decode(postImage, Base64.DEFAULT);
//                            decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                        }

                        //Method that updates the timeline list
                        updateTimeLine(postId,profileUrl,title,body,name,"11:00",profileUrl, likesCountString);
                    }

                }else {
                    String error = responseObject.getJSONObject("error").getString("message");
                    Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (event.getRequest().equals("BOOKMARK")){
            String responseString = responseArray.get(0);
            Log.i("Response BookMark", responseString);
        }

    }

    /**
     *
     * @param postId this is the id for the post gotten from the server which is put here by the onEvent Method with param
     *               of RequestDone Event
     *
     * @param mainImageUrl this is the main Image for the post gotten from the server which is put here by the onEvent Method with param
     *      *               of RequestDone Event
     *
     * @param title this is the title for the post gotten from the server which is put here by the onEvent Method with param
     *      *               of RequestDone Event
     *
     * @param description this is the description for the post gotten from the server which is put here by the onEvent Method with param
     *      *               of RequestDone Event
     *
     * @param name this is the name for the post gotten from the server which is put here by the onEvent Method with param
     *      *               of RequestDone Event
     *
     * @param time this is the time for the post gotten from the server which is put here by the onEvent Method with param
     *      *               of RequestDone Event
     *
     * @param profileImageUrl this is the profile image url for the post gotten from the server which is put here by the onEvent Method with param
     *      *               of RequestDone Event
     */
    private void updateTimeLine(String postId,String mainImageUrl, String title, String description, String name, String time, String profileImageUrl, String likeCount){

        TimelineModel timelineModel = new TimelineModel(postId,mainImageUrl,title,description,name,time,profileImageUrl, likeCount);
        list.add(timelineModel);

        adapter = new TimelineAdapter(getContext(), list, ((view1, position) -> {
//            try {
//                JSONArray itemJson = new JSONArray(getDetailsForTimeline(list,position));
//
//                for (int i =0; i< itemJson.length(); i++){
//                    JSONObject itemObject = itemJson.getJSONObject(i);
//                    String titleItem = itemObject.getString("title");
//                    String descriptionItem = itemObject.getString("description");
//                    String nameItem = itemObject.getString("name");
//                    String timeItem = itemObject.getString("time");
//                    String likesCountItem = itemObject.getString("likesCount");
//                    String profileImageUrlItem = itemObject.getString("profileImageUrl");
//                    String mainImageUrlItem = itemObject.getString("mainImageUrl");
//
//                    tinyDb.putString("titleItem", titleItem);
//                    tinyDb.putString("descriptionItem", descriptionItem);
//                    tinyDb.putString("nameItem", nameItem);
//                    tinyDb.putString("timeItem", timeItem);
//                    tinyDb.putString("likesCountItem", likesCountItem);
//                    tinyDb.putString("profileImageUrlItem", profileImageUrlItem);
//                    tinyDb.putString("mainImageUrlItem", mainImageUrlItem);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            startActivity(new Intent(requireContext(),PostPreviewActivity.class));
        }));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * This method takes hold of the position on the recycler view that was clicked
     * and converts that dynamic type to a json array
     * @param timeLineList This is the whole list
     * @param position position that was clicked
     * @return gives back the jsonarray
     */
    private String getDetailsForTimeline(List<TimelineModel> timeLineList, int position){
        List<TimelineModel> timelineModels = new ArrayList<>();
        timelineModels.add(timeLineList.get(position));

        Gson gson = new Gson();
        return gson.toJson(timelineModels);
    }
}
