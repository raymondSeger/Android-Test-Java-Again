package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SlidrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidr);
    }

    public void openActivity2(View v) {
        Intent intent = new Intent(this, Slidr2Activity.class);
        startActivity(intent);
    }

}