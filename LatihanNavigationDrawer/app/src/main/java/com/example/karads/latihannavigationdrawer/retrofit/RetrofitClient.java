package com.example.karads.latihannavigationdrawer.retrofit;

import android.content.Context;

import com.example.karads.latihannavigationdrawer.util.UtilProperties;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by karads on 1/10/2018.
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {
        if (retrofit==null) {
            try {
                retrofit = new Retrofit.Builder()
                        .baseUrl(UtilProperties.getProperty("base.url.retrofit", context))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return retrofit;
    }

    public static Retrofit getClient(){
        return retrofit;
    }

}
