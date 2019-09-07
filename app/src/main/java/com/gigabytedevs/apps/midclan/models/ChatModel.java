package com.gigabytedevs.apps.midclan.models;

public class ChatModel {
    private int senderImage;
    private String senderName, senderText, senderTime, senderImageUrl,id;

    public int getSenderImage() {
        return senderImage;
    }

    public void setSenderImage(int senderImage) {
        this.senderImage = senderImage;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderText() {
        return senderText;
    }

    public void setSenderText(String senderText) {
        this.senderText = senderText;
    }

    public String getSenderTime() {
        return senderTime;
    }

    public void setSenderTime(String senderTime) {
        this.senderTime = senderTime;
    }

    public String getSenderImageUrl() {
        return senderImageUrl;
    }

    public String getId() {
        return id;
    }

    public ChatModel(String id, String senderName, String senderText, String senderTime, String senderImageUrl){
        this.senderName = senderName;
        this.senderText = senderText;
        this.senderTime = senderTime;
        this.senderImageUrl = senderImageUrl;
        this.id = id;
    }
}
