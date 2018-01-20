package com.example.rahman.kejarandroid1.model;

import java.util.List;

/**
 * Created by karads on 1/20/2018.
 */

public class MovieResponse {

    private String page;
    private List<Movie> results;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
