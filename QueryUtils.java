package com.example.android.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    private static final String TAG = QueryUtils.class.getSimpleName();

    public static List<News> fetchNewsData(String stringUrl) {
        if (stringUrl == null) {
            return null;
        }

        URL url = createUrl(stringUrl);
        String jsonResponse = makeHttpRequest(url);

        List<News> newsList = extractDataFromJson(jsonResponse);

        return newsList;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        if (stringUrl == null) {
            return null;
        }

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String makeHttpRequest(URL url) {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "makeHttpRequest: " + jsonResponse);
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) {
        StringBuilder output = new StringBuilder();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader reader = new BufferedReader(inputStreamReader);
        try {
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    static List<News> extractDataFromJson(String jsonResponse) {

        List<News> newsArrayList = new ArrayList<>();
        String title;
        String data;
        String url;
        String sectionName;
        try {
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);

            JSONObject baseJsonResults = baseJsonResponse.getJSONObject("response");
            JSONArray newsArray = baseJsonResults.getJSONArray("results");

            for (int i = 0; i < newsArray.length(); i++) {
                JSONObject currentNews = newsArray.getJSONObject(i);

                String author = "(unknown author)";
                if (currentNews.has("fields")) {
                    JSONObject fieldsObject = currentNews.getJSONObject("fields");

                    if (fieldsObject.has("byline")) {
                        author = fieldsObject.optString("byline");
                    }
                }

                title = currentNews.optString("webTitle");
                sectionName = currentNews.getString("sectionName");
                url = currentNews.getString("webUrl");
                data = currentNews.getString("webPublicationDate");


                newsArrayList.add(new News(title, author, url, data, sectionName));

            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }
        return newsArrayList;
    }

}


