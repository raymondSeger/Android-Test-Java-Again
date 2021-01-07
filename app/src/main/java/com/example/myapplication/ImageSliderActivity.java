package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.myapplication.adapters.ImageAdapter;

public class ImageSliderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);

        ViewPager viewPager     = findViewById(R.id.viewPager);
        ImageAdapter adapter    = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
    }
}