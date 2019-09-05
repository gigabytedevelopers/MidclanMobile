package com.gigabytedevs.apps.midclan.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.adapters.CommentsAdapter;
import com.gigabytedevs.apps.midclan.models.CommentsModel;
import com.gigabytedevs.apps.midclan.models.events_models.RequestDoneEvent;
import com.gigabytedevs.apps.midclan.service.SendVolleyRequest;
import com.gigabytedevs.apps.midclan.utils.TinyDb;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PostPreviewActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private AppCompatTextView postUserNameTopBar,title,userName,time,body,numberOfLikes;
    private AppCompatImageView postImage, postLikeIcon;
    private CircularImageView profileImage;
    private TinyDb tinyDb;
    private AppCompatEditText commentEditText;
    private AppCompatImageButton sendButton;
    private RecyclerView commentRecycleView;
    private ArrayList<CommentsModel> commentsList;
    private CommentsAdapter commentsAdapter;
    private ArrayList<String> responseArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_preview);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        postUserNameTopBar = findViewById(R.id.post_user_name);
        title = findViewById(R.id.user_post_preview_title);
        commentEditText = findViewById(R.id.comment_edit_text);
        sendButton = findViewById(R.id.comment_send_btn);
        commentRecycleView = findViewById(R.id.comments_list);
        postLikeIcon = findViewById(R.id.post_preview_like);
        userName = findViewById(R.id.user_name_post_preview);
        time = findViewById(R.id.user_time_post_preview);
        body = findViewById(R.id.user_text_post_preview);
        numberOfLikes = findViewById(R.id.post_preview_like_txt);
        postImage = findViewById(R.id.image_preview);
        profileImage = findViewById(R.id.user_post_preview_image);
        commentsList = new ArrayList<>();
        responseArray = new ArrayList<>();
        tinyDb = new TinyDb(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpToolBar();

        CommentsModel commentsModel = new CommentsModel();
        commentsModel.setColor(getResources().getColor(R.color.primary_text));

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentTxt = String.valueOf(commentEditText.getText());
                commentEditText.setText("");
                CommentsModel commentsModel = new CommentsModel(tinyDb.getString("profileImageUrlItem"),
                        tinyDb.getString("nameItem"),commentTxt);
                commentsList.add(commentsModel);

                commentsModel.setColor(getResources().getColor(R.color.secondary_text));

                commentsAdapter = new CommentsAdapter(PostPreviewActivity.this,commentsList);
                commentsAdapter.notifyDataSetChanged();

                LinearLayoutManager layoutManager = new LinearLayoutManager(PostPreviewActivity.this);
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                commentRecycleView.setLayoutManager(layoutManager);
                commentRecycleView.setAdapter(commentsAdapter);


                //Sending the comment to the server using the SendVolleyRequest class
                JSONObject params = new JSONObject();
                String mRequestBody = "";

                try {
                    params.put("postId", tinyDb.getString("postIdItem"));
                    params.put("comment", commentTxt);
                    mRequestBody = params.toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                responseArray = SendVolleyRequest.SendRequest(getResources().getString(R.string.base_url)+ "posts/comment/create",mRequestBody,"POST-HEAD", PostPreviewActivity.this);
            }
        });

        //Getting details from tinydb which were given by the FeedsFragment
        //Update timeline method

        postUserNameTopBar.setText(tinyDb.getString("nameItem"));
        title.setText(tinyDb.getString("titeItem"));
        userName.setText(tinyDb.getString("nameItem"));
        time.setText(tinyDb.getString("timeItem"));
        body.setText(tinyDb.getString("descriptionItem"));
        numberOfLikes.setText(tinyDb.getString("likesCountItem"));
        Glide.with(this)
                .load(tinyDb.getString("profileImageUrlItem"))
                .into(profileImage);

        Glide.with(this)
                .load(tinyDb.getString("mainImageUrlItem"))
                .into(postImage);

        getComments();


    }

    private void setUpToolBar() {
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) throw new AssertionError();

        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle(null);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(RequestDoneEvent event){
        if (event.getRequest().equals("BOOKMARK")){
            String responseString = responseArray.get(0);
            Log.i("Response", responseString);

            try {
                JSONObject responseObject = new JSONObject(responseString);
                if (responseObject.getBoolean("success")){
                   CommentsModel commentsModel = new CommentsModel();
                   commentsModel.setColor(getResources().getColor(R.color.primary_text));
                }else{
                    String error = responseObject.getJSONObject("error").getString("message");
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //This method get the comments from tinydb with key commentsItem passed by
    //the FeedsFragment
    private void getComments() {
        try {
            JSONObject responseObject = new JSONObject(tinyDb.getString("commentsItem"));
            JSONArray valuesArray = responseObject.getJSONArray("values");

            for (int i =0; i< valuesArray.length(); i++){
                JSONObject valuesObject = valuesArray.getJSONObject(i);
                String name = valuesObject.getJSONObject("nameValuePairs").getJSONObject("author").
                        getJSONObject("nameValuePairs").getString("name");

                String imageUrl = valuesObject.getJSONObject("nameValuePairs").getJSONObject("author").
                        getJSONObject("nameValuePairs").getString("imageUrl");

                String commentBody = valuesObject.getJSONObject("nameValuePairs").getString("body");

                updateCommentList(imageUrl,name,commentBody);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method updates the comment list from the json send to this activity by tinydb
     * from the feedsfragment
     * @param imageUrl this is the profile image of the commenter
     * @param name this is the name of the commenter
     * @param comment this is the comment
     */
    private void updateCommentList(String imageUrl, String name, String comment){
        CommentsModel commentsModel = new CommentsModel(imageUrl,name,comment);
        commentsList.add(commentsModel);
        commentsModel.setColor(getResources().getColor(R.color.primary_text));

        commentsAdapter = new CommentsAdapter(this, commentsList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(PostPreviewActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        commentRecycleView.setLayoutManager(layoutManager);
        commentRecycleView.setAdapter(commentsAdapter);
    }
}
