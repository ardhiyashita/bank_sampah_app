package com.bps.pesanpede;

public class User {

    private int id;
    private String name;
    private String no_buku;
    private String email;
    private int saldo;
    private String foto;
    private String alamat;
    private String jenis_kelamin;
    private String no_hp;
    private String tgl_lahir;

    public User(int id_user, String name, String no_buku, String email, int saldo, String foto, String alamat, String jenis_kelamin, String no_hp, String tgl_lahir) {
        this.id = id_user;
        this.name = name;
        this.no_buku = no_buku;
        this.email = email;
        this.saldo = saldo;
        this.foto = foto;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
        this.no_hp = no_hp;
        this.tgl_lahir = tgl_lahir;
    }

    public int getId_user() {
        return id;
    }

    public void setId_user(int id_user) {
        this.id = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo_buku() {
        return no_buku;
    }

    public void setNo_buku(String no_buku) {
        this.no_buku = no_buku;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
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

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }
}
