package com.gigabytedevs.apps.midclan.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.adapters.PostImageAdapter;
import com.gigabytedevs.apps.midclan.models.PostImageModel;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {
    private AppCompatEditText title,description;
    private RecyclerView postImageRecycler;
    private ArrayList<PostImageModel> imageList;
    private PostImageAdapter postImageAdapter;
    private AppCompatImageView cameraUpload, galleryUpload;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS= 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        AppCompatImageView closeCreate = findViewById(R.id.close_create);
        title = findViewById(R.id.post_title);
        cameraUpload = findViewById(R.id.create_add_camera);
        galleryUpload = findViewById(R.id.create_add_image);
        description = findViewById(R.id.post_description);
        closeCreate.setOnClickListener(v -> {
            finish();
        });
        imageList = new ArrayList<>();
        postImageRecycler = findViewById(R.id.picture_layout);

        final RxPermissions rxPermissions = new RxPermissions(this);

        cameraUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkAndRequestPermissions()){
                        Toast.makeText(CreateActivity.this, "Permissions Granted", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(CreateActivity.this, "Permissions Not Granted", Toast.LENGTH_SHORT).show();

                    }


                } else {
                    // code for lollipop and pre-lollipop devices
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
}
