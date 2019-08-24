package com.gigabytedevs.apps.midclan.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class SendVolleyRequest {
    public static String SendRequest(String url, String bodyParams, String method, Context context){
        if (method.equals("POST")){
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){

            };

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(request);
        }

        return "hello";
    }
}
