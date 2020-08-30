package com.example.myapplication.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class CustomFragmentForTutorial extends Fragment {

    public int imageData;
    public String headline;
    public String smaller_text;

    public CustomFragmentForTutorial() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public CustomFragmentForTutorial(int data, String headline, String smaller_text) {
        this.imageData      = data;
        this.headline       = headline;
        this.smaller_text   = smaller_text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragment                 = inflater.inflate(R.layout.fragment_for_tutorial_activity, container, false);

        ImageView the_picture               = (ImageView) fragment.findViewById(R.id.the_picture);
        TextView headline_text_text_view    = (TextView) fragment.findViewById(R.id.headline_text);
        TextView smaller_text_text_view     = (TextView) fragment.findViewById(R.id.smaller_text);

        the_picture.setImageResource(imageData);
        headline_text_text_view.setText(headline);
        smaller_text_text_view.setText(smaller_text);

        return fragment;
    }

}