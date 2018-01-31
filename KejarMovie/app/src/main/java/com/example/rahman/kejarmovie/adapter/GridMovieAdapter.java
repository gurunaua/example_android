package com.example.rahman.kejarmovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.example.rahman.kejarmovie.R;
import com.example.rahman.kejarmovie.model.*;

import java.util.ArrayList;

/**
 * Created by karads on 1/27/2018.
 */

public class GridMovieAdapter extends RecyclerView.Adapter <GridMovieAdapter.GridViewHolder>{

    private static String BASE_URL_IMAGE= "http://image.tmdb.org/t/p/w185";

    private Context context;
    private ArrayList<MovieInfo> movies;

    public GridMovieAdapter(Context context){
        this.context = context;
    }

    public ArrayList<MovieInfo> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MovieInfo> movies) {
        this.movies = movies;
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_movie, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
        Glide.with(context).load(BASE_URL_IMAGE+getMovies().get(position).getPosterPath())
                .override(350, 550).crossFade()
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return getMovies().size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder{

        ImageView movieImage;
        public GridViewHolder(View itemView) {
            super(itemView);
            movieImage = (ImageView)itemView.findViewById(R.id.img_item_photo);
        }
    }
}