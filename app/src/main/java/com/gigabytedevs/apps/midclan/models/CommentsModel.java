package com.gigabytedevs.apps.midclan.models;

public class CommentsModel {
    private String imageUrl;
    private String name;
    private String comment;
    private int color;

    public CommentsModel(){

    }
    public CommentsModel(String imageUrl, String name, String comment){
        this.imageUrl = imageUrl;
        this.name = name;
        this.comment = comment;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }
}
