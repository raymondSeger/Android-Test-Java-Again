package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tomer.fadingtextview.FadingTextView;

import static com.tomer.fadingtextview.FadingTextView.MILLISECONDS;

public class FadingRandomizedTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fading_randomized_text_view);

        FadingTextView fadingTextView = findViewById(R.id.fading_text_view);
        fadingTextView.setTimeout(500, MILLISECONDS);
    }
}