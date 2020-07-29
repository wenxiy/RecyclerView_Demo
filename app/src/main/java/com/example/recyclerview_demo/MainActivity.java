package com.example.recyclerview_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private List<Developer> developerdatas;
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private static final String baseUrl ="https://ghapi.huchen.dev/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        init();
        response();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerview);
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new MaterialHeader(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                Toast.makeText(MainActivity.this, "success to refresh", Toast.LENGTH_SHORT).show();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                Toast.makeText(MainActivity.this, "fail to refresh", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void response() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);Call<List<Developer>> task = api.getDevelopers();
       task.enqueue(new Callback<List<Developer>>() {
           @Override
           public void onResponse(Call<List<Developer>> call, Response<List<Developer>> response) {
               int code = response.code();
               developerdatas = new ArrayList<>();
               if (code == HttpsURLConnection.HTTP_OK) {
                   developerdatas = response.body();
                   LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                   recyclerView.setLayoutManager(linearLayoutManager);
                   ListAdapter listAdapter = new ListAdapter(developerdatas);
                   recyclerView.setAdapter(listAdapter);
                   Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
               }
               if (code == HttpsURLConnection.HTTP_NOT_FOUND) {
                   Toast.makeText(MainActivity.this, "404 not found", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<List<Developer>> call, Throwable t) {
               Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
           }
       });
    }

}
