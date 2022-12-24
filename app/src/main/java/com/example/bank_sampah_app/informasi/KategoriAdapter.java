package com.example.bank_sampah_app.informasi;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank_sampah_app.API.Constant;
import com.example.bank_sampah_app.API.responses.DataKategori;
import com.example.bank_sampah_app.API.responses.DataPenarikan;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.transaksi.PenarikanAdapter;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.ListKategoriViewHolder>{
    ArrayList<DataKategori> listKategori;

    public KategoriAdapter(ArrayList<DataKategori> list) {
        this.listKategori = list;
    }


    @NonNull
    @Override
    public KategoriAdapter.ListKategoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kategori_item, parent, false);
        return new KategoriAdapter.ListKategoriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriAdapter.ListKategoriViewHolder holder, int position) {
        DataKategori dataKategori = listKategori.get(position);
        Picasso.get().load("https://pesanpede.com/" + dataKategori.getGambar()).into(holder.gambarKategori);
        holder.jenisKategori.setText(dataKategori.getNamaKategori());
        holder.hargaKategori.setText(formatRupiah(dataKategori.getHarga()));
    }

    @Override
    public int getItemCount() {
        return listKategori.size();
    }

    public class ListKategoriViewHolder extends RecyclerView.ViewHolder {
        TextView jenisKategori, hargaKategori;
        ImageView gambarKategori;

        ListKategoriViewHolder(View itemview) {
            super(itemview);
            jenisKategori = itemview.findViewById(R.id.jenisKategori);
            hargaKategori = itemview.findViewById(R.id.hargaKategori);
            gambarKategori = itemview.findViewById(R.id.img_kategori_sampah);
        }
    }

    private String formatRupiah(int number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}
