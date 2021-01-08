package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

public class CircularProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_progress_bar);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    int i = 0;
                    while (i < 106) {
                        sleep(500);
                        progressBar.setProgress(i);
                        i = i + 5;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }
}