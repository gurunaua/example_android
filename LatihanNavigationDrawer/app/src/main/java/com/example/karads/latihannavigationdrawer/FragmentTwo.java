package com.example.karads.latihannavigationdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.karads.latihannavigationdrawer.util.UtilProperties;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {


    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_two, container, false);
        TextView textView = view.findViewById(R.id.nama_from_config);

        try {
            String name = UtilProperties.getProperty("name", getActivity().getApplicationContext());
            textView.setText(name);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return view;
    }

}
