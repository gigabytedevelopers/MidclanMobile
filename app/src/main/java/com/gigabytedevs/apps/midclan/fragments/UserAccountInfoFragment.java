package com.gigabytedevs.apps.midclan.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.balysv.materialripple.MaterialRippleLayout;
import com.gigabytedevs.apps.midclan.R;
import com.gigabytedevs.apps.midclan.models.events_models.CountEvent;
import com.gigabytedevs.apps.midclan.utils.TinyDb;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.gigabytedevs.apps.midclan.activities.CreateActivity.REQUEST_ID_MULTIPLE_PERMISSIONS;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAccountInfoFragment extends Fragment {
    private AppCompatEditText firstName, lastName,userName,emailAddress,phone,password;
    private CircularImageView profilePic;
    private TinyDb tinyDb;
    private Uri mImageCaptureUri;

    public UserAccountInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_account_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstName = view.findViewById(R.id.create_new_firstname);
        lastName = view.findViewById(R.id.create_new_lastname);
        userName = view.findViewById(R.id.create_new_username);
        emailAddress = view.findViewById(R.id.create_new_email_address);
        phone = view.findViewById(R.id.create_new_phone);
        password = view.findViewById(R.id.create_new_password);
        profilePic = view.findViewById(R.id.profile_pic);
        MaterialRippleLayout nextSession = view.findViewById(R.id.next_session);
        MaterialRippleLayout previousSession = view.findViewById(R.id.previous_session);
        tinyDb = new TinyDb(getContext());

        //Get the saved strings from tinydb if they exist if they don't exist
        //then it will be empty
        firstName.setText(tinyDb.getString("firstNameUser"));
        lastName.setText(tinyDb.getString("lastNameUser"));
        userName.setText(tinyDb.getString("userNameUser"));
        emailAddress.setText(tinyDb.getString("emailAddress"));
        phone.setText(tinyDb.getString("phoneUser"));
        password.setText(tinyDb.getString("passwordUser"));

        profilePic.setOnClickListener(view13 -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkAndRequestPermissions()){
                    choosePhotoFromGallary();
                } else {
                    Toast.makeText(getContext(), "Permissions Not Granted", Toast.LENGTH_SHORT).show();
                }
            } else {
                // code for lollipop and pre-lollipop devices
                choosePhotoFromGallary();
            }
        });

        nextSession.setOnClickListener(view12 -> {
            //This event bus gives an int telling the Register Activity that this is the
            // first fragment thereby changing the dots on top
            EventBus.getDefault().post(new CountEvent(3));

            //save user details in tinydb
            tinyDb.putString("firstNameUser", String.valueOf(firstName.getText()));
            tinyDb.putString("lastNameUser", String.valueOf(lastName.getText()));
            tinyDb.putString("userNameUser", String.valueOf(userName.getText()));
            tinyDb.putString("emailAddress", String.valueOf(emailAddress.getText()));
            tinyDb.putString("phoneUser", String.valueOf(phone.getText()));
            tinyDb.putString("passwordUser", String.valueOf(password.getText()));

                UserInfoFragment userInfoFragment = new UserInfoFragment();
                FragmentTransaction userInfoInfoTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                userInfoInfoTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                userInfoInfoTransaction.replace(R.id.frame_content, userInfoFragment);
                userInfoInfoTransaction.commit();
        });

        previousSession.setOnClickListener(view1 -> {
            //This event bus gives an int telling the Register Activity that this is the
            // first fragment thereby changing the dots on top
            EventBus.getDefault().post(new CountEvent(1));

            DesignationFragment designationFragment = new DesignationFragment();
            FragmentTransaction designationTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            designationTransaction.replace(R.id.frame_content,designationFragment);
            designationTransaction.commit();
        });
    }

    private boolean checkAndRequestPermissions() {
        int camera = ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.CAMERA);
        int write = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
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
            ActivityCompat.requestPermissions(requireActivity(), listPermissionsNeeded.toArray(new String[0]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    private void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        galleryIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,mImageCaptureUri);
        startActivityForResult(galleryIntent, 1);
    }

    private String convertImageToBase64(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                try {
                    assert data != null;
                    Uri contentURI = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), contentURI);
                    profilePic.setImageBitmap(bitmap);
                    String imageString = convertImageToBase64(bitmap);
                    tinyDb.putString("profile64String", imageString);

                    //Path to the image so it can be used to get the name
                    String pathToImage = mImageCaptureUri.getPath();
                    tinyDb.putString("image_path", pathToImage);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(requireContext(), "Failed!", Toast.LENGTH_SHORT).show();
                } catch (NullPointerException npe) {
                    npe.printStackTrace();
                }
            }
        }
    }
}