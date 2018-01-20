package com.example.karads.latihannavigationdrawer;

import com.example.karads.latihannavigationdrawer.model.*;
import com.example.karads.latihannavigationdrawer.retrofit.GitHubClient;
import com.example.karads.latihannavigationdrawer.retrofit.TestRestRetrofit;
import com.example.karads.latihannavigationdrawer.retrofit.RetrofitClient;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRetrofitPost extends Fragment implements View.OnClickListener{

    private Button btn_login;
    private EditText et_username, et_password;
    ProgressDialog progressDialog;
    private TextView tv_message_login;

    public FragmentRetrofitPost() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_retrofit_post, container, false);
        btn_login = view.findViewById(R.id.btn_login);
        et_username = view.findViewById(R.id.et_username);
        et_password = view.findViewById(R.id.et_password);
        tv_message_login = view.findViewById(R.id.tv_message_login);
        btn_login.setOnClickListener(this);
        progressDialog   = new ProgressDialog(getActivity());
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_login){

            progressDialog.setTitle("Please wait...");
            progressDialog.setMessage("loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            Retrofit retrofit = RetrofitClient.getClient();
            TestRestRetrofit restRetrofit = retrofit.create(TestRestRetrofit.class);
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsername(et_username.getText().toString());
            loginRequest.setPassword(et_password.getText().toString());
            Call<LoginResponse> call = restRetrofit.doLogin(loginRequest);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse body = response.body();
                    if(body!=null && body.getStatus().equals(LoginResponse.status_success)){
                        Toast.makeText(getActivity().getApplicationContext(),"berhasil", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(),"gagal", Toast.LENGTH_SHORT).show();
                        tv_message_login.setText("username atau password salah");
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(),"gagal", Toast.LENGTH_SHORT).show();
                    tv_message_login.setText("gagal, periksa koneksi anda");
                    progressDialog.dismiss();
                }
            });
        }
    }
}
