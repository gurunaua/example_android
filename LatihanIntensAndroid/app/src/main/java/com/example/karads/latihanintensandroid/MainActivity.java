package com.example.karads.latihanintensandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.karads.latihanintensandroid.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_pindah_activity, btn_pindah_dg_data, btn_pindah_activity_dg_object;

    private Button btnDialPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btn_pindah_activity = findViewById(R.id.btn_pindah_activity);
        btn_pindah_dg_data = findViewById(R.id.btn_pindah_activity_dg_data);
        btn_pindah_activity_dg_object = findViewById(R.id.btn_pindah_activity_dg_object);
        btnDialPhone = findViewById(R.id.btn_dial_up);

        btn_pindah_activity.setOnClickListener(this);
        btn_pindah_dg_data.setOnClickListener(this);
        btn_pindah_activity_dg_object.setOnClickListener(this);
        btnDialPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_pindah_activity:
                Intent moveIntent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(moveIntent);
                break;
            case R.id.btn_pindah_activity_dg_data:
                List<Object> contohList = new ArrayList<>();
                contohList.add("asda");
                contohList.add(1);
                Intent intent = new Intent(MainActivity.this, PindahDenganDataActivity.class);
                intent.putExtra(PindahDenganDataActivity.EXTRA_NAME,"namasss");
                intent.putExtra(PindahDenganDataActivity.EXTRA_AGE, 29);
//                intent.putStringArrayListExtra(PindahDenganDataActivity.EXTRA_LIST, (ArrayList<Object>) contohList);
                startActivity(intent);
                break;
            case R.id.btn_pindah_activity_dg_object:
                Person person = new Person();
                person.setAge(333);
                person.setName("sdfsd");
                Intent intentO = new Intent(MainActivity.this, PindahDgOnjectActivity.class);
                intentO.putExtra(PindahDgOnjectActivity.EXTRA_OBJECT,person);
//                intent.putStringArrayListExtra(PindahDenganDataActivity.EXTRA_LIST, (ArrayList<Object>) contohList);
                startActivity(intentO);
                break;
            case R.id.btn_dial_up:
                String phone_number="123435435";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone_number));
                startActivity(dialPhoneIntent);
                break;
        }

    }
}
