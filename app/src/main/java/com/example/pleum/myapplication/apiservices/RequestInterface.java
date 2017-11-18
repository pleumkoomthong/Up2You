package com.example.pleum.myapplication.apiservices;

import com.example.pleum.myapplication.models.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by PLEUM on 19/10/2560.
 */

public interface RequestInterface {

    @GET("allmusic.php")
    Call<JSONResponse> getJSON();






}
