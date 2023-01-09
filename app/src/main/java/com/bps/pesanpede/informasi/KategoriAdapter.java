package com.bps.pesanpede.informasi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bps.pesanpede.API.responses.DataKategori;
import com.bps.pesanpede.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

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
        if(dataKategori.getFoto()!=null){
            Picasso.get().load("https://pesanpede.com/" + dataKategori.getFoto()).into(holder.gambarKategori);
        }
        holder.jenisKategori.setText(dataKategori.getNama());
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
