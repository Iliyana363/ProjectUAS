package com.example.projecthoroscope.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projecthoroscope.API.APIRequestData;
import com.example.projecthoroscope.API.RetroServer;
import com.example.projecthoroscope.Activity.DetailActivity;
import com.example.projecthoroscope.Activity.MainActivity;
import com.example.projecthoroscope.Activity.UbahActivity;
import com.example.projecthoroscope.Model.ModelHoroscope;
import com.example.projecthoroscope.Model.ModelResponse;
import com.example.projecthoroscope.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterHoroscope extends RecyclerView.Adapter<AdapterHoroscope.VHHoroscope> {
    private Context ctx;
    private List<ModelHoroscope> listHoroscope;

    public AdapterHoroscope(Context ctx, List<ModelHoroscope> listHoroscope) {
        this.ctx = ctx;
        this.listHoroscope = listHoroscope;
    }

    @NonNull
    @Override
    public VHHoroscope onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_kuliner, parent, false);
        return new VHHoroscope(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHHoroscope holder, int position) {
        ModelHoroscope MHR = listHoroscope.get(position);
        holder.tvId.setText(MHR.getId());
        holder.tvNama.setText(MHR.getNama_horoscope());
        holder.tvOld.setText(MHR.getOld());
        holder.tvNow.setText(MHR.getNow());
        holder.tvDeskripsi.setText(MHR.getDeskripsi());
        holder.tvImgPath.setText(MHR.getPhoto());
        Glide.with(holder.itemView.getContext())
                .load(MHR.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return listHoroscope.size();
    }

    public class VHHoroscope extends RecyclerView.ViewHolder {
        ImageView ivPhoto, ivEdit, ivDetail, ivDelete;
        TextView tvId, tvNama, tvOld, tvNow, tvDeskripsi, tvImgPath;
        public VHHoroscope(@NonNull View itemView) {
            super(itemView);

            ivPhoto = itemView.findViewById(R.id.iv_listitemkuliner_photo);
            tvId = itemView.findViewById(R.id.tv_listitemkuliner_id);
            tvNama = itemView.findViewById(R.id.tv_listitemkuliner_nama);
            tvOld = itemView.findViewById(R.id.tv_listitemkuliner_old);
            tvNow = itemView.findViewById(R.id.tv_listitemkuliner_now);
            tvDeskripsi = itemView.findViewById(R.id.tv_listitemkuliner_deskripsi);
            tvImgPath = itemView.findViewById(R.id.tv_listitemkuliner_imgpath);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            ivDetail = itemView.findViewById(R.id.iv_detail);
            ivDelete = itemView.findViewById(R.id.iv_delete);

            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, UbahActivity.class);
                    intent.putExtra("xId", tvId.getText().toString());
                    intent.putExtra("xNama", tvNama.getText().toString());
                    intent.putExtra("xOld", tvOld.getText().toString());
                    intent.putExtra("xNow", tvNow.getText().toString());
                    intent.putExtra("xDeskripsi", tvDeskripsi.getText().toString());
                    intent.putExtra("xFoto", (String) ivPhoto.getTag());
                    intent.putExtra("xPathFoto", (String) tvImgPath.getText().toString());
                    ctx.startActivity(intent);
                }
            });

            ivDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, DetailActivity.class);
                    intent.putExtra("xId", tvId.getText().toString());
                    intent.putExtra("xNama", tvNama.getText().toString());
                    intent.putExtra("xOld", tvOld.getText().toString());
                    intent.putExtra("xNow", tvNow.getText().toString());
                    intent.putExtra("xDeskripsi", tvDeskripsi.getText().toString());
                    intent.putExtra("xFoto", (String) ivPhoto.getTag());
                    intent.putExtra("xPathFoto", (String) tvImgPath.getText().toString());
                    ctx.startActivity(intent);
                }
            });

            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String idHoroscope = tvId.getText().toString();
                    hapusHoroscope(idHoroscope);
                }
            });
        }
        private void hapusHoroscope(String id){
            APIRequestData API = RetroServer.connectRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = API.ardDelete(id);
            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, "Kode: "+kode+ "| Pesan:" +pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieverHoroscope();
                }
                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal menghubungi server: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
