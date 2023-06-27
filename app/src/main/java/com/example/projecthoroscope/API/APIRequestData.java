package com.example.projecthoroscope.API;

import com.example.projecthoroscope.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieve();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse> ardCreate(
            @Field("nama_horoscope") String nama_horoscope,
            @Field("old") String old,
            @Field("now") String now,
            @Field("deskripsi") String deskripsi,
            @Field("photo") String photo
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelResponse> ardDelete(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse> ardUpdate(
            @Field("id") String id,
            @Field("nama_horoscope") String nama_horoscope,
            @Field("old") String old,
            @Field("now") String now,
            @Field("deskripsi") String deskripsi,
            @Field("photo") String photo
    );
}
