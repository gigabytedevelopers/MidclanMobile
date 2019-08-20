package com.gigabytedevs.apps.midclan.utils.events;

public class ChangeFrameEvent {

    private String choose;

    public ChangeFrameEvent(String choose){
        this.choose = choose;
    }

    public String getChoose() {
        return choose;
    }
}
