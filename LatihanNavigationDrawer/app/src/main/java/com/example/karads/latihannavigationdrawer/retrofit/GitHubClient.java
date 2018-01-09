package com.example.karads.latihannavigationdrawer.retrofit;

import com.example.karads.latihannavigationdrawer.model.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by karads on 1/9/2018.
 */

public interface GitHubClient {

    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> repoForUser(@Path("user") String user);

}
