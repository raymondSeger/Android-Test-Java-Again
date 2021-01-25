package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class CallPhoneAndDialSimpleVersionActivity extends AppCompatActivity {

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    action_call();
                } else {
                    // Permission Denied
                    Toast.makeText(getBaseContext(), "not allowed, app should exit", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone_and_dial_simple_version);

        Button action_call_btn = (Button) findViewById(R.id.action_call_btn);
        Button action_dial_btn = (Button) findViewById(R.id.action_dial_btn);

        action_call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(CallPhoneAndDialSimpleVersionActivity.this, CALL_PHONE) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(CallPhoneAndDialSimpleVersionActivity.this, new String[] { CALL_PHONE }, 1);
                    return;
                }

                action_call();
            }
        });

        action_dial_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action_dial();
            }
        });

    }

    private void action_dial() {
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:0612312312"));
        startActivity(i);
    }

    private void action_call() {
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:0612312312"));
        startActivity(i);
    }
}