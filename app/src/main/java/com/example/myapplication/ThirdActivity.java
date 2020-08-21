package com.example.myapplication;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three);

        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath( "android.resource://" + getPackageName() + "/" + R.raw.main_activity_video);
        videoView.start();
    }
}