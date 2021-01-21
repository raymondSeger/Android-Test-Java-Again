package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TryBackButtonActivity4 extends AppCompatActivity {

    private Button goToActivity1;
    private Button goToActivity2;
    private Button goToActivity3;
    private Button goToActivity4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_back_button4);

        goToActivity1 = (Button) findViewById(R.id.goToActivity1);
        goToActivity2 = (Button) findViewById(R.id.goToActivity2);
        goToActivity3 = (Button) findViewById(R.id.goToActivity3);
        goToActivity4 = (Button) findViewById(R.id.goToActivity4);

        goToActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TryBackButtonActivity4.this, TryBackButtonActivity.class);
                startActivity(intent);
            }
        });

        goToActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TryBackButtonActivity4.this, TryBackButtonActivity2.class);
                startActivity(intent);
            }
        });

        goToActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TryBackButtonActivity4.this, TryBackButtonActivity3.class);
                startActivity(intent);
            }
        });

        goToActivity4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TryBackButtonActivity4.this, TryBackButtonActivity4.class);
                startActivity(intent);
            }
        });

    }
}