package com.example.myapplication.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.provider.Telephony;

public class ReceiveSMS extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle myBundle = intent.getExtras();
        if (myBundle != null) {
            SmsMessage[] rawSmsChunks = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            for (SmsMessage rawSmsChunk : rawSmsChunks) {
                if (rawSmsChunk != null) {
                    String sender   = rawSmsChunk.getDisplayOriginatingAddress();
                    String smsChunk = rawSmsChunk.getDisplayMessageBody();
                }
            }

        }
    }
}
