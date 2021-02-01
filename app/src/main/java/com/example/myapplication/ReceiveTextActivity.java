package com.example.myapplication;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

public class ReceiveTextActivity extends AppCompatActivity {

    // to get data from notification

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle remoteInput = RemoteInput.getResultsFromIntent(getIntent());
        if (remoteInput != null) {

            CharSequence a = remoteInput.getCharSequence("key_text_reply");

            // Build a new notification, which informs the user that the system
            // handled their interaction with the previous notification.
            Notification repliedNotification = new NotificationCompat.Builder(getApplicationContext(), "1")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentText("replied!")
                    .build();

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(88, repliedNotification);
        }
    }

}