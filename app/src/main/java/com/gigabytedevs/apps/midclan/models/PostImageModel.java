package com.gigabytedevs.apps.midclan.models;

import android.graphics.Bitmap;
import android.net.Uri;

public class PostImageModel {
    private Bitmap imgResource;

    public PostImageModel(){

    }
    public PostImageModel(Bitmap imgResource){
        this.imgResource = imgResource;
    }

    public Bitmap getImgResource() {
        return imgResource;
    }
}
