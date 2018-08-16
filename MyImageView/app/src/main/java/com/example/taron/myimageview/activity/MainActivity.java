package com.example.taron.myimageview.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.taron.myimageview.adapter.ListAdapterForImg;
import com.example.taron.myimageview.R;
import com.example.taron.myimageview.model.Result;
import com.example.taron.myimageview.retrofit.RetrofitClient;
import com.example.taron.myimageview.retrofit.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "tag";
    private ListAdapterForImg adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializationRecycler();
        retrofitLoader();
    }

    private void initializationRecycler() {
        final RecyclerView rvImgList = findViewById(R.id.list_items);
        adapter = new ListAdapterForImg(MainActivity.this);
        rvImgList.setLayoutManager(new LinearLayoutManager(this));
        rvImgList.setHasFixedSize(true);
        rvImgList.setAdapter(adapter);
    }

    private void retrofitLoader() {
        RetrofitInterface client = RetrofitClient.getClient().create(RetrofitInterface.class);
        Call<List<Result>> call = client.getImagesData();
        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(@NonNull Call<List<Result>> call,
                                   @NonNull Response<List<Result>> response) {
                List<Result> imgList = response.body();
                adapter.setData(imgList);
            }

            @Override
            public void onFailure(@NonNull Call<List<Result>> call, @NonNull Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
