package com.gigabytedevs.apps.midclan.models.api_models;

public class PatientModel {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String mobileno;
    private String password;
    private String address;
    private String state;
    private String country;
    private String dob;
    private String gender;

    public PatientModel(
            String firstName,
            String lastName,
            String userName,
            String email,
            String mobileno,
            String password,
            String address,
            String state,
            String country,
            String dob,
            String gender
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.mobileno = mobileno;
        this.password = password;
        this.address = address;
        this.state = state;
        this.country = country;
        this.dob = dob;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }
}
