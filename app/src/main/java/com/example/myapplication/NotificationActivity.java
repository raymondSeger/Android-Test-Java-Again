package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        Button button       = (Button) findViewById(R.id.the_button);
        Button the_button2  = (Button) findViewById(R.id.the_button2);
        Button the_button3  = (Button) findViewById(R.id.the_button3);
        Button the_button4  = (Button) findViewById(R.id.the_button4);
        Button the_button5  = (Button) findViewById(R.id.the_button5);
        Button the_button6  = (Button) findViewById(R.id.the_button6);
        Button the_button7  = (Button) findViewById(R.id.the_button7);

        the_button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                // Create an explicit intent for an Activity in your app
                Intent intent = new Intent(getApplicationContext(), ReceiveTextActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                    String replyLabel = "Reply";
                    RemoteInput remoteInput = new RemoteInput.Builder("key_text_reply")
                            .setLabel(replyLabel)
                            .build();

                    Intent replyIntent = new Intent(getApplicationContext(), ReceiveTextActivity.class);
                    replyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,replyIntent, PendingIntent.FLAG_ONE_SHOT);

                    // Create the reply action and add the remote input.
                    NotificationCompat.Action action =
                            new NotificationCompat.Action.Builder(R.drawable.ic_launcher_background, "Reply", pendingIntent).addRemoteInput(remoteInput).build();

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "1")
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("My notification")
                            .setContentText("a")
                            .addAction(action)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setSound(defaultSoundUri);

                    NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    notificationManager.notify(88, builder.build());
                }



            }
        });

        the_button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                // Create an explicit intent for an Activity in your app
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "1")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("My notification")
                        .setContentText("Download complete").setProgress(0,0,false) // When done, update the notification one more time to remove the progress bar
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setSound(defaultSoundUri);

                NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(3, builder.build());
            }
        });

        the_button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                // Create an explicit intent for an Activity in your app
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "1")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("My notification")
                        .setContentText("Much longer text that cannot fit one line...")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Much longer text that cannot fit one line...Much longer text that cannot fit one line...Much longer text that cannot fit one line..."))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setSound(defaultSoundUri)
                        .setAutoCancel(true)
                        .addAction(R.drawable.ic_launcher_background, "buttonText", pendingIntent);

                builder.setProgress(100, 50, false);

                NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(3, builder.build());
            }
        });

        the_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                // Create an explicit intent for an Activity in your app
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "1")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("My notification")
                        .setContentText("Much longer text that cannot fit one line...")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Much longer text that cannot fit one line...Much longer text that cannot fit one line...Much longer text that cannot fit one line..."))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setSound(defaultSoundUri)
                        .setAutoCancel(true)
                        .addAction(R.drawable.ic_launcher_background, "buttonText", pendingIntent);

                NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(1, builder.build());
            }
        });

        the_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create an explicit intent for an Activity in your app
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "1")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("My notification")
                        .setContentText("Much longer text that cannot fit one line...")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Much longer text that cannot fit one line...Much longer text that cannot fit one line...Much longer text that cannot fit one line..."))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(1, builder.build());
            }
        });

        the_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "1")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("My notification")
                        .setContentText("Much longer text that cannot fit one line...")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Much longer text that cannot fit one line...Much longer text that cannot fit one line...Much longer text that cannot fit one line..."))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(1, builder.build());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message  = "Message";
                String contentTitle = "Much longer text that cannot fit one line...Much longer text that cannot fit one line...Much longer text that cannot fit one line...";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = "channel_name";
                    String description = "channel_description";
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel channel = new NotificationChannel("1", name, importance);
                    channel.setDescription(description);
                    // Register the channel with the system; you can't change the importance
                    // or other notification behaviors after this
                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "1")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(contentTitle)
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(0 /* ID of notification */, builder.build());
            }
        });

        int b = 0;
    }
}