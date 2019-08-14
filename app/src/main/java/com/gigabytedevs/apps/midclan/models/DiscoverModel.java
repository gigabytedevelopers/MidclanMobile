package com.gigabytedevs.apps.midclan.models;

public class DiscoverModel {
    private int discoverImage;
    private String discoverTitle;

    public int getDiscoverImage() {
        return discoverImage;
    }

    public void setDiscoverImage(int discoverImage) {
        this.discoverImage = discoverImage;
    }

    public String getDiscoverTitle() {
        return discoverTitle;
    }

    public void setDiscoverTitle(String discoverTitle) {
        this.discoverTitle = discoverTitle;
    }

    public  DiscoverModel(String discoverTitle, int discoverImage){
        this.discoverTitle = discoverTitle;
        this.discoverImage = discoverImage;
    }
}
