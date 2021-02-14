package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import static android.Manifest.permission.PROCESS_OUTGOING_CALLS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class InterceptCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intercept_call);


        if ( ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, PROCESS_OUTGOING_CALLS) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(InterceptCallActivity.this, new String[] { READ_PHONE_STATE, PROCESS_OUTGOING_CALLS }, 1);
            return;
        }

    }
}