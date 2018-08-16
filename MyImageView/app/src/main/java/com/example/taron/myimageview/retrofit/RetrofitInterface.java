package com.example.taron.myimageview.retrofit;


import com.example.taron.myimageview.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("photos")
    Call<List<Result>> getImagesData();
}
