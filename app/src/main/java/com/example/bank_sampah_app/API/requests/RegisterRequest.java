package com.example.bank_sampah_app.API.requests;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class RegisterRequest {

    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;
    @SerializedName("no_hp")
    private String no_hp;
    @SerializedName("tgl_lahir")
    private String tgl_lahir;
    @SerializedName("password")
    private String password;
    @SerializedName("foto")
    private String foto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
