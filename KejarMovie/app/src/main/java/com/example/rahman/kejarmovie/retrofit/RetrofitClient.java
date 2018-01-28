package com.example.rahman.kejarmovie.retrofit;

/**
 * Created by karads on 1/28/2018.
 */
import com.example.rahman.kejarmovie.util.UtilProperties;
import android.content.Context;
import java.io.IOException;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {
        if (retrofit==null) {
            try {
                retrofit = new Retrofit.Builder()
                        .baseUrl(UtilProperties.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retrofit;
    }

    public static Retrofit getClient(){
        return retrofit;
    }
}
