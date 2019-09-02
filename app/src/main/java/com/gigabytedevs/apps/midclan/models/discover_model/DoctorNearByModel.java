package com.gigabytedevs.apps.midclan.models.discover_model;

public class DoctorNearByModel {
    private String doctorName, doctorAddress, doctorLocation;
    private int doctorImage;
    private float  doctorRating;

    public float getDoctorRating() {
        return doctorRating;
    }

    public void setDoctorRating(float doctorRating) {
        this.doctorRating = doctorRating;
    }

    public DoctorNearByModel(int doctorImage, float doctorRating, String doctorName, String doctorAddress, String doctorLocation) {
        this.doctorImage = doctorImage;
        this.doctorName = doctorName;
        this.doctorAddress = doctorAddress;
        this.doctorLocation = doctorLocation;
        this.doctorRating = doctorRating;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    public String getDoctorLocation() {
        return doctorLocation;
    }

    public void setDoctorLocation(String doctorLocation) {
        this.doctorLocation = doctorLocation;
    }

    public int getDoctorImage() {
        return doctorImage;
    }

    public void setDoctorImage(int doctorImage) {
        this.doctorImage = doctorImage;
    }
}