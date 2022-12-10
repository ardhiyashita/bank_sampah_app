package com.example.bank_sampah_app.panduan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.help.HelpAdapter;
import com.example.bank_sampah_app.help.HelpItem;

import java.util.ArrayList;

public class PanduanAdapter extends RecyclerView.Adapter<PanduanAdapter.ListViewHolder>{

    private ArrayList<PanduanItem> listPanduan;
    private PanduanAdapter.OnItemClickCallback onItemClickCallback;

    public void setOnItemClickListener(PanduanAdapter.OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public PanduanAdapter(ArrayList<PanduanItem> list) {
        this.listPanduan = list;
    }

    @NonNull
    @Override
    public PanduanAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_panduan, parent, false);
        return new PanduanAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PanduanAdapter.ListViewHolder holder, int position) {
        PanduanItem panduanItem = listPanduan.get(position);
        holder.panduan.setText(panduanItem.getPanduan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listPanduan.get(holder.getAdapterPosition()));

            }
        });
        holder.panduan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listPanduan.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPanduan.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView panduan;
        ImageView panduan_img;

        ListViewHolder(View itemview) {
            super(itemview);
            panduan = itemview.findViewById(R.id.panduan_content);
            panduan_img = itemview.findViewById(R.id.panduan_img);
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(PanduanItem data);
    }
}
