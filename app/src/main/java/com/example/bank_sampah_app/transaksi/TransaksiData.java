package com.example.bank_sampah_app.transaksi;

import com.example.bank_sampah_app.help.HelpItem;

import java.util.ArrayList;

public class TransaksiData {
    private static final String [] jenisTransaksi = {
            "Penarikan",
            "Setoran",
            "Setoran",
            "Penarikan"
    };
    private static final String [] statusTransaksi = {
            "Diproses",
            "Diproses",
            "Diproses",
            "Diproses",
    };

    static ArrayList<TransaksiItem> getListData(){
        ArrayList<TransaksiItem> list = new ArrayList<>();
        for (int position = 0; position <jenisTransaksi.length; position++) {
            TransaksiItem transaksiItem = new TransaksiItem();
            transaksiItem.setJenisTransaksi(jenisTransaksi[position]);
            transaksiItem.setStatusTransaksi(statusTransaksi[position]);
            list.add(transaksiItem);
        }
        return list;
    }
}
