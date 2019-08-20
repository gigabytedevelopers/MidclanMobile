package com.gigabytedevs.apps.midclan.utils.events;

public class ButtonVisibilityEvent {
    private int visible;

    public ButtonVisibilityEvent(int visible){
        this.visible = visible;
    }

    public int getVisible() {
        return visible;
    }
}
