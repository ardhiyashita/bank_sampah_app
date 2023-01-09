package com.bps.pesanpede.API.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataTransaksi {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("foto_sampah")
    @Expose
    private String fotoSampah;
    @SerializedName("catatan_sampah")
    @Expose
    private Object catatanSampah;
    @SerializedName("tipe_pengambilan")
    @Expose
    private String tipePengambilan;
    @SerializedName("berat")
    @Expose
    private String berat;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("admin_id")
    @Expose
    private Object adminId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public DataTransaksi(Integer id, Integer userId, String fotoSampah, Object catatanSampah, String tipePengambilan, String berat, String status, Object adminId, String createdAt, String updatedAt) {
        this.id = id;
        this.userId = userId;
        this.fotoSampah = fotoSampah;
        this.catatanSampah = catatanSampah;
        this.tipePengambilan = tipePengambilan;
        this.berat = berat;
        this.status = status;
        this.adminId = adminId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFotoSampah() {
        return fotoSampah;
    }

    public void setFotoSampah(String fotoSampah) {
        this.fotoSampah = fotoSampah;
    }

    public Object getCatatanSampah() {
        return catatanSampah;
    }

    public void setCatatanSampah(Object catatanSampah) {
        this.catatanSampah = catatanSampah;
    }

    public String getTipePengambilan() {
        return tipePengambilan;
    }

    public void setTipePengambilan(String tipePengambilan) {
        this.tipePengambilan = tipePengambilan;
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

    public Object getAdminId() {
        return adminId;
    }

    public void setAdminId(Object adminId) {
        this.adminId = adminId;
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
