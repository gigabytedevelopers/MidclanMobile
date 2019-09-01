package com.gigabytedevs.apps.midclan.models.events_models;

public class RequestDoneEvent {
    private String request;

    public String getRequest() {
        return request;
    }

    public RequestDoneEvent(String request){
        this.request = request;
    }
}
