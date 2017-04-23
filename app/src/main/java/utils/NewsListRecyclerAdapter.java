package utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.news.allinone.craftystudio.allinonenewsapp.R;

/**
 * Created by bunny on 23/04/17.
 */

public class NewsListRecyclerAdapter extends RecyclerView.Adapter<NewsListRecyclerAdapter.MyViewHolder> {


    private ArrayList<NewsMetaInfo> newsMetaInfoArrayList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView newsHeadingTextView, newsDateTextView, newsCategoryTextView;
        public ImageView newsImageView;

        public MyViewHolder(View view) {
            super(view);
            newsHeadingTextView = (TextView) view.findViewById(R.id.newslist_row_heading_textView);
            newsDateTextView = (TextView) view.findViewById(R.id.newslist_row_date_textView);
            newsCategoryTextView = (TextView) view.findViewById(R.id.newslist_row_category_textView);
            newsImageView = (ImageView)view.findViewById(R.id.newslist_row_imageView);

        }
    }


    public NewsListRecyclerAdapter(ArrayList<NewsMetaInfo> newsMetaInfoArrayList) {
        this.newsMetaInfoArrayList = newsMetaInfoArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newslist_recycler_row, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewsMetaInfo newsMetaInfo = newsMetaInfoArrayList.get(position);
        holder.newsHeadingTextView.setText(newsMetaInfo.getNewsHeading());
        holder.newsDateTextView.setText(newsMetaInfo.getNewsDate());
        holder.newsCategoryTextView.setText(newsMetaInfo.getNewsSource());
        holder.newsImageView.setImageBitmap(newsMetaInfo.getNewsImage());
    }

    @Override
    public int getItemCount() {
        return newsMetaInfoArrayList.size();
    }


}
