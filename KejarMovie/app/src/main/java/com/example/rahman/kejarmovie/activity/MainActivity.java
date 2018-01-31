package com.example.rahman.kejarmovie.activity;

import com.example.rahman.kejarmovie.adapter.*;
import com.example.rahman.kejarmovie.util.*;
import com.example.rahman.kejarmovie.model.*;
import com.example.rahman.kejarmovie.retrofit.*;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private String apiKey = "bb17600bd93578f4a3cf1dde1ac920d5";
    private static final String TAG = MainActivity.class.getName();
    ProgressDialog progressDialog;
    private List<Genre> genres = new ArrayList<>();
    private DatabaseHelper dbConnector ;
    private ArrayList<MovieInfo> list;

    RecyclerView rvMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMovie = findViewById(R.id.rv_movie);
        dbConnector = new DatabaseHelper(MainActivity.this);
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
//        gridMovieAdapter.setMovies(Movie.getDummyDate());


        progressDialog   = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

//        new GenresAsyncTask().execute();

        getMoviePopular(gridMovieAdapter);
    }


    private class GenresAsyncTask extends AsyncTask<Object, Object, List<Genre>> {
        DatabaseHelper dbConnector = new DatabaseHelper(MainActivity.this);

        @Override
        protected List<Genre> doInBackground(Object... params) {
            // Open the database
            try (Cursor cursor = dbConnector.getData()) {
                while (cursor.moveToNext()) {
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                    Log.d(TAG, "sql Lite: Genres: id    :"+cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ID)));
                    Log.d(TAG, "sql Lite: Genres: name  :"+cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME)));
                    genres.add(new Genre(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID)),
                            cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME))));
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return genres;//dbConnector.ListAllNotes();
        }

        @Override
        protected void onPostExecute(List<Genre> result) {
            // Close Database
            dbConnector.close();
            if(!genres.isEmpty()){
                progressDialog.dismiss();
            }else{
                getGenresFromApi();
            }
        }

    }

    private void getMoviePopular(final GridMovieAdapter gridMovieAdapter){
        Retrofit retrofit = RetrofitClient.getClient(getApplicationContext());
        MovieRetrofit movieRetrofit = retrofit.create(MovieRetrofit.class);
        Call<ResponsePopularMovie> popular = movieRetrofit.getPopular(apiKey);
        popular.enqueue(new Callback<ResponsePopularMovie>() {
            @Override
            public void onResponse(Call<ResponsePopularMovie> call, Response<ResponsePopularMovie> response) {
                ResponsePopularMovie body = response.body();
                if(body!=null && body.getResults()!=null && body.getResults().size()>0){
                    list = new ArrayList<MovieInfo>(body.getResults());
                    gridMovieAdapter.setMovies(list);
                    rvMovie.setAdapter(gridMovieAdapter);

                    ItemClickSupport.addTo(rvMovie).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                        @Override
                        public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                            showSelectedPresident(list.get(position));
                            Intent intent = new Intent(MainActivity.this, DetailMovieActivity.class);
                            intent.putExtra(DetailMovieActivity.EXTRA_ID_MOVIE, list.get(position).getId());
                            startActivity(intent);
                        }
                    });
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponsePopularMovie> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }

    private void getGenresFromApi() {
        Log.d(TAG, "getGenresFromApi: ");
        Retrofit retrofit = RetrofitClient.getClient(getApplicationContext());
        MovieRetrofit movieRetrofit = retrofit.create(MovieRetrofit.class);
        Call<ResponseGenres> genresCall= movieRetrofit.listRepos(apiKey);
        genresCall.enqueue(new Callback<ResponseGenres>() {
            @Override
            public void onResponse(Call<ResponseGenres> call, Response<ResponseGenres> response) {
                ResponseGenres body = response.body();
                genres = body.getGenres();
                for(Genre genre: genres){
                    if(dbConnector.addData(genre.getId(), genre.getName())){
                        Log.d(TAG, "sqlLite saved "+genre.toString());
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseGenres> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }

    private void showSelectedPresident(MovieInfo movieInfo){
        Toast.makeText(this, "Kamu memilih "+movieInfo.getTitle(), Toast.LENGTH_SHORT).show();
    }

}
