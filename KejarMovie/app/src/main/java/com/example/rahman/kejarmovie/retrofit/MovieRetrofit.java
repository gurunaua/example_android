package com.example.rahman.kejarmovie.retrofit;

/**
 * Created by karads on 1/28/2018.
 */

import com.example.rahman.kejarmovie.model.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieRetrofit {

    @GET("genre/movie/list")
    Call<ResponseGenres> listRepos(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<ResponsePopularMovie> getPopular(@Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Call<ResponseReview> getReview(@Query("api_key") String apiKey, @Path("id") String idMovie);

    @GET("movie/{id}")
    Call<ResponsePopularMovie> getDetail(@Query("api_key") String apiKey, @Path("id") String idMovie);


}
