package com.example.bank_sampah_app.Transaksi.Diproses;

public class DiprosesItem {

    String jenisTransaksi, statusTransaksi, tanggalTransaksi;

    public DiprosesItem(){

    }

    public DiprosesItem(String jenisTransaksi, String statusTransaksi, String tanggalTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
        this.statusTransaksi = statusTransaksi;
        this.tanggalTransaksi = tanggalTransaksi;
    }

    //getter
    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    //setter

    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }
}
