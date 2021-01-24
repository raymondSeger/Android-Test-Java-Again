package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FirebaseInAppMessagingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_in_app_messaging);

        // check logcat for I/FIAM.Headless: Starting InAppMessaging runtime with Installation ID YOUR_ID
        // then use your ID for testing.
    }
}