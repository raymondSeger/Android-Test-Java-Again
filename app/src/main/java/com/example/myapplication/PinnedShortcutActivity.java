package com.example.myapplication;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;

public class PinnedShortcutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // doesn't work
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinned_shortcut);

        Button create_pinned_shortcut_button = (Button) findViewById(R.id.create_pinned_shortcut_button);

        create_pinned_shortcut_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1){

                    ShortcutManager shortcutManager = getBaseContext().getSystemService(ShortcutManager.class);

                    if (shortcutManager.isRequestPinShortcutSupported()) {

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:0811808060"));
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }

                        ShortcutInfo pinShortcutInfo = new ShortcutInfo.Builder(getBaseContext(), "my-shortcut")
                                .setShortLabel("Short Label")
                                .setLongLabel("Long Label")
                                .setIntent(intent)
                                .build();

                        Intent pinnedShortcutCallbackIntent = shortcutManager.createShortcutResultIntent(pinShortcutInfo);
                        PendingIntent successCallback = PendingIntent.getBroadcast(getBaseContext(),0, pinnedShortcutCallbackIntent, 0);
                        shortcutManager.requestPinShortcut(pinShortcutInfo, successCallback.getIntentSender());
                    }

                }


            }
        });

    }
}