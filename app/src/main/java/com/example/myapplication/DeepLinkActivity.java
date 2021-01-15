package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class DeepLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);

        TextView textView5 = (TextView) findViewById(R.id.textView5);

        Intent intent   = getIntent();
        String action   = intent.getAction();
        Uri data        = intent.getData(); // https://www.example.com/hello/world?a=12

        // Try with adb shell am start -W -a android.intent.action.VIEW -d "https://www.example.com/hello/world?a=12" com.example.myapplication
        // in real world, add this to site: https://developer.android.com/training/app-links/verify-site-associations
        if(data != null) {
            textView5.setText( data.toString() );
        }
        int a           = 0;
    }
}