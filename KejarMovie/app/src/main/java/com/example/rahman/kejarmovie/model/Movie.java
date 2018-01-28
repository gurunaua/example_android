package com.example.rahman.kejarmovie.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karads on 1/27/2018.
 */

public class Movie {

    private String title;
    private String imageUrl;
    private String desc;

    public Movie(){}

    public Movie(String title, String imageUrl, String desc) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ArrayList<Movie> getDummyDate(){
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Title 1", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/01/Presiden_Sukarno.jpg/418px-Presiden_Sukarno.jpg", "Desc 1"));
        movies.add(new Movie("Title 2", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/President_Suharto%2C_1993.jpg/468px-President_Suharto%2C_1993.jpg", "Desc 1"));
        movies.add(new Movie("Title 3", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/01/Presiden_Sukarno.jpg/418px-Presiden_Sukarno.jpg", "Desc 1"));
        movies.add(new Movie("Title 4", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/01/Presiden_Sukarno.jpg/418px-Presiden_Sukarno.jpg", "Desc 1"));

        return movies;
    }
}
