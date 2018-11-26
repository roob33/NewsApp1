package com.example.android.newsapp;

class News {
    private String mTitle;
    private String mAuthor;
    private String mUrl;
    private String mData;
    private String mSectionName;


    public News(String title, String author, String url, String data, String sectionName) {
        mTitle = title;
        mAuthor = author;
        mUrl = url;
        mData = data;
        mSectionName = sectionName;
    }


    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getData() {
        return mData;
    }

    public String getSectionName() {
        return mSectionName;
    }
}
