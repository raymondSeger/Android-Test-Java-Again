package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Notification2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification2);

        Button notification_button_1 = (Button) findViewById(R.id.notification_button_1);


        notification_button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_notification_1();
            }
        });

    }

    private void show_notification_1() {

        String channelId    = "channel_name_2";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon( R.drawable.flag_afghanistan )
                        .setContentTitle( "Title" )
                        .setContentText( "Message" )
                        .setColor(Color.BLUE)
                        .setLargeIcon( BitmapFactory.decodeResource(getApplicationContext().getResources(),
                                R.drawable.flag_indonesia) )
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture( BitmapFactory.decodeResource(getApplicationContext().getResources(),
                                        R.drawable.flag_indonesia) )
                                .bigLargeIcon( BitmapFactory.decodeResource(getApplicationContext().getResources(),
                                        R.drawable.flag_albania) ) )
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setPriority(Notification.PRIORITY_MAX);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Importance is high", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        Notification the_notification = notificationBuilder.build();
        // if you add this, the user can't remove the notification
        // the_notification.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;

        notificationManager.notify(0 /* ID of notification */, the_notification );

    }
}