package com.example.projecthoroscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projecthoroscope.API.APIRequestData;
import com.example.projecthoroscope.API.RetroServer;
import com.example.projecthoroscope.Model.ModelResponse;
import com.example.projecthoroscope.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    private String yId, yNama, yOld, yNow, yDeskripsi, yFoto, yPathFoto;
    private String nama, old, now, deskripsi, foto;
    private EditText etNama, etOld, etNow, etDeskripsi, etFoto;
    private Button btnUbah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent intent = getIntent();
        yId = intent.getStringExtra("xId");
        yNama = intent.getStringExtra("xNama");
        yOld = intent.getStringExtra("xOld");
        yNow = intent.getStringExtra("xNow");
        yDeskripsi = intent.getStringExtra("xDeskripsi");
        yFoto = intent.getStringExtra("xFoto");
        yPathFoto = intent.getStringExtra("xPathFoto");

        etNama = findViewById(R.id.et_ubah_nama);
        etOld = findViewById(R.id.et_ubah_old);
        etNow = findViewById(R.id.et_ubah_now);
        etDeskripsi = findViewById(R.id.et_ubah_deskripsi);
        etFoto = findViewById(R.id.et_ubah_photo);
        btnUbah = findViewById(R.id.btn_ubah_ubah);

        etNama.setText(yNama);
        etOld.setText(yOld);
        etNow.setText(yNow);
        etDeskripsi.setText(yDeskripsi);
        etFoto.setText(yPathFoto);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                old = etOld.getText().toString();
                now = etNow.getText().toString();
                deskripsi = etDeskripsi.getText().toString();
                foto = etFoto.getText().toString();

                if (nama.trim().equals("")) {
                    etNama.setError("Nama harus diisi");
                } else if (old.trim().equals("")) {
                    etOld.setError("Old harus diisi");
                } else if (now.trim().equals("")) {
                    etNow.setError("Now harus diisi");
                } else if (deskripsi.trim().equals("")) {
                    etDeskripsi.setError("Deskripsi harus diisi");
                } else if (foto.trim().equals("")) {
                    etFoto.setError("Photo harus diisi");
                } else {
                    ubahMenu();
                }
            }
        });
    }

    private void ubahMenu() {
        String id = yId;
        APIRequestData api = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = api.ardUpdate(id, nama, old, now, deskripsi, foto);
        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                Toast.makeText(UbahActivity.this, "Kode : " + kode + "| Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal menghubungi server : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}