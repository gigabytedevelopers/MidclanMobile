package com.gigabytedevs.apps.midclan.models;

import android.graphics.Bitmap;

import org.json.JSONArray;

import java.util.ArrayList;

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

    public String getCommentsCount() {
        return commentsCount;
    }

    private String commentsCount;
    private int profileImage;
    private String profileImageUrl;
    private String mainImageUrl;
    private String postId;
    private String likesCount;
    private JSONArray comments;

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

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }


    public String getPostId() {
        return postId;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public TimelineModel(String postId, String mainImageUrl, String title, String description,
                         String name, String time, String profileImageUrl, String likesCount,
                         String commentsCount, JSONArray comments){
        this.mainImageUrl = mainImageUrl;
        this.title = title;
        this.description = description;
        this.name = name;
        this.time = time;
        this.profileImageUrl = profileImageUrl;
        this.postId = postId;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.comments = comments;
    }
}
