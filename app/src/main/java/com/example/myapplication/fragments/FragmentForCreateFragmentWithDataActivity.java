package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

public class FragmentForCreateFragmentWithDataActivity extends Fragment {

    private static final String ARG_TEXT    = "argText";
    private static final String ARG_NUMBER  = "argNumber";
    private String text;
    private int number;

    public static FragmentForCreateFragmentWithDataActivity newInstance(String text, int number) {
        FragmentForCreateFragmentWithDataActivity fragment  = new FragmentForCreateFragmentWithDataActivity();
        Bundle args                                         = new Bundle();

        args.putString(ARG_TEXT, text);
        args.putInt(ARG_NUMBER, number);

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v              = inflater.inflate(R.layout.fragment_for_create_fragment_with_data_activity, container, false);
        TextView textView   = v.findViewById(R.id.text_view_fragment);

        if (getArguments() != null) {
            text    = getArguments().getString(ARG_TEXT);
            number  = getArguments().getInt(ARG_NUMBER);
        }

        textView.setText(text + number);
        return v;
    }

}