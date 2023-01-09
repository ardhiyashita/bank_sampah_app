package com.example.bank_sampah_app.tentangKami;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.panduan.PanduanAdapter;
import com.example.bank_sampah_app.panduan.PanduanItem;

import java.util.ArrayList;

public class TentangKamiAdapter extends RecyclerView.Adapter<TentangKamiAdapter.ListViewHolder> {
    private final ArrayList<TentangKamiItem> listTentang;
    private TentangKamiAdapter.OnItemClickCallback onItemClickCallback;

    public void setOnItemClickListener(TentangKamiAdapter.OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public TentangKamiAdapter(ArrayList<TentangKamiItem> list) {
        this.listTentang = list;
    }

    @NonNull
    @Override
    public TentangKamiAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tentang_kami_item, parent, false);
        return new TentangKamiAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TentangKamiAdapter.ListViewHolder holder, int position) {
        TentangKamiItem tentangKamiItem = listTentang.get(position);
        holder.tentang.setText(tentangKamiItem.getTentang());
        Glide.with(holder.itemView.getContext())
                .load(tentangKamiItem.getLogo())
                .into(holder.tentang_img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listTentang.get(holder.getAdapterPosition()));

            }
        });
        holder.tentang_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listTentang.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTentang.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tentang;
        ImageView tentang_img;

        ListViewHolder(View itemview) {
            super(itemview);
            tentang = itemview.findViewById(R.id.tentang_content);
            tentang_img = itemview.findViewById(R.id.tentang_img);
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(TentangKamiItem data);
    }
}
