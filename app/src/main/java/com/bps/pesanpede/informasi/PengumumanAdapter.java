package com.bps.pesanpede.informasi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bps.pesanpede.API.responses.DataPengumuman;
import com.bps.pesanpede.R;

import java.util.ArrayList;

public class PengumumanAdapter extends RecyclerView.Adapter<PengumumanAdapter.ListPengumumanViewHolder> {
    ArrayList<DataPengumuman> listPengumuman;

    public PengumumanAdapter(ArrayList<DataPengumuman> list) {
        this.listPengumuman = list;
    }


    @NonNull
    @Override
    public PengumumanAdapter.ListPengumumanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pengumuman_item, parent, false);
        return new PengumumanAdapter.ListPengumumanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PengumumanAdapter.ListPengumumanViewHolder holder, int position) {
        DataPengumuman dataPengumuman = listPengumuman.get(position);
        holder.judulPengumuman.setText(dataPengumuman.getJudul());
        holder.isiPengumuman.setText(dataPengumuman.getIsi());
    }

    @Override
    public int getItemCount() {
        return listPengumuman.size();
    }

    public class ListPengumumanViewHolder extends RecyclerView.ViewHolder {
        TextView judulPengumuman, isiPengumuman;

        ListPengumumanViewHolder(View itemview) {
            super(itemview);
            judulPengumuman = itemview.findViewById(R.id.judul_pengumuman);
            isiPengumuman = itemview.findViewById(R.id.isi_pengumuman);
        }
    }
}
