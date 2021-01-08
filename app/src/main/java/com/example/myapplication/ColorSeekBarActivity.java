package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.rtugeek.android.colorseekbar.ColorSeekBar;

public class ColorSeekBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_seek_bar);

        final TextView textView     = findViewById(R.id.text_view);
        ColorSeekBar colorSeekBar   = findViewById(R.id.color_seek_bar);
        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int i, int i1, int i2) {
                textView.setTextColor(i2);
            }
        });

    }
}