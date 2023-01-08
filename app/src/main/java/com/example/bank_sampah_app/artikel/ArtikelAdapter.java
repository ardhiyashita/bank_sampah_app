package com.example.bank_sampah_app.artikel;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank_sampah_app.API.Constant;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.panduan.PanduanAdapter;
import com.example.bank_sampah_app.panduan.PanduanItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.ViewHolder> {

    private ArrayList<Artikel> artikelList;
    Context context;

    public ArtikelAdapter(Context context, ArrayList<Artikel>list){
        this .artikelList=list;
        this.context = context;
    }

    @NonNull
    @Override
    public ArtikelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_article,parent,false);
        return new ArtikelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtikelAdapter.ViewHolder holder, int position) {
        Artikel artikelItem = artikelList.get(position);
        Picasso.get().load("https://pesanpede.com/"+ artikelItem.getGambar()).into(holder.gambar);
        holder.judul.setText(artikelItem.getJudul());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.konten.setText(Html.fromHtml(artikelItem.getKonten(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.konten.setText(Html.fromHtml(artikelItem.getKonten()));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onItemClickCallback.onItemClicked(artikelList.get(holder.getAdapterPosition()));
                Intent intent =  new Intent(context, ArtikelDetailActivity.class);
                intent.putExtra("slug", artikelItem.getSlug());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artikelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView gambar;
        private TextView judul;
        private TextView konten;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gambar=itemView.findViewById(R.id.article_image);
            judul=itemView.findViewById(R.id.article_heading);
            konten=itemView.findViewById(R.id.article_content);

        }
    }

}
