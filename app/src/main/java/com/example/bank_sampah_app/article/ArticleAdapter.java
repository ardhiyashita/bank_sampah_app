package com.example.bank_sampah_app.article;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank_sampah_app.R;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<ArticleModelClass> articleList;

    public ArticleAdapter (List<ArticleModelClass>articleList){ this .articleList=articleList; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_article,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int image= articleList.get(position).getArticle_image();
        String heading= articleList.get(position).getArticle_heading();
        String content= articleList.get(position).getArticle_content();
        String divider= articleList.get(position).getArticle_divider();

        holder.setData(image,heading,content,divider);

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView article_image;
        private TextView article_heading;
        private TextView article_content;
        private TextView article_divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            article_image=itemView.findViewById(R.id.article_image);
            article_heading=itemView.findViewById(R.id.article_heading);
            article_content=itemView.findViewById(R.id.article_content);
            article_divider=itemView.findViewById(R.id.article_divider);

        }

        public void setData(int image, String heading, String content, String divider){

            article_image.setImageResource(image);
            article_heading.setText(heading);
            article_content.setText(content);
            article_divider.setText(divider);

        }
    }

}
