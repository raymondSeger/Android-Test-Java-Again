package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class BlankFragment2 extends Fragment {

    private String mParam1;
    private String mParam2;

    public BlankFragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("param1");
            mParam2 = getArguments().getString("param2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_blank2, container, false);

        TextView textview1 = fragmentView.findViewById(R.id.textview1);
        TextView textview2 = fragmentView.findViewById(R.id.textview2);

        textview1.setText(mParam1);
        textview2.setText(mParam2);

        Button button_in_fragment = (Button) fragmentView.findViewById(R.id.button_in_fragment);

        button_in_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Hello from Fragment 2", Toast.LENGTH_LONG).show();
            }
        });

        return fragmentView;
    }
}