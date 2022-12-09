package com.example.bank_sampah_app.Transaksi.Diproses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.bank_sampah_app.Transaksi.Diproses.DiprosesItem;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank_sampah_app.R;

import java.util.ArrayList;

public class DiprosesAdapter extends RecyclerView.Adapter<DiprosesAdapter.MyViewHolder> {

    Context context;
    ArrayList<DiprosesItem> diprosesItemArrayList;

    public DiprosesAdapter(DiprosesTransaksiFragment context, ArrayList<DiprosesItem> diprosesItemArrayList) {
        this.context = context.getActivity();
        this.diprosesItemArrayList = diprosesItemArrayList;
    }

    @NonNull
    @Override
    public DiprosesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_diproses_item,parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DiprosesAdapter.MyViewHolder holder, int position) {
        DiprosesItem diprosesItem = diprosesItemArrayList.get(position);
        holder.jenisTransaksi.setText(diprosesItem.jenisTransaksi);
        holder.statusTransaksi.setText(diprosesItem.statusTransaksi);
        holder.tanggalTransaksi.setText(diprosesItem.tanggalTransaksi);
    }

    @Override
    public int getItemCount() {
        return diprosesItemArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView jenisTransaksi, statusTransaksi, tanggalTransaksi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            jenisTransaksi = itemView.findViewById(R.id.jenisTransaksi);
            statusTransaksi = itemView.findViewById(R.id.statusTransaksi);
            tanggalTransaksi = itemView.findViewById(R.id.tanggalTransaksi);
        }
    }
}
