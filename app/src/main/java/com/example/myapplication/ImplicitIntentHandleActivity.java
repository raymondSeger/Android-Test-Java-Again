package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class ImplicitIntentHandleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent_handle);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        String receivedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        int a = 0; // debug here
    }
}