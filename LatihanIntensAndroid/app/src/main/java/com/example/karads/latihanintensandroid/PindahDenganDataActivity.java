package com.example.karads.latihanintensandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PindahDenganDataActivity extends AppCompatActivity {


    public static String EXTRA_AGE = "extra_age";
    public static String EXTRA_NAME = "extra_name";
    public static String EXTRA_LIST = "extra_list";


    private TextView tvData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pindah_dengan_data);
        tvData = findViewById(R.id.tv_data);

        int age = getIntent().getIntExtra(EXTRA_AGE, 0);
        String name = getIntent().getStringExtra(EXTRA_NAME);

        tvData.setText("Name:"+name+" , Age:"+age);
    }
}
