package com.example.bank_sampah_app.transaksi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank_sampah_app.API.responses.DataTransaksi;
import com.example.bank_sampah_app.R;

import java.util.List;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ListTransaksiViewHolder>{

    Context context;
    List<DataTransaksi> listTransaksi;

    public TransaksiAdapter(List<DataTransaksi> list) {
        this.listTransaksi = list;
    }


    @NonNull
    @Override
    public TransaksiAdapter.ListTransaksiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_transaksi_item, parent, false);
        return new TransaksiAdapter.ListTransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiAdapter.ListTransaksiViewHolder holder, int position) {
        DataTransaksi dataTransaksi = listTransaksi.get(position);
        holder.jenisTransaksi.setText(dataTransaksi.getTipePengambilan());
        holder.statusTransaksi.setText(dataTransaksi.getStatus());
        holder.tanggalTransaksi.setText(dataTransaksi.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return listTransaksi.size();
    }

    public class ListTransaksiViewHolder extends RecyclerView.ViewHolder {
        TextView jenisTransaksi, statusTransaksi, tanggalTransaksi;

        ListTransaksiViewHolder(View itemview) {
            super(itemview);
            jenisTransaksi = itemview.findViewById(R.id.jenisTransaksi);
            statusTransaksi = itemview.findViewById(R.id.statusTransaksi);
            tanggalTransaksi = itemview.findViewById(R.id.tanggalTransaksi);
        }
    }
}
