package com.example.karads.latihannavigationdrawer;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.karads.latihannavigationdrawer.model.GitHubRepo;
import com.example.karads.latihannavigationdrawer.model.GitHubRepoAdapter;
import com.example.karads.latihannavigationdrawer.retrofit.GitHubClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRetrofit extends Fragment {

    ListView listView;


    public FragmentRetrofit() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_fragment_retrofit, container, false);
        listView = (ListView) v.findViewById(R.id.list_view_github);


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        GitHubClient gitHubClient = retrofit.create(GitHubClient.class);
        Call<List<GitHubRepo>> call = gitHubClient.repoForUser("gurunaua");
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                List<GitHubRepo> bodies = response.body();
                if(bodies!=null)
                    listView.setAdapter(new GitHubRepoAdapter(getActivity(),bodies));

            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(getActivity(), "failure request", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        return v;

    }

}
