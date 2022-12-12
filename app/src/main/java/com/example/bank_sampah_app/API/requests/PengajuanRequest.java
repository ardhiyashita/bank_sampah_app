package com.example.bank_sampah_app.API.requests;

import java.util.Date;

public class PengajuanRequest {

    private int user_id;
    private String foto_sampah;
    private String token;
    private String catatan_sampah;
    private String tipe_pengambilan;
    private int berat;
    private int admin_id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFoto_sampah() {
        return foto_sampah;
    }

    public void setFoto_sampah(String foto_sampah) {
        this.foto_sampah = foto_sampah;
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

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
}
