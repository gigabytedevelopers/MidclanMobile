package com.gigabytedevs.apps.midclan.models.events_models;

public class ButtonVisibilityEvent {
    private int visible;

    public ButtonVisibilityEvent(int visible){
        this.visible = visible;
    }

    public int getVisible() {
        return visible;
    }
}
