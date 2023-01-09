package com.example.bank_sampah_app.help;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bank_sampah_app.R;
import java.util.ArrayList;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.ListViewHolder>{

    private final ArrayList<HelpItem> listQuestion;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickListener(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public HelpAdapter(ArrayList<HelpItem> list) {
        this.listQuestion = list;
    }

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
