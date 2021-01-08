package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.fragments.FragmentForCreateFragmentWithDataActivity;

public class CreateFragmentWithDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fragment_with_data);

        FragmentForCreateFragmentWithDataActivity fragment = FragmentForCreateFragmentWithDataActivity.newInstance("example text ", 123);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

    }
}