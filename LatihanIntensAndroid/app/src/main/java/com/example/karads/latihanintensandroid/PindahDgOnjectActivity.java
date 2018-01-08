package com.example.karads.latihanintensandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.karads.latihanintensandroid.model.Person;

public class PindahDgOnjectActivity extends AppCompatActivity {

    private TextView tvData;

    public static String EXTRA_OBJECT = "extra_object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pindah_dg_onject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvData = findViewById(R.id.textViewO);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Person person = (Person) getIntent().getSerializableExtra(EXTRA_OBJECT);

        tvData.setText(person.getName()+"===="+person.getAge());
    }

}
