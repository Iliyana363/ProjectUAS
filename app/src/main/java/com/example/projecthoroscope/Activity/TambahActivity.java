package com.example.projecthoroscope.Activity;

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

public class TambahActivity extends AppCompatActivity {
    private String nama, old, now, deskripsi, foto;
    private EditText etNama, etOld, etNow, etDeskripsi, etFoto;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_tambah_nama);
        etOld = findViewById(R.id.et_tambah_old);
        etNow = findViewById(R.id.et_tambah_now);
        etDeskripsi = findViewById(R.id.et_tambah_deskripsi);
        etFoto = findViewById(R.id.et_tambah_photo);
        btnSimpan = findViewById(R.id.btn_tambah_tambah);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                old = etOld.getText().toString();
                now = etNow.getText().toString();
                deskripsi = etDeskripsi.getText().toString();
                foto = etFoto.getText().toString();

                if (nama.trim().isEmpty()) {
                    etNama.setError("Nama tidak boleh kosong");
                } else if (old.trim().isEmpty()) {
                    etNama.setError("Old tidak boleh kosong");
                } else if (now.trim().isEmpty()) {
                    etNama.setError("Now tidak boleh kosong");
                } else if (deskripsi.trim().isEmpty()) {
                    etNama.setError("Deskripsi tidak boleh kosong");
                } else if (foto.trim().isEmpty()) {
                    etNama.setError("Foto tidak boleh kosong");
                } else {
                    tambahMenu();
                }
            }
        });
    }

    private void tambahMenu() {
        APIRequestData api = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = api.ardCreate(nama, old, now, deskripsi, foto);
        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : " + kode + "| Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal menghubungi server : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}