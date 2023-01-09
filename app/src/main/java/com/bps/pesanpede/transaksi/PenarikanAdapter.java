package com.bps.pesanpede.transaksi;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bps.pesanpede.API.responses.DataPenarikan;
import com.bps.pesanpede.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class PenarikanAdapter extends RecyclerView.Adapter<PenarikanAdapter.ListPenarikanViewHolder>{
    ArrayList<DataPenarikan> listPenarikan;

    public PenarikanAdapter(ArrayList<DataPenarikan> list) {
        this.listPenarikan = list;
    }


    @NonNull
    @Override
    public PenarikanAdapter.ListPenarikanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_penarikan_item, parent, false);
        return new PenarikanAdapter.ListPenarikanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenarikanAdapter.ListPenarikanViewHolder holder, int position) {
        DataPenarikan dataPenarikan = listPenarikan.get(position);
        if(dataPenarikan.getStatus().equals("diterima")){
            holder.statusPenarikan.setTextColor(Color.parseColor("#00A86F"));
        }
        holder.statusPenarikan.setText(dataPenarikan.getStatus());
        holder.tanggalPenarikan.setText(dateFormatter(dataPenarikan.getCreatedAt()));
        holder.jumlahPenarikan.setText(formatRupiah(dataPenarikan.getUang()));
    }

    @Override
    public int getItemCount() {
        return listPenarikan.size();
    }

    public class ListPenarikanViewHolder extends RecyclerView.ViewHolder {
        TextView statusPenarikan, tanggalPenarikan, jumlahPenarikan;

        ListPenarikanViewHolder(View itemview) {
            super(itemview);
            statusPenarikan = itemview.findViewById(R.id.statusPenarikan);
            tanggalPenarikan = itemview.findViewById(R.id.tanggalPenarikan);
            jumlahPenarikan = itemview.findViewById(R.id.jumlahPenarikan);
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

    private String formatRupiah(int number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}
