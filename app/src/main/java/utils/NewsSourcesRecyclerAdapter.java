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
 * Created by bunny on 24/04/17.
 */

public class NewsSourcesRecyclerAdapter extends RecyclerView.Adapter<NewsSourcesRecyclerAdapter.MyViewHolder> {


    private ArrayList<NewsSourceList> newsSourceListArrayList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView newsHeadingTextView, newsSourceTextView, newsSummaryTextView;
        public ImageView newsSourceImageView;

        public MyViewHolder(View view) {
            super(view);
            newsHeadingTextView = (TextView) view.findViewById(R.id.newsSource_row_heading_textView);
            newsSourceTextView = (TextView) view.findViewById(R.id.newsSource_row_source_textView);
            newsSourceImageView = (ImageView) view.findViewById(R.id.newsSource_row_sourceImage_imageView);
            newsSummaryTextView = (TextView) view.findViewById(R.id.newsSource_row_newsSummary_textView);

        }
    }


    public NewsSourcesRecyclerAdapter(ArrayList<NewsSourceList> newsSourceListArrayList) {
        this.newsSourceListArrayList = newsSourceListArrayList;
    }

    @Override
    public NewsSourcesRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newssource_recycler_row, parent, false);


        return new NewsSourcesRecyclerAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(NewsSourcesRecyclerAdapter.MyViewHolder holder, int position) {
        NewsSourceList newsSourceList = newsSourceListArrayList.get(position);
        holder.newsHeadingTextView.setText(newsSourceList.getNewsListHeading());
        holder.newsSourceTextView.setText(newsSourceList.getNewsListSource());
        if (newsSourceList.isExpanded()) {
            holder.newsSummaryTextView.setText(newsSourceList.getNewsListArticle());
            holder.newsSummaryTextView.setTextSize(16f);
        } else {
            holder.newsSummaryTextView.setTextSize(0f);
        }


    }

    @Override
    public int getItemCount() {
        return newsSourceListArrayList.size();
    }


}
