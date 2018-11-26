package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News>{

    List<News> mNewsList;

    public NewsAdapter(@NonNull Context context, List<News> newsList) {
        super(context, 0,newsList);
        mNewsList = newsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item,parent,false);
        }
        News currentNews = getItem(position);
        TextView titleTextView = listItemView.findViewById(R.id.title_text_view);
        TextView authorTextView = listItemView.findViewById(R.id.author_text_view);
        TextView dataTextView = listItemView.findViewById(R.id.data_text_view);
        TextView sectionName = listItemView.findViewById(R.id.sectionName);
        titleTextView.setText(currentNews.getTitle());
        authorTextView.setText(currentNews.getAuthor());
        dataTextView.setText(currentNews.getData());
        sectionName.setText(currentNews.getSectionName());


        return listItemView;
    }
}
