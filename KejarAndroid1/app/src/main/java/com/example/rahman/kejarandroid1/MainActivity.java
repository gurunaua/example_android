package com.example.rahman.kejarandroid1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_judul = findViewById(R.id.tv_judul);
        String [] listJudul = ToyBox.getToyNames();
        for (String s: listJudul)
        tv_judul.append(s+"\n\n");
    }
}
