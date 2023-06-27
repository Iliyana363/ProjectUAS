package com.example.projecthoroscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projecthoroscope.API.APIRequestData;
import com.example.projecthoroscope.API.RetroServer;
import com.example.projecthoroscope.Adapter.AdapterHoroscope;
import com.example.projecthoroscope.Model.ModelHoroscope;
import com.example.projecthoroscope.Model.ModelResponse;
import com.example.projecthoroscope.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pb;
    private RecyclerView rvHoroscope;
    private RecyclerView.Adapter adHoroscope;
    private FloatingActionButton fabHoroscope;
    private RecyclerView.LayoutManager lmHoroscope;
    private List<ModelHoroscope> listHoroscope = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = findViewById(R.id.pbKuliner);
        rvHoroscope = findViewById(R.id.rv_kuliner);
        fabHoroscope = findViewById(R.id.fab_kuliner);
        lmHoroscope = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvHoroscope.setLayoutManager(lmHoroscope);
        fabHoroscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieverHoroscope();
    }
    public void retrieverHoroscope(){
        pb.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardRetrieve();
        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                listHoroscope = response.body().getData();
                adHoroscope = new AdapterHoroscope(MainActivity.this, listHoroscope);
                rvHoroscope.setAdapter(adHoroscope);
                adHoroscope.notifyDataSetChanged();
                pb.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal menghubungi server : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.GONE);
            }
        });
    }
}