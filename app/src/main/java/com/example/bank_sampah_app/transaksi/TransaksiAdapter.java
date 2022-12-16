package com.example.bank_sampah_app.transaksi;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank_sampah_app.R;
import java.util.ArrayList;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ListViewHolder>{
    private ArrayList<TransaksiItem> listTransaksi;

    public TransaksiAdapter(ArrayList<TransaksiItem> list) {
        this.listTransaksi = list;
    }

    @NonNull
    @Override
    public TransaksiAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_transaksi_item, parent, false);
        return new TransaksiAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiAdapter.ListViewHolder holder, int position) {
        TransaksiItem transaksiItem = listTransaksi.get(position);
        holder.jenisTransaksi.setText(transaksiItem.getJenisTransaksi());
        holder.jenisTransaksi.setText(transaksiItem.getStatusTransaksi());
    }

    @Override
    public int getItemCount() {
        return listTransaksi.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView jenisTransaksi, statusTransaksi;


        public ListViewHolder(View itemview) {
            super(itemview);
            jenisTransaksi = itemview.findViewById(R.id.jenisTransaksi);
            statusTransaksi = itemview.findViewById(R.id.statusTransaksi);
        }
    }
}
