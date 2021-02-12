package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

public class RingToneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_tone);

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(getBaseContext(), alarmUri);
        ringtone.play();

    }
}