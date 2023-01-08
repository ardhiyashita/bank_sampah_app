package com.example.bank_sampah_app.transaksi;

public class TransaksiItem {
    public TransaksiItem() {
        this.jenisTransaksi = jenisTransaksi;
        this.statusTransaksi = statusTransaksi;
    }

    String jenisTransaksi, statusTransaksi;

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }
}
