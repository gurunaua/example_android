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

public interface MovieRetrofit {


    @GET("https://api.themoviedb.org/3/genre/movie/list?language=en-US&api_key=bb17600bd93578f4a3cf1dde1ac920d5")
    Call<List<ResponseGenres>> listRepos();

}
