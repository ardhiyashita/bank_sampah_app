package com.example.bank_sampah_app.help;

import android.content.Context;
import android.telecom.StatusHints;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.Transaksi.Diproses.DiprosesAdapter;
import com.example.bank_sampah_app.Transaksi.Diproses.DiprosesItem;

import java.util.ArrayList;
import java.util.List;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.ListViewHolder>{

    private List<HelpItem> listQuestion;
    private OnItemClickCallback onItemClickCallback;
//    private AdapterView.OnItemClickListener onItemClickListener;
//
    public void setOnItemClickListener(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public HelpAdapter(ArrayList<HelpItem> list) {
        this.listQuestion = list;
    }

//    public void setFilteredList(List<HelpItem> filteredList){
//        this.listQuestion = filteredList;
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_faq, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        HelpItem helpItem = listQuestion.get(position);
        holder.question.setText(helpItem.getQuestion());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listQuestion.get(holder.getAdapterPosition()));

            }
        });
        holder.imgview_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listQuestion.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listQuestion.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        ImageView imgview_answer;

        ListViewHolder(View itemview) {
            super(itemview);
            question = itemview.findViewById(R.id.question);
            imgview_answer = itemview.findViewById(R.id.imgview_answer);
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(HelpItem data);
    }
}
