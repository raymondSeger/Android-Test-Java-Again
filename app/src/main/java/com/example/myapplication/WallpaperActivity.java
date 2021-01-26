package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;

public class WallpaperActivity extends AppCompatActivity {

    // BIND_WALLPAPER is not allowed, LIVE wallpaper is not doable for "3rd party apps"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        WallpaperManager wallpaper_manager = WallpaperManager.getInstance(getApplicationContext());

        try {
            wallpaper_manager.setBitmap( BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.flag_afghanistan) );
        } catch (IOException e){
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(),"Wallpaper changed",Toast.LENGTH_SHORT).show();
    }
}