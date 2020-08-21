package com.example.myapplication;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;

public class BubbleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // doesn't work
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bubble);
        Button the_button = (Button) findViewById(R.id.the_button);

        the_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create bubble intent
                Intent target = new Intent(getApplicationContext(), ThirdActivity.class);
                PendingIntent bubbleIntent = PendingIntent.getActivity(getApplicationContext(), 0, target, 0 /* flags */);

                // Create bubble metadata
                NotificationCompat.BubbleMetadata bubbleData = new NotificationCompat.BubbleMetadata.Builder()
                                .setDesiredHeight(600)
                                .setIcon(IconCompat.createWithResource(getApplicationContext(), R.drawable.ic_launcher_background))
                                .setIntent(bubbleIntent)
                                .build();

                // Create notification
                Person chatBot = new Person.Builder()
                        .setBot(true)
                        .setName("BubbleBot")
                        .setImportant(true)
                        .build();

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "1")
                                .setContentIntent(bubbleIntent)
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setBubbleMetadata(bubbleData)
                                .addPerson(chatBot.toString());
            }
        });

    }
}