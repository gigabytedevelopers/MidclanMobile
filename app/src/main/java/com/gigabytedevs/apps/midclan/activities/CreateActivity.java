package com.gigabytedevs.apps.midclan.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.adapters.PostImageAdapter;
import com.gigabytedevs.apps.midclan.models.PostImageModel;
import com.gigabytedevs.apps.midclan.models.events_models.RequestDoneEvent;
import com.gigabytedevs.apps.midclan.service.SendVolleyRequest;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {
    private AppCompatEditText title,description;
    private RecyclerView postImageRecycler;
    private ArrayList<PostImageModel> imageList;
    private PostImageAdapter postImageAdapter;
    private AppCompatImageView cameraUpload, galleryUpload;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS= 7;
    private AppCompatTextView createPost;
    private ArrayList<String> responseArray;
    String mRequestBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        AppCompatImageView closeCreate = findViewById(R.id.close_create);
        title = findViewById(R.id.post_title);
        cameraUpload = findViewById(R.id.create_add_camera);
        galleryUpload = findViewById(R.id.create_add_image);
        description = findViewById(R.id.post_description);
        createPost = findViewById(R.id.create_post);
        responseArray = new ArrayList<>();
        postImageRecycler = findViewById(R.id.picture_layout);
        imageList = new ArrayList<>();

        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating the body of the request
                JSONObject params = new JSONObject();
                JSONArray arrayParams = new JSONArray();

                for (int i = 0; i< imageList.size(); i++){
                    PostImageModel postImageModel = new PostImageModel();
                    System.out.println("Post Resource"+postImageModel.getImgResource());
                    JSONObject jsonObject = new JSONObject();

                }

                try {
                    params.put("title", String.valueOf(title.getText()));
                    params.put("body", String.valueOf(description));
                    mRequestBody = params.toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                responseArray = SendVolleyRequest.SendRequest(getResources().getString(R.string.base_url)
//                        +"posts/create",mRequestBody,"POST-HEAD", CreateActivity.this);
            }
        });

        closeCreate.setOnClickListener(v -> {
            finish();
        });
        imageList = new ArrayList<>();
        postImageRecycler = findViewById(R.id.picture_layout);

        cameraUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkAndRequestPermissions()){
                       takePicture();
                    }else {
                        Toast.makeText(CreateActivity.this, "Permissions Not Granted", Toast.LENGTH_SHORT).show();

                    }


                } else {
                    // code for lollipop and pre-lollipop devices
                    takePicture();
                }
            }
        });

        galleryUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkAndRequestPermissions()){
                        choosePhotoFromGallary();
                    }else {
                        Toast.makeText(CreateActivity.this, "Permissions Not Granted", Toast.LENGTH_SHORT).show();

                    }


                } else {
                    // code for lollipop and pre-lollipop devices
                    choosePhotoFromGallary();
                }
            }
        });



//        PostImageModel postImageModel = new PostImageModel(R.drawable.photo_male_2);
//        imageList.add(postImageModel);
//
//        PostImageModel postImageModel1 = new PostImageModel(R.drawable.photo_male_3);
//        imageList.add(postImageModel1);
//
//        postImageAdapter = new PostImageAdapter(CreateActivity.this, imageList);
//        LinearLayoutManager layoutManager
//                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        postImageRecycler.setAdapter(postImageAdapter);
//        postImageRecycler.setLayoutManager(layoutManager);
    }

    private boolean checkAndRequestPermissions() {
        int camera = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        int write = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (write != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (read != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    private void takePicture(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);
    }

    private void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                updateImageList(thumbnail);
            }
        }else  if (requestCode == 1){

            try {
                Uri contentURI = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);

                updateImageList(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(CreateActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
            }catch (NullPointerException npe){
                npe.printStackTrace();
            }
        }
    }

    private void updateImageList(Bitmap file) {
        PostImageModel postImageModel = new PostImageModel(file);
        imageList.add(postImageModel);

        postImageRecycler.setVisibility(View.VISIBLE);
        postImageAdapter = new PostImageAdapter(CreateActivity.this, imageList);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        postImageRecycler.setAdapter(postImageAdapter);
        postImageRecycler.setLayoutManager(layoutManager);
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
            Log.i("ResponsePost", responseString);
        }
    }
}
