package com.example.recyclerview_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
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

    private static final String baseUrl ="https://ghapi.huchen.dev/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        response();
        initdata();
    }

    private void response() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<Developer> task = api.getDevelopers();
       task.enqueue(new Callback<Developer>() {
           @Override
           public void onResponse(Call<Developer> call, Response<Developer> response) {
               int code = response.code();
               if (code == HttpsURLConnection.HTTP_OK) {
                   for (int i = 0; i < 6; i++) {
                       Developer developers = new Developer();
                       developers.setAvatar(response.body().getAvatar());
                       developers.setName(response.body().getName());
                       developers.setUsername(response.body().getUsername());
                       developerdatas.add(developers);
                   }
                   Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
               }
               if (code == HttpsURLConnection.HTTP_NOT_FOUND) {
                   Toast.makeText(MainActivity.this, "404 not found", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<Developer> call, Throwable t) {
               Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void initdata() {
        developerdatas = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ListAdapter listAdapter = new ListAdapter(developerdatas);
        recyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }
}
