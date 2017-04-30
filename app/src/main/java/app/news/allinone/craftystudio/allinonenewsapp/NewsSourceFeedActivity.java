package app.news.allinone.craftystudio.allinonenewsapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import utils.NewsInfo;
import utils.NewsSourceList;

public class NewsSourceFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_source_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.finishAffinity(NewsSourceFeedActivity.this);
                Intent intent = new Intent(NewsSourceFeedActivity.this, NewsListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        resolveIntent();
    }

    private void resolveIntent() {
        Intent intent =getIntent();
        NewsSourceList newsSourceList = new NewsSourceList();
        newsSourceList.setNewsListHeading(intent.getStringExtra("NewsHeading"));
        newsSourceList.setNewsListArticle(intent.getStringExtra("NewsArticle"));
        newsSourceList.setSourceIndex(intent.getIntExtra("NewsSourceIndex" ,0));
        String imageLocalPath = intent.getStringExtra("NewsImageLocalPath");

        initializeActivity( newsSourceList , imageLocalPath);


        TextView textView =(TextView)findViewById(R.id.newsSourceFeed_newsSource_textView);
        textView.setText(NewsInfo.resolveDateString(intent.getLongExtra("NewsTime", 0L)));

    }

    private void initializeActivity(NewsSourceList newsSourceList, String imageLocalPath) {
        ImageView imageView = (ImageView) findViewById(R.id.newsSourceFeed_newsImage_ImageView);
        imageView.setImageBitmap(BitmapFactory.decodeFile(imageLocalPath));

        TextView textView = (TextView)findViewById(R.id.newsSourceFeed_newsHeading_textView);
        textView.setText(newsSourceList.getNewsListHeading());

         textView = (TextView)findViewById(R.id.newsSourceFeed_newsSummary_textView);
        textView.setText(newsSourceList.getNewsListArticle());

        imageView = (ImageView) findViewById(R.id.newsSourceFeed_newsSourceImage_imageView);
        imageView.setImageDrawable(NewsSourceList.resolveIconImage( this, newsSourceList.getSourceIndex()));


    }


}
