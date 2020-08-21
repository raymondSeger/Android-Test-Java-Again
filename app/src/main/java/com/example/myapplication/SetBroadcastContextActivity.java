package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;

public class SetBroadcastContextActivity extends AppCompatActivity {

    public BroadcastReceiver br;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(br);
        } catch (Exception e) {
            // Error occurred when opening raw file for reading.
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_broadcast_context);

        Button set_broadcast_button     = (Button) findViewById(R.id.set_broadcast_button);
        Button remove_broadcast_button  = (Button) findViewById(R.id.remove_broadcast_button);

        set_broadcast_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                br = new MyBroadcastReceiver();

                IntentFilter screenStateFilter = new IntentFilter();
                screenStateFilter.addAction(Intent.ACTION_SCREEN_ON);
                screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);
                screenStateFilter.addAction("com.example.myapplication.MY_NOTIFICATION");
                getApplicationContext().registerReceiver(br, screenStateFilter);

                Intent intent = new Intent();
                intent.setAction("com.example.myapplication.MY_NOTIFICATION");
                intent.putExtra("data_from_broadcastContentActivity","Notice me senpai!");
                sendBroadcast(intent);

            }
        });

        remove_broadcast_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    unregisterReceiver(br);
                } catch (Exception e) {
                    // Error occurred when opening raw file for reading.
                }

            }
        });

    }
}