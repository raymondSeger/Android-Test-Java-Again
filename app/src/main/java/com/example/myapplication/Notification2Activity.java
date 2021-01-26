package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
        Button notification_button_2 = (Button) findViewById(R.id.notification_button_2);
        Button notification_button_3 = (Button) findViewById(R.id.notification_button_3);


        notification_button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_notification_1();
            }
        });

        notification_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_notification_2();
            }
        });

        notification_button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_notification_3_not_working_yet();
            }
        });




    }

    private void show_notification_3_not_working_yet() {
        String channelId            = "channel_name_2";
        Intent intent               = new Intent(getBaseContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getBaseContext(), channelId)
                // Show controls on lock screen even when user hides sensitive content.
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.flag_albania)
                // Add media control buttons that invoke intents in your media service
                .addAction(R.drawable.flag_albania, "Previous", pendingIntent) // #0
                .addAction(R.drawable.flag_indonesia, "Pause", pendingIntent)  // #1
                .addAction(R.drawable.flag_afghanistan, "Next", pendingIntent)     // #2
                // Apply the media style template
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1 /* #1: pause button */))
                .setContentTitle("Wonderful music")
                .setContentText("My Awesome Band")
                .setLargeIcon( BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.flag_indonesia) );

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

    private void show_notification_2() {

        //use constant ID for notification used as group summary
        int SUMMARY_ID              = 1;
        String GROUP_KEY_WORK_EMAIL = "com.example_myapplication.WORK_EMAIL";
        String channelId            = "channel_name_2";

        Notification newMessageNotification1 =
                new NotificationCompat.Builder(Notification2Activity.this, channelId)
                        .setSmallIcon(R.drawable.flag_afghanistan)
                        .setContentTitle("content 1")
                        .setContentText("You will not believe...")
                        .setGroup(GROUP_KEY_WORK_EMAIL)
                        .build();

        Notification newMessageNotification2 =
                new NotificationCompat.Builder(Notification2Activity.this, channelId)
                        .setSmallIcon(R.drawable.flag_indonesia)
                        .setContentTitle("Content 2")
                        .setContentText("Please join us to celebrate the...")
                        .setGroup(GROUP_KEY_WORK_EMAIL)
                        .build();

        Notification summaryNotification =
                new NotificationCompat.Builder(Notification2Activity.this, channelId)
                        .setContentTitle("Content 3")
                        //set content text to support devices running API level < 24
                        .setContentText("Two new messages")
                        .setSmallIcon(R.drawable.flag_albania)
                        //build summary info into InboxStyle template
                        .setStyle(new NotificationCompat.InboxStyle()
                                .addLine("Alex Faarborg  Check this out")
                                .addLine("Jeff Chang    Launch Party")
                                .setBigContentTitle("2 new messages")
                                .setSummaryText("janedoe@example.com"))
                        //specify which group this notification belongs to
                        .setGroup(GROUP_KEY_WORK_EMAIL)
                        //set this notification as the summary for the group
                        .setGroupSummary(true)
                        .build();



        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Importance is high", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(3, newMessageNotification1);
        notificationManager.notify(4, newMessageNotification2);
        notificationManager.notify(SUMMARY_ID, summaryNotification);


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