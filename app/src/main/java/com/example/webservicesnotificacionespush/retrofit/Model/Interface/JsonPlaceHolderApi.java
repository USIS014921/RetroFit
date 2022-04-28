package com.example.webservicesnotificacionespush.retrofit.Model.Interface;

import com.example.webservicesnotificacionespush.retrofit.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("Posts")
    default Call<List<Posts>> getPosts() {

        return null;
    }
}
