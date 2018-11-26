package com.example.android.newsapp;

import android.content.Context;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

import static android.content.ContentValues.TAG;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<News> newsList = QueryUtils.fetchNewsData(mUrl);
        if (newsList.size() == 0) {
            Log.d(TAG, "news list is empty");
        } else {
            Log.d(TAG, newsList.get(0).getTitle());
        }

        return newsList;
    }
}
