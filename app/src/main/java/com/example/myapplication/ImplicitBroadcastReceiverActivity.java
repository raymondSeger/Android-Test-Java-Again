package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

import com.example.myapplication.receivers.ImplicitBroadcastReceiver;

public class ImplicitBroadcastReceiverActivity extends AppCompatActivity {

    ImplicitBroadcastReceiver the_object = new ImplicitBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_broadcast);

        IntentFilter filter = new IntentFilter("com.raymond.EXAMPLE_ACTION");
        registerReceiver(the_object, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(the_object);
    }
}