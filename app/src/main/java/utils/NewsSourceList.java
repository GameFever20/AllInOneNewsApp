package utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import app.news.allinone.craftystudio.allinonenewsapp.R;

public class NewsSourceList {
    String newsListSource;
    String newsListHeading;
    String newsListLink;
    String newsListArticle;


    int sourceIndex = 0;


    boolean isExpanded = false;


    public NewsSourceList() {
    }

    public String getNewsListSource() {
        return newsListSource;
    }

    public void setNewsListSource(String newsListSource) {
        this.newsListSource = newsListSource;
    }

    public String getNewsListHeading() {
        return newsListHeading;
    }

    public void setNewsListHeading(String newsListHeading) {
        this.newsListHeading = newsListHeading;
    }

    public String getNewsListLink() {
        return newsListLink;
    }

    public void setNewsListLink(String newsListLink) {
        this.newsListLink = newsListLink;
    }

    public String getNewsListArticle() {
        return newsListArticle;
    }

    public void setNewsListArticle(String newsListArticle) {
        this.newsListArticle = newsListArticle;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getSourceIndex() {
        return sourceIndex;
    }

    public void setSourceIndex(int sourceIndex) {
        this.sourceIndex = sourceIndex;
    }

    public Drawable resolveIconImage(Context context) {

        Drawable drawable = null;

        switch (getSourceIndex()) {
            case 0:
                drawable = ContextCompat.getDrawable(context, R.mipmap.ic_zee_logo);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(context, R.mipmap.ic_ajjtak_logo);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(context, R.mipmap.ic_hindustantimes_logo);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(context, R.mipmap.ic_economicstimes_logo);
                break;
            default:
                drawable = ContextCompat.getDrawable(context, R.mipmap.ic_zee_logo);
                break;

        }

        return drawable;

    }
}
