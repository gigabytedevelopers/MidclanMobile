package com.gigabytedevs.apps.midclan.service;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gigabytedevs.apps.midclan.models.events_models.RequestDoneEvent;
import com.gigabytedevs.apps.midclan.utils.TinyDb;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SendVolleyRequest {
    /**
     *
     * @param url The url passed in by the particular request
     * @param bodyParams This is the body that should be passed to the url
     * @param method This is the method passed in from the particular request eg. POST,GET,PATCH
     * @param context This is the particular context which this request is coming from
     * @return This gives back a string array to the particular context that requested it
     */
    public static ArrayList<String> SendRequest(String url, String bodyParams, String method, Context context){
        final ArrayList<String> res = new ArrayList<>();
        TinyDb tinyDb = new TinyDb(context);

        switch (method) {
            case "POST": {
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, response -> {
                    res.clear();
                    res.add(response.toString());
                    EventBus.getDefault().post(new RequestDoneEvent("SIGN-IN"));
                }, error -> {
                    //Getting the error body from the server
                    res.clear();
                    try {
                        String responseBody = error.networkResponse.toString();
                        JSONObject jsonObject = new JSONObject(responseBody);

                        //add the response string to the array that is to be returned
                        res.add(jsonObject.toString());

                        //Launch this event to the particular context when the response has been gotten
                        EventBus.getDefault().post(new RequestDoneEvent("SIGN-IN"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }) {
                    @Override
                    public byte[] getBody() {
                        //Sending the user parameters as a body to the server

                        try {
                            return bodyParams == null ? null : bodyParams.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", bodyParams, "utf-8");
                            return null;
                        }
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(request);

                break;
            }
            case "GET": {
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                    res.clear();
                    res.add(response.toString());
                    EventBus.getDefault().post(new RequestDoneEvent("TIMELINE"));
                }, error -> {
                    //Getting the error body from the server
                    res.clear();
                    try {
                        String responseBody = error.networkResponse.toString();
                        JSONObject jsonObject = new JSONObject(responseBody);

                        //add the response string to the array that is to be returned
                        res.add(jsonObject.toString());

                        //Launch this event to the particular context when the response has been gotten
                        EventBus.getDefault().post(new RequestDoneEvent("TIMELINE"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        headers.put("Authorization", "Bearer " + tinyDb.getString("token"));
                        return headers;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(request);

                break;
            }
            case "POST-HEAD": {
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, response -> {
                    res.clear();
                    res.add(response.toString());
                    EventBus.getDefault().post(new RequestDoneEvent("BOOKMARK"));
                }, error -> {
                    //Getting the error body from the server
                    res.clear();
                    try {
                        String responseBody = new String(error.networkResponse.data, "utf-8");
                        JSONObject jsonObject = new JSONObject(responseBody);

                        //add the response string to the array that is to be returned
                        res.add(jsonObject.toString());

                        //Launch this event to the particular context when the response has been gotten
                        EventBus.getDefault().post(new RequestDoneEvent("BOOKMARK"));

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        headers.put("Authorization", "Bearer " + tinyDb.getString("token"));
                        return headers;
                    }

                    @Override
                    public byte[] getBody() {
                        //Sending the user parameters as a body to the server

                        try {
                            return bodyParams == null ? null : bodyParams.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", bodyParams, "utf-8");
                            return null;
                        }
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(request);
                break;
            }
        }

        return res;
    }

    private void requestBody(){

    }
}