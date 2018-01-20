package com.example.rahman.kejarandroid1;

import android.graphics.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rahman.kejarandroid1.model.Movie;
import com.google.gson.Gson;

import com.example.rahman.kejarandroid1.util.NetworkUtils;
import com.example.rahman.kejarandroid1.model.*;
import com.example.rahman.kejarandroid1.util.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieDbActivity extends AppCompatActivity {

    private static String TAG = MovieDbActivity.class.getName();

    ProgressBar pb_loading_indicator;
    TextView tv_judul_movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_db);

        pb_loading_indicator =findViewById(R.id.pb_loading_indicator_movie1);
        tv_judul_movie = findViewById(R.id.tv_judul_movie);
        URL githubSearchUrl = MoviewDbNetworkUtils.buildUrl();
        new MovieDbActivity.GithubQueryTask().execute(githubSearchUrl);
    }

    private void showJsonDataView(List<String> list) {
        tv_judul_movie.setText("");
        for (String judul:list ) {
            tv_judul_movie.append(judul+"\n\n\n");
        }

    }

    private void showErrorMessage() {
        tv_judul_movie.setText("Error Url.....");
    }


    public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb_loading_indicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                Log.i(TAG, "doInBackground: url"+searchUrl);
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            Log.i(TAG, "onPostExecute: kelar");
            // COMPLETED (27) As soon as the loading is complete, hide the loading indicator
            pb_loading_indicator.setVisibility(View.INVISIBLE);
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                // COMPLETED (17) Call showJsonDataView if we have valid, non-null results
                Log.i(TAG, "onPostExecute: githubSearchResults:"+githubSearchResults);
                Gson gson = new Gson();
                MovieResponse response = gson.fromJson(githubSearchResults, MovieResponse.class);
                List<String> stringList = new ArrayList<>();
                for (Movie movie : response.getResults()){
                    stringList.add(movie.getTitle());
                }
                showJsonDataView(stringList);
            } else {
                // COMPLETED (16) Call showErrorMessage if the result is null in onPostExecute
                showErrorMessage();
            }
        }
    }

}
