package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.fragments.SharedViewModelFragmentA;
import com.example.myapplication.fragments.SharedViewModelFragmentB;

public class SharedViewModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_view_model);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_a, new SharedViewModelFragmentA())
                .add(R.id.container_b, new SharedViewModelFragmentB())
                .commit();

    }
}