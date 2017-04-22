package app.news.allinone.craftystudio.allinonenewsapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import utils.DatabaseHandlerFirebase;
import utils.NewsInfo;
import utils.ZoomOutPageTransformer;

public class NewsListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    ArrayList<NewsInfo> newsInfoArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initiaizeNewsInfoArrayList();
        initializeViewPager();

    }

    private void initializeViewPager() {

// Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.content_news_list_view_pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // return NewsArticleViewFragment.newInstance("New", newsArrayList.get(position));
            return NewsListFragment.newInstance("newsInfo", newsInfoArrayList.get(position));

        }

        @Override
        public int getCount() {
            //return newsArrayList.size();
            return newsInfoArrayList.size();
        }
    }

    public void initiaizeNewsInfoArrayList() {
        for(int i=0 ;i<10 ; i++) {
            NewsInfo newsInfo = new NewsInfo();
            newsInfo.setNewsDate("20/04/2017");
            newsInfo.setNewsCategory("Technology");
            newsInfo.setNewsHeadline("Heading text nothing to add so just dummy line here and there to test");
            newsInfo.setNewsSummary("hjbjdsa bcdsj cjhewhjbc b eh c hj ch c c jh  c  c whj c  c j ejc ec dsj c  eh  h jh h h h jh hg g dsagj ug cud sagj cwga f bdsa bh edvheb vakh kae veg vuer fcuwe fuc uew vugu  dvahwe agf agf agkg faj fgegagfha fgaf gah ahh f fahj ha ");
            newsInfoArrayList.add(newsInfo);
        }


       /* DatabaseHandlerFirebase databaseHandlerFirebase = new DatabaseHandlerFirebase();
        databaseHandlerFirebase.getNewsList(10);
        databaseHandlerFirebase.addNewsListListner(new DatabaseHandlerFirebase.DataBaseHandlerNewsListListner() {
            @Override
            public void onNewsList(ArrayList<NewsInfo> newsInfoArrayList) {

                for (int i =newsInfoArrayList.size() ; i>0 ; i--){
                    NewsListActivity.this.newsInfoArrayList.add(newsInfoArrayList.get(i));
                }

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onNoticePost(boolean isSuccessful) {

            }
        });*/


    }

}
