package com.gigabytedevs.apps.midclan.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.adapters.ChatAdapter;
import com.gigabytedevs.apps.midclan.models.ChatModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatsFragment extends Fragment {
    private AppCompatTextView appTitle;
    private AppCompatImageView appNotify;
    private RecyclerView recyclerView;
    private ArrayList<ChatModel> list;
    private ChatAdapter adapter;

    public ChatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        appTitle = view.findViewById(R.id.app_bar);
//        appNotify = view.findViewById(R.id.notification_btn);
//        appTitle.setText(getString(R.string.nav_chat));
//        appNotify.setOnClickListener(view1 -> {
//
//        });

        recyclerView = view.findViewById(R.id.fees_list);
        list = new ArrayList<>();

        ChatModel chatModel = new ChatModel("Doctor Mark", "New Message", "12:00", R.drawable.test);
        list.add(chatModel);

        ChatModel chatModel2 = new ChatModel("Doctor Mark", "New Message", "12:00", R.drawable.test);
        list.add(chatModel2);

        adapter = new ChatAdapter(getContext(), list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
