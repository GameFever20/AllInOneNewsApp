package utils;

public class NewsSourceList {
    String newsListSource;
    String newsListHeading;
    String newsListLink;
    String newsListArticle;


    boolean isExpanded=false;


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


}
