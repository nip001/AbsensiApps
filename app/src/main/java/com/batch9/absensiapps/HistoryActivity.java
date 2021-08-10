package com.batch9.absensiapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.batch9.absensiapps.adapter.AbsensiAdapter;
import com.batch9.absensiapps.entity.Absensi;
import com.batch9.absensiapps.service.ApiClient;
import com.batch9.absensiapps.service.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView rvAbsensi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rvAbsensi = findViewById(R.id.rv_absensi);

        ApiInterface aInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<ArrayList<Absensi>> call = aInterface.getHistory(getIntent().getStringExtra("username"));
        call.enqueue(new Callback<ArrayList<Absensi>>() {
            @Override
            public void onResponse(Call<ArrayList<Absensi>> call, Response<ArrayList<Absensi>> response) {

                AbsensiAdapter adapter = new AbsensiAdapter(response.body(), HistoryActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HistoryActivity.this,LinearLayoutManager.VERTICAL,false);
                rvAbsensi.setLayoutManager(layoutManager);
                rvAbsensi.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Absensi>> call, Throwable t) {

            }
        });

    }
}