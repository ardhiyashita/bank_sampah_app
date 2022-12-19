package com.example.bank_sampah_app.API.requests;

import com.google.gson.annotations.SerializedName;

public class TarikRequest {
    @SerializedName("uang")
    private int uang;

    @SerializedName("catatan")
    private String catatan;

    public int getUang() {
        return uang;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
