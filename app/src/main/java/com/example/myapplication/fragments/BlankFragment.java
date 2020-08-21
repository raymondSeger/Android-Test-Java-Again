package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class BlankFragment extends Fragment {
    private String mParam1;
    private String mParam2;

    public BlankFragment() {
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
        final View fragmentView = inflater.inflate(R.layout.fragment_blank, container, false);

        TextView textview1 = fragmentView.findViewById(R.id.textview1);
        TextView textview2 = fragmentView.findViewById(R.id.textview2);

        textview1.setText(mParam1);
        textview2.setText(mParam2);

        Button send_data_to_other_fragment = (Button) fragmentView.findViewById(R.id.send_data_to_other_fragment);

        send_data_to_other_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle result = new Bundle();
                result.putString("key1", "test data");
                getParentFragmentManager().setFragmentResult("data_from_blank_fragment", result); // alpha feature
            }
        });

        return fragmentView;
    }
}