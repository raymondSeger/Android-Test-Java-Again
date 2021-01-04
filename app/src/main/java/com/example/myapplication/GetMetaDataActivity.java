package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class GetMetaDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_meta_data);

        try {
            ApplicationInfo appInfo = getApplicationContext().getPackageManager().getApplicationInfo( getApplicationContext().getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                 String a = appInfo.metaData.getString("com.google.android.geo.API_KEY");
                Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
            }
        } catch (PackageManager.NameNotFoundException e) {
            // if we can’t find it in the manifest, just return null
        }

    }
}