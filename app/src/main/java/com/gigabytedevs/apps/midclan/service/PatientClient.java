package com.gigabytedevs.apps.midclan.service;

import com.gigabytedevs.apps.midclan.models.api_models.PatientModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PatientClient {
    @POST("patient")
    Call<PatientModel> createAccount(@Body PatientModel patientModel);
}
