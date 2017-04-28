package app.news.allinone.craftystudio.allinonenewsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import utils.ClickListener;
import utils.DatabaseHandlerFirebase;
import utils.NewsInfo;
import utils.NewsMetaInfo;
import utils.NewsSourceList;
import utils.NewsSourcesRecyclerAdapter;
import utils.RecyclerTouchListener;

public class NewsFeedActivity extends AppCompatActivity {
    public NewsMetaInfo newsMetaInfo = new NewsMetaInfo();
    public NewsInfo newsInfo = null;
    NewsSourcesRecyclerAdapter newsSourcesRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShareClick();
            }
        });

        resolveIntent();
    }

    private void resolveIntent() {

        Intent intent = getIntent();
        boolean isDynamicLink= intent.getBooleanExtra("ByLink", false);

        if(isDynamicLink){
            fetchNewsInfo(false);
        }
        else {
            newsMetaInfo.setNewsHeading(intent.getStringExtra("NewsHeading"));
            newsMetaInfo.setNewsPushKeyId(intent.getStringExtra("PushKeyId"));

            newsMetaInfo.setNewsImageLocalPath(intent.getStringExtra("NewsImageLocalPath"));

            if (!newsMetaInfo.resolvenewsLocalImage()) {
                fetchNewsInfo(false);
            }

            fetchNewsInfo(true);

            TextView textView = (TextView) findViewById(R.id.newsFeed_newsHeading_textView);
            textView.setText(newsMetaInfo.getNewsHeading());

            ImageView imageView = (ImageView) findViewById(R.id.newsFeed_newsImage_ImageView);
            imageView.setImageBitmap(newsMetaInfo.getNewsImage());
        }

    }

    private void fetchNewsInfo(boolean isfetchImage) {
        DatabaseHandlerFirebase databaseHandlerFirebase = new DatabaseHandlerFirebase();
        databaseHandlerFirebase.addNewsListListner(new DatabaseHandlerFirebase.DataBaseHandlerNewsListListner() {
            @Override
            public void onNewsList(ArrayList<NewsMetaInfo> newsMetaInfoArrayList) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onNoticePost(boolean isSuccessful) {

            }

            @Override
            public void onNewsImageFetched(boolean isFetchedImage) {

                ImageView imageView = (ImageView) findViewById(R.id.newsFeed_newsImage_ImageView);
                imageView.setImageBitmap(newsMetaInfo.getNewsImage());


            }

            @Override
            public void onNewsInfo(NewsInfo newsInfo) {

                NewsFeedActivity.this.newsInfo = newsInfo;
                initializeActivity();
            }

            @Override
            public void ongetNewsListMore(ArrayList<NewsMetaInfo> newsMetaInfoArrayListMore) {

            }
        });
        databaseHandlerFirebase.getNewsInfo(newsMetaInfo.getNewsPushKeyId());
        if (isfetchImage) {
            databaseHandlerFirebase.downloadImageFromFireBase(newsMetaInfo);
        }
    }

    private void initializeActivity() {
        TextView textView = (TextView) findViewById(R.id.newsFeed_newsHeading_textView);
        textView.setText(newsInfo.getNewsHeadline());
        textView = (TextView) findViewById(R.id.newsFeed_newsSummary_textView);
        textView.setText(newsInfo.getNewsSummary());
        textView = (TextView) findViewById(R.id.newsFeed_newsSource_textView);
        textView.setText(newsInfo.getNewsSource());

        initializeRecyclerView();

    }

    private void initializeRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.newsFeed_newsSourceList_recyclerView);
        newsInfo.resolveHashmap();
        newsSourcesRecyclerAdapter = new NewsSourcesRecyclerAdapter(newsInfo.getNewsSourceListArrayList(),this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(newsSourcesRecyclerAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(NewsFeedActivity.this, "Item clicked = " + position, Toast.LENGTH_SHORT).show();
                openNewsIndex(position);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }

    private void openNewsIndex(int position) {

        /*boolean currentValue = newsInfo.getNewsSourceListArrayList().get(position).isExpanded();

        for (NewsSourceList newsSourceList : newsInfo.getNewsSourceListArrayList()){
            newsSourceList.setExpanded(false);
        }

        if (currentValue) {
            newsInfo.getNewsSourceListArrayList().get(position).setExpanded(false);
        } else {
            newsInfo.getNewsSourceListArrayList().get(position).setExpanded(true);
        }


        newsSourcesRecyclerAdapter.notifyDataSetChanged();
*/

        Intent intent = new Intent(this , NewsSourceFeedActivity.class);
        intent.putExtra("NewsHeading",newsInfo.getNewsSourceListArrayList().get(position).getNewsListHeading());
        intent.putExtra("NewsArticle",newsInfo.getNewsSourceListArrayList().get(position).getNewsListArticle());
        intent.putExtra("NewsSourceIndex",newsInfo.getNewsSourceListArrayList().get(position).getSourceIndex());
        intent.putExtra("NewsImageLocalPath" , newsMetaInfo.getNewsImageLocalPath());


        startActivity(intent);

    }


    public void onShareClick(){

        String appCode = getString(R.string.app_code);
        String appName = getString(R.string.app_name);
        String packageName = getString(R.string.package_name);

        String utmSource = getString(R.string.utm_source);
        String utmCampaign = getString(R.string.utm_campaign);
        String utmMedium = getString(R.string.utm_medium);

        String url = "https://" + appCode + ".app.goo.gl/?link=https://AllInOneNews.com/"
                +newsMetaInfo.getNewsPushKeyId()
                + "&apn=" +
                packageName + "&st=" +
                newsInfo.getNewsHeadline()
                + "&sd=" +
                appName + "&utm_source=" +
                utmSource + "&utm_medium=" +
                utmMedium + "&utm_campaign=" +
                utmCampaign;

        // Toast.makeText(this, "Shared an article " + url, Toast.LENGTH_SHORT).show();

        url = url.replaceAll(" ", "+");

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");

        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Download the app and Start reading");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, url + " \nHey read this editorial");
        startActivity(Intent.createChooser(sharingIntent, "Share Editorial via"));


    }


}