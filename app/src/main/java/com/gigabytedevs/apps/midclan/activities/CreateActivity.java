package com.gigabytedevs.apps.midclan.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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

import java.io.ByteArrayOutputStream;
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
    private ProgressBar postProgressBar;

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
        postProgressBar = findViewById(R.id.post_progress_bar);
        responseArray = new ArrayList<>();
        postImageRecycler = findViewById(R.id.picture_layout);
        imageList = new ArrayList<>();

        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postProgressBar.setVisibility(View.VISIBLE);
                //Creating the body of the request
                JSONObject params = new JSONObject();


                try {
                    params.put("title", String.valueOf(title.getText()));
                    params.put("body", String.valueOf(description.getText()));
                    params.put("postImages", postImages());
                    mRequestBody = params.toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                responseArray = SendVolleyRequest.SendRequest(getResources().getString(R.string.base_url)
                        +"posts/create",mRequestBody,"POST-HEAD", CreateActivity.this);
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
                Uri contentURI = data.getData();
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String imagePath = getRealPathFromURI(contentURI);
                String imageNameWithExt = imagePath.substring(imagePath.lastIndexOf("/")+1);

                int indexOfDash = imageNameWithExt.indexOf('.');
                String imageName = imageNameWithExt.substring(0, indexOfDash);

                Log.i("ImageName", imageName);
                updateImageList(thumbnail, imageName);

            }
        }else  if (requestCode == 1){

            try {
                Uri contentURI = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);

                String imagePath = getRealPathFromURI(contentURI);
                String imageNameWithExt = imagePath.substring(imagePath.lastIndexOf("/")+1);

                int indexOfDash = imageNameWithExt.indexOf('.');
                String imageName = imageNameWithExt.substring(0, indexOfDash);

                Log.i("ImageName", imageName);
                updateImageList(bitmap, imageName);

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(CreateActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
            }catch (NullPointerException npe){
                npe.printStackTrace();
            }
        }
    }
    public String getRealPathFromURI(Uri contentUri) {

        // can post image
        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = this.getContentResolver().query( contentUri,
                proj, // Which columns to return
                null,       // WHERE clause; which rows to return (all rows)
                null,       // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }
    private void updateImageList(Bitmap file, String imageName) {
        PostImageModel postImageModel = new PostImageModel(file, imageName);
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

    private String convertImageToBase64(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    private JSONArray postImages(){
        JSONArray imgArray = new JSONArray();
        JSONObject imgObject = new JSONObject();

        for (PostImageModel postImageModel : imageList){
            Bitmap postBitMap = postImageModel.getImgResource();

            try {
                imgObject.put("str", convertImageToBase64(postBitMap));
                imgObject.put("image_ext", "jpeg");
                imgObject.put("filename",postImageModel.getImageName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        imgArray.put(imgObject);
        return imgArray;
    }

    @Subscribe
    public void onEvent(RequestDoneEvent event){
        if (event.getRequest().equals("BOOKMARK")){
            String responseString = responseArray.get(0);
            Log.i("ResponsePost", responseString);

            try {
                JSONObject responseObject = new JSONObject(responseString);

                //If the success is true or false
                if (responseObject.getBoolean("success")){
                    postProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(this, "Post Sent Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    postProgressBar.setVisibility(View.INVISIBLE);
                    String error = responseObject.getJSONObject("error").getString("message");
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
