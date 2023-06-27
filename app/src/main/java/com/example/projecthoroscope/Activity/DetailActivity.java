package com.example.projecthoroscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projecthoroscope.R;

public class DetailActivity extends AppCompatActivity {
    private String yId, yNama, yOld, yNow, yDeskripsi, yFoto, yPathFoto;
    private TextView tvNama, tvOld, tvNow, tvDeskripsi;
    private ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        yId = intent.getStringExtra("xId");
        yNama = intent.getStringExtra("xNama");
        yOld = intent.getStringExtra("xOld");
        yNow = intent.getStringExtra("xNow");
        yDeskripsi = intent.getStringExtra("xDeskripsi");
        yFoto = intent.getStringExtra("xFoto");
        yPathFoto = intent.getStringExtra("xPathFoto");

        tvNama = findViewById(R.id.tv_nama);
        tvOld = findViewById(R.id.tv_old);
        tvNow = findViewById(R.id.tv_now);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
        ivPhoto = findViewById(R.id.iv_photo);

        tvNama.setText(yNama);
        tvOld.setText(yOld);
        tvNow.setText(yNow);
        tvDeskripsi.setText(yDeskripsi);
        Glide.with(DetailActivity.this)
                .load(yPathFoto)
                .apply(new RequestOptions().override(600, 600))
                .into(ivPhoto);
    }
}