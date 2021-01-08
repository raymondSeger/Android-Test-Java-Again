package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import maes.tech.intentanim.CustomIntent;

public class IntentAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_animation);
    }

    public void openActivity2(View v) {
        Intent intent = new Intent(this, CustomButtonActivity.class);
        startActivity(intent);
        CustomIntent.customType(this, "bottom-to-up");
    }
}