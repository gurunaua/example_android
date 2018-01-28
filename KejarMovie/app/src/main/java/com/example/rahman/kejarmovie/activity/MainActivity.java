package com.example.rahman.kejarmovie.activity;

import com.example.rahman.kejarmovie.adapter.*;
import com.example.rahman.kejarmovie.util.*;
import com.example.rahman.kejarmovie.model.*;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rahman.kejarmovie.R;
import com.example.rahman.kejarmovie.model.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    RecyclerView rvMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMovie = findViewById(R.id.rv_movie);
        showRecyclerGrid();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void showRecyclerGrid(){
        rvMovie.setLayoutManager(new GridLayoutManager(this, 2));
        GridMovieAdapter gridMovieAdapter = new GridMovieAdapter(this);
        gridMovieAdapter.setMovies(Movie.getDummyDate());
        rvMovie.setAdapter(gridMovieAdapter);

        new GenresAsyncTask().execute();
    }


    private class GenresAsyncTask extends AsyncTask<Object, Object, Cursor> {
        DatabaseHelper dbConnector = new DatabaseHelper(MainActivity.this);

        @Override
        protected Cursor doInBackground(Object... params) {
            // Open the database
            try (Cursor cursor = dbConnector.getData()) {
                while (cursor.moveToNext()) {
                    Log
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;//dbConnector.ListAllNotes();
        }
        @Override
        protected void onPostExecute(Cursor result) {
            // Close Database
            dbConnector.close();
        }

    }
}
