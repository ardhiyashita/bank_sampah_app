package com.example.bank_sampah_app.article;

import android.graphics.ColorSpace;

public class ArticleModelClass {

    private int article_image;
    private String article_heading;
    private String article_content;
    private String article_divider;

    public ArticleModelClass(int article_image, String article_heading, String article_content, String article_divider){
        this.article_image=article_image;
        this.article_heading=article_heading;
        this.article_content=article_content;
        this.article_divider=article_divider;
    }

    public int getArticle_image() {
        return article_image;
    }

    public String getArticle_heading() {
        return article_heading;
    }

    public String getArticle_content() {
        return article_content;
    }

    public String getArticle_divider() {
        return article_divider;
    }
}
