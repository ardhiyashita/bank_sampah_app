package com.bps.pesanpede.artikel;

import com.google.gson.annotations.SerializedName;

public class Artikel {
    @SerializedName("id")
    private String id;

    @SerializedName("slug")
    private String slug;

    @SerializedName("judul")
    private String judul;

    @SerializedName("paragraf")
    private String konten;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("kategori_id")
    private String kategori;

    @SerializedName("created_at")
    private String tanggal;

    @SerializedName("update_at")
    private String update_tanggal;

    public Artikel(String judul, String konten, String gambar, String kategori, String tanggal) {
        this.judul = judul;
        this.konten = konten;
        this.gambar = gambar;
        this.kategori = kategori;
        this.tanggal = tanggal;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUpdate_tanggal() {
        return update_tanggal;
    }

    public void setUpdate_tanggal(String update_tanggal) {
        this.update_tanggal = update_tanggal;
    }

}
