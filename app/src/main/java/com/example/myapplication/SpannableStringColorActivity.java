package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class SpannableStringColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string_color);

        TextView textView               = findViewById(R.id.text_view);
        String text                     = "I want THIS and THIS to be colored";
        SpannableString ss              = new SpannableString(text);
        SpannableStringBuilder ssb      = new SpannableStringBuilder(text);
        ForegroundColorSpan fcsRed      = new ForegroundColorSpan(Color.RED);
        ForegroundColorSpan fcsGreen    = new ForegroundColorSpan(Color.GREEN);
        BackgroundColorSpan bcsYellow   = new BackgroundColorSpan(Color.YELLOW);

        ssb.setSpan(fcsRed, 7, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(fcsGreen, 16, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(bcsYellow, 27, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.append(" and this to be appended");

        textView.setText(ssb);

    }
}