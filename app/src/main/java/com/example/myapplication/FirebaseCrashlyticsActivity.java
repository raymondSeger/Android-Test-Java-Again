package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

public class FirebaseCrashlyticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_crashlytics);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add custom text to Crashanalytics
                // Set a key to a string.
                FirebaseCrashlytics.getInstance().setCustomKey("str_key", "hello");

                throw new RuntimeException("Test Crash"); // Force a crash
            }
        });
    }
}