package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class PostDelayedActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_delayed);
    }

    public void startRepeating(View v) {
        //mHandler.postDelayed(mToastRunnable, 5000);
        mToastRunnable.run();
    }

    public void stopRepeating(View v) {
        mHandler.removeCallbacks(mToastRunnable);
    }

    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(PostDelayedActivity.this, "This is a delayed toast", Toast.LENGTH_SHORT).show();
            mHandler.postDelayed(this, 5000);
        }
    };

}