package com.example.rahman.kejarmovie.activity;

import com.example.rahman.kejarmovie.adapter.*;
import com.example.rahman.kejarmovie.util.*;
import com.example.rahman.kejarmovie.model.*;
import com.example.rahman.kejarmovie.retrofit.*;

import android.content.Intent;
import android.content.res.Configuration;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

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
        SharedPref.context = getApplicationContext();
        Log.i(TAG, ">>>> onCreate: "+SharedPref.getString(SharedPref.LANGUAGE));
        dbConnector = new DatabaseHelper(MainActivity.this);
        showRecyclerGrid();
        SharedPref.saveString(SharedPref.LAST_PAGE, SharedPref.LAST_PAGE_MAINACTIVITY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
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
        Retrofit retrofit = RetrofitMovieClient.getClient(getApplicationContext());
        MovieRetrofit movieRetrofit = retrofit.create(MovieRetrofit.class);
        Call<ResponsePopularMovie> popular = movieRetrofit.getPopular(UtilProperties.API_KEY_MOVIE);
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
                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            int idMovie = list.get(position).getId();
                            SharedPref.saveInt(SharedPref.MOVIE_ID,idMovie);
                            intent.putExtra(DetailActivity.EXTRA_ID_MOVIE, idMovie);
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
        Retrofit retrofit = RetrofitMovieClient.getClient(getApplicationContext());
        MovieRetrofit movieRetrofit = retrofit.create(MovieRetrofit.class);
        Call<ResponseGenres> genresCall= movieRetrofit.listRepos(UtilProperties.API_KEY_MOVIE);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_language_id:
                Log.i(TAG, "onOptionsItemSelected: action_list");
                SharedPref.saveString(SharedPref.LANGUAGE, SharedPref.LANGUAGE_VALUE_INDONESIA);
//                String token = SharedPref.getString(ACCESS_TOKEN);
//                SharedPref.deleteString(ACCESS_TOKEN)
                setLangRecreate("id");
                break;
            case R.id.action_language_en:
                Log.i(TAG, "onOptionsItemSelected: action_grid: "+SharedPref.getString(SharedPref.LANGUAGE));
                SharedPref.saveString(SharedPref.LANGUAGE, SharedPref.LANGUAGE_VALUE_ENGLISH);
                setLangRecreate("en");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    Locale locale;
    public void setLangRecreate(String langval) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }
}
