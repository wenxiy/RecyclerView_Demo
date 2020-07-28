package com.example.recyclerview_demo;

import android.database.Observable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface API {
    @GET("/developers")
    Call<List<Developer>> getDevelopers();
}
