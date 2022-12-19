package com.example.bank_sampah_app.transaksi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank_sampah_app.API.responses.DataTransaksi;
import com.example.bank_sampah_app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class SetoranAdapter extends RecyclerView.Adapter<SetoranAdapter.ListTransaksiViewHolder>{

    ArrayList<DataTransaksi> listTransaksi;

    public SetoranAdapter(ArrayList<DataTransaksi> list) {
        this.listTransaksi = list;
    }


    @NonNull
    @Override
    public SetoranAdapter.ListTransaksiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_setoran_item, parent, false);
        return new SetoranAdapter.ListTransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SetoranAdapter.ListTransaksiViewHolder holder, int position) {
        DataTransaksi dataTransaksi = listTransaksi.get(position);
        holder.statusTransaksi.setText(dataTransaksi.getStatus());
        holder.tanggalTransaksi.setText(dateFormatter(dataTransaksi.getCreatedAt()));
    }

    @Override
    public int getItemCount() {
        return listTransaksi.size();
    }

    public class ListTransaksiViewHolder extends RecyclerView.ViewHolder {
        TextView statusTransaksi, tanggalTransaksi;

        ListTransaksiViewHolder(View itemview) {
            super(itemview);
            statusTransaksi = itemview.findViewById(R.id.statusTransaksi);
            tanggalTransaksi = itemview.findViewById(R.id.tanggalTransaksi);
        }
    }

    public String dateFormatter(String date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date d = sdf.parse(date);

            SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            output.setTimeZone(TimeZone.getDefault());
            String formattedTime = output.format(d);
            return formattedTime;
        }catch (ParseException e) {
            return null;
        }
    }
}
