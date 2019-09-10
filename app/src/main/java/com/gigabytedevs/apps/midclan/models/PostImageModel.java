package com.gigabytedevs.apps.midclan.models;

import android.graphics.Bitmap;
import android.net.Uri;

public class PostImageModel {
    private Bitmap imgResource;
    private String imageName;

    public PostImageModel(){

    }

    public String getImageName() {
        return imageName;
    }

    public PostImageModel(Bitmap imgResource, String imageName){
        this.imgResource = imgResource;
        this.imageName = imageName;
    }

    public Bitmap getImgResource() {
        return imgResource;
    }
}
