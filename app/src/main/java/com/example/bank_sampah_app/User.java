package com.example.bank_sampah_app;

import java.util.Date;

public class User {

    private String id_user;
    private String nama;
    private String email;
    private String saldo;
    private String foto;
    private String alamat;
    private String jenis_kelamin;
    private String no_hp;
    private Date tgl_lahir;

    public User(String id_user, String nama, String email, String saldo, String foto, String alamat, String jenis_kelamin, String no_hp, Date tgl_lahir) {
        this.id_user = id_user;
        this.nama = nama;
        this.email = email;
        this.saldo = saldo;
        this.foto = foto;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
        this.no_hp = no_hp;
        this.tgl_lahir = tgl_lahir;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public Date getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(Date tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }
}
