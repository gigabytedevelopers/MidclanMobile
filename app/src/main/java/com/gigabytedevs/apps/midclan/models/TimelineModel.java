package com.gigabytedevs.apps.midclan.models;

import android.graphics.Bitmap;

public class TimelineModel {
    public int getMainImageResource() {
        return mainImageResource;
    }

    public void setMainImageResource(int mainImageResource) {
        this.mainImageResource = mainImageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    private int mainImageResource;
    private String title;
    private String description;
    private String name;
    private String time;
    private int profileImage;
    private String profileImageUrl;
    private Bitmap mainImageBitmap;
    private String postId;

    public TimelineModel(int mainImageResource, String title, String description, String name, String time, int profileImage){
        this.mainImageResource = mainImageResource;
        this.title = title;
        this.description = description;
        this.name = name;
        this.time = time;
        this.profileImage = profileImage;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Bitmap getMainImageBitmap() {
        return mainImageBitmap;
    }

    public void setMainImageBitmap(Bitmap mainImageBitmap) {
        this.mainImageBitmap = mainImageBitmap;
    }

    public String getPostId() {
        return postId;
    }

    public TimelineModel(String postId, Bitmap mainImageBitmap, String title, String description, String name, String time, String profileImageUrl){
        this.mainImageBitmap = mainImageBitmap;
        this.title = title;
        this.description = description;
        this.name = name;
        this.time = time;
        this.profileImageUrl = profileImageUrl;
        this.postId = postId;
    }
}
