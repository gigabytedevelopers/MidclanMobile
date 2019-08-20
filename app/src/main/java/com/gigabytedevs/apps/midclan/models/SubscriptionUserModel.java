package com.gigabytedevs.apps.midclan.models;

public class SubscriptionUserModel {
    private String subscriptionType, subscriptionAmount, featureInfo1, featureInfo2, featureInfo3;

    public SubscriptionUserModel(String subscriptionType, String subscriptionAmount, String featureInfo1, String featureInfo2, String featureInfo3){
        this.subscriptionType = subscriptionType;
        this.subscriptionAmount = subscriptionAmount;
        this.featureInfo1 = featureInfo1;
        this.featureInfo2 = featureInfo2;
        this.featureInfo3 = featureInfo3;
    }

    public SubscriptionUserModel(String subscriptionType, String subscriptionAmount, String featureInfo1, String featureInfo2){
        this.subscriptionType = subscriptionType;
        this.subscriptionAmount = subscriptionAmount;
        this.featureInfo1 = featureInfo1;
        this.featureInfo2 = featureInfo2;
    }

    public SubscriptionUserModel(String subscriptionType, String subscriptionAmount){
        this.subscriptionType = subscriptionType;
        this.subscriptionAmount = subscriptionAmount;
    }


    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public void setSubscriptionAmount(String subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
    }

    public String getFeatureInfo1() {
        return featureInfo1;
    }

    public void setFeatureInfo1(String featureInfo1) {
        this.featureInfo1 = featureInfo1;
    }

    public String getFeatureInfo2() {
        return featureInfo2;
    }

    public void setFeatureInfo2(String featureInfo2) {
        this.featureInfo2 = featureInfo2;
    }

    public String getFeatureInfo3() {
        return featureInfo3;
    }

    public void setFeatureInfo3(String featureInfo3) {
        this.featureInfo3 = featureInfo3;
    }
}
