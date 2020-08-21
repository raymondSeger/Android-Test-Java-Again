package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action       = intent.getAction();

        if(action.equals("com.example.myapplication.MY_NOTIFICATION")) {
            String dataString   = intent.getStringExtra("data_from_broadcastContentActivity");
        } else if  ( action.equals("android.intent.action.SCREEN_OFF") ) {

        }

    }
}
