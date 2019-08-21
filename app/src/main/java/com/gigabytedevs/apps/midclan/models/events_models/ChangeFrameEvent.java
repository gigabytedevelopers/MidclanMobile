package com.gigabytedevs.apps.midclan.models.events_models;

public class ChangeFrameEvent {

    private String choose;

    public ChangeFrameEvent(String choose){
        this.choose = choose;
    }

    public String getChoose() {
        return choose;
    }
}
