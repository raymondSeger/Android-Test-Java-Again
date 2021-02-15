package com.example.myapplication.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.telecom.Call;
import android.telecom.InCallService;
import android.util.Log;

public class InCallServiceImplementation extends InCallService {

    public Call call;

    @Override
    public void onCallAdded(Call call) {
    }

}

