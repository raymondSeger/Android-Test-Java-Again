package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class StringFormatterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_formatter);

        // ordinary string
        String string1      = getString(R.string.hello);
        TextView textView1  = (TextView) findViewById(R.id.textView1);
        textView1.setText(string1);

        // plural string
        Resources res       = getResources();
        String[] planets    = res.getStringArray(R.array.planets_array);
        TextView textView2  = (TextView) findViewById(R.id.textView2);
        textView2.setText( TextUtils.join(",", planets) );

        // quantity string
        int count1          = 3;
        int count2          = 1;
        Resources res2      = getResources();
        String text1        = res2.getQuantityString(R.plurals.numberOfSongsAvailable, count1, count1);
        String text2        = res2.getQuantityString(R.plurals.numberOfSongsAvailable, count2, count2);
        TextView textView3  = (TextView) findViewById(R.id.textView3);
        TextView textView4  = (TextView) findViewById(R.id.textView4);
        textView3.setText( text1 );
        textView4.setText( text2 );

    }
}