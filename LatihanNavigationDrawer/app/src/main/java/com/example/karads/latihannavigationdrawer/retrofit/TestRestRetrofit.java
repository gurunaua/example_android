package com.example.karads.latihannavigationdrawer.retrofit;

import com.example.karads.latihannavigationdrawer.model.*;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by karads on 1/10/2018.
 */

public interface TestRestRetrofit {

    @POST("/login")
    Call<LoginResponse> doLogin(@Body LoginRequest loginRequest);

}
