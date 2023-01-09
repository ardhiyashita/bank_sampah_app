package com.bps.pesanpede.API.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PengajuanRequest {

    @SerializedName("user_id")
    private int user_id;
    @SerializedName("foto_sampah")
    private String foto_sampah;
    @SerializedName("catatan_sampah")
    private String catatan_sampah;
    @SerializedName("tipe_pengambilan")
    private String tipe_pengambilan;
    @SerializedName("berat")
    private String berat;
    @SerializedName("status")
    private String status;
    @SerializedName("admin_id")
    private int admin_id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCatatan_sampah() {
        return catatan_sampah;
    }

    public void setCatatan_sampah(String catatan_sampah) {
        this.catatan_sampah = catatan_sampah;
    }

    public String getTipe_pengambilan() {
        return tipe_pengambilan;
    }

    public void setTipe_pengambilan(String tipe_pengambilan) {
        this.tipe_pengambilan = tipe_pengambilan;
    }

    public String getFoto_sampah() {
        return foto_sampah;
    }

    public void setFoto_sampah(String foto_sampah) {
        this.foto_sampah = foto_sampah;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
