package com.example.projecthoroscope.Model;

import java.util.List;

public class ModelResponse {
    private int kode;
    private String pesan;
    private List<ModelHoroscope> data;
    public Integer getKode() {
        return kode;
    }
    public void setKode(Integer kode) {
        this.kode = kode;
    }
    public String getPesan() {
        return pesan;
    }
    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
    public List<ModelHoroscope> getData() {
        return data;
    }
    public void setData(List<ModelHoroscope> data) {
        this.data = data;
    }
}
