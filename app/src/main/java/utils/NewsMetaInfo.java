package utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by bunny on 23/04/17.
 */

public class NewsMetaInfo {
    String newsHeading;
    String newsDate;
    String newsPushKeyId;
    Bitmap newsImage;
    String newsSource;
    long newsTime = 0l;
    int newsSourceimageIndex = 0;

    String newsImageLocalPath = "";


    String newsTimeString ="";
    String newsSourceShort="";

    public NewsMetaInfo() {
    }

    public String getNewsSourceShort() {
        return newsSourceShort;
    }

    public void setNewsSourceShort(String newsSourceShort) {
        this.newsSourceShort = newsSourceShort;
    }

    public long getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(long newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsImageLocalPath() {
        return newsImageLocalPath;
    }

    public void setNewsImageLocalPath(String newsImageLocalPath) {
        this.newsImageLocalPath = newsImageLocalPath;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }


    public String getNewsHeading() {
        return newsHeading;
    }

    public void setNewsHeading(String newsHeading) {
        this.newsHeading = newsHeading;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsPushKeyId() {
        return newsPushKeyId;
    }

    public void setNewsPushKeyId(String newsPushKeyId) {
        this.newsPushKeyId = newsPushKeyId;
    }

    public Bitmap getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(Bitmap newsImage) {
        this.newsImage = newsImage;
    }

    public int getNewsSourceimageIndex() {
        return newsSourceimageIndex;
    }

    public void setNewsSourceimageIndex(int newsSourceimageIndex) {
        this.newsSourceimageIndex = newsSourceimageIndex;
    }

    public String getNewsTimeString() {
        return newsTimeString;
    }

    public void setNewsTimeString(String newsTimeString) {
        this.newsTimeString = newsTimeString;
    }

    public void resolveNewsTimeString(){
        this.setNewsTimeString( NewsInfo.resolveDateString(getNewsTime()));
    }

    public boolean resolvenewsLocalImage() {
        if (!newsImageLocalPath.isEmpty()) {
            setNewsImage(BitmapFactory.decodeFile(getNewsImageLocalPath()));
            return true;
        } else {
            return false;
        }
    }

}
