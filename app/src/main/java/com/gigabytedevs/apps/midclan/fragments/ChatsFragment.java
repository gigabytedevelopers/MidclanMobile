package com.gigabytedevs.apps.midclan.fragments;


import android.content.Intent;
import android.os.Bundle;
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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.activities.NotificationActivity;
import com.gigabytedevs.apps.midclan.activities.UserChatActivity;
import com.gigabytedevs.apps.midclan.adapters.ChatAdapter;
import com.gigabytedevs.apps.midclan.models.ChatModel;
import com.gigabytedevs.apps.midclan.models.events_models.RequestDoneEvent;
import com.gigabytedevs.apps.midclan.service.SendVolleyRequest;
import com.gigabytedevs.apps.midclan.utils.TinyDb;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<ChatModel> list;
    private ChatAdapter adapter;
    private ArrayList<String> responseArray;
    private TinyDb tinyDb;

    public ChatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatTextView appTitle = view.findViewById(R.id.app_bar);
        AppCompatImageView appNotify = view.findViewById(R.id.notification_btn);
        appTitle.setText(getString(R.string.nav_chat));
        appNotify.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), NotificationActivity.class));
        });

        recyclerView = view.findViewById(R.id.fees_list);
        list = new ArrayList<>();
        tinyDb = new TinyDb(requireContext());

        responseArray = new ArrayList<>();

        responseArray = SendVolleyRequest.SendRequest(getResources().getString(R.string.base_url)+"users/all","","GET",requireContext());
//        ChatModel chatModel = new ChatModel("Doctor Mark", "New Message", "12:00", R.drawable.test);
//        list.add(chatModel);
//
//        ChatModel chatModel2 = new ChatModel("Doctor Mark", "New Message", "12:00", R.drawable.test);
//        list.add(chatModel2);
//
//        adapter = new ChatAdapter(getContext(), list, (view1, position)->{
//            startActivity(new Intent(getActivity(), UserChatActivity.class));
//        });

//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL));
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
        if (event.getRequest().equals("TIMELINE")){
            String responseString = responseArray.get(0);
            Log.i("ResponseUsers", responseString);
            try {
                JSONObject responseObject = new JSONObject(responseString);

                if (responseObject.getBoolean("success")){
                    JSONArray jsonArray = responseObject.getJSONObject("payload").getJSONArray("data");
                    for (int i =0; i< jsonArray.length(); i++){
                        JSONObject dataObject = jsonArray.getJSONObject(i);
                        String firstName = dataObject.getString("firstname");
                        String lastName = dataObject.getString("lastname");
                        String profilePic = dataObject.getString("profilepicture");
                        String id = dataObject.getString("_id");
                        String name = lastName + " " + firstName;


                        updateChatList(id,name, "" , "", profilePic);

                    }
                }else {
                    String error = responseObject.getJSONObject("error").getString("message");
                    Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateChatList(String id,String senderName, String senderText, String senderTime, String senderImageUrl){
        ChatModel chatModel = new ChatModel(id,senderName,senderText,senderTime,senderImageUrl);
        list.add(chatModel);

        adapter = new ChatAdapter(getContext(), list, (view1, position)->{
            try {
                JSONArray itemJson = new JSONArray(getDetailsForChat(list,position));

                for (int i = 0; i<itemJson.length(); i++){
                    JSONObject jsonObject = itemJson.getJSONObject(i);
                    String idItem = jsonObject.getString("id");
                    String name = jsonObject.getString("senderName");
                    String imageUrl = jsonObject.getString("senderImageUrl");

                    tinyDb.putString("recipientName", name);
                    tinyDb.putString("recipientId", idItem);
                    tinyDb.putString("recipientImage", imageUrl);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(getActivity(), UserChatActivity.class));
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private String getDetailsForChat(List<ChatModel> chatList, int position){
        List<ChatModel> chatModels = new ArrayList<>();
        chatModels.add(chatList.get(position));

        Gson gson = new Gson();
        return gson.toJson(chatModels);
    }
}
