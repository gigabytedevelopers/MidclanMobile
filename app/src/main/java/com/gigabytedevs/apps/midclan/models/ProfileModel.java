package com.gigabytedevs.apps.midclan.models;

public class ProfileModel {
    private String profileTitle, profileCaption;

    public ProfileModel(String profileTitle, String profileCaption){
        this.profileTitle = profileTitle;
        this.profileCaption = profileCaption;
    }

    public String getProfileTitle() {
        return profileTitle;
    }

    public void setProfileTitle(String profileTitle) {
        this.profileTitle = profileTitle;
    }

    public String getProfileCaption() {
        return profileCaption;
    }

    public void setProfileCaption(String profileCaption) {
        this.profileCaption = profileCaption;
    }
}
