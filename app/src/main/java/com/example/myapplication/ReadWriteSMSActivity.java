package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.view.View.generateViewId;

public class ReadWriteSMSActivity extends AppCompatActivity {

    private LinearLayout linear_layout;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readSMS();
                } else {
                    // Permission Denied
                    Toast.makeText(getBaseContext(), "not allowed, app should exit", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    writeSMS();
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
        setContentView(R.layout.activity_read_write_receive_sms);

        Button read_sms_btn     = (Button) findViewById(R.id.read_sms_btn);
        Button write_sms_btn    = (Button) findViewById(R.id.write_sms_btn);
        Button receive_sms_btn  = (Button) findViewById(R.id.receive_sms_btn);
        linear_layout           = (LinearLayout) findViewById(R.id.linear_layout);

        receive_sms_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(ReadWriteSMSActivity.this, RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(ReadWriteSMSActivity.this, new String[] { RECEIVE_SMS }, 1);
                    return;
                }
            }
        });

        read_sms_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(ReadWriteSMSActivity.this, READ_SMS) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(ReadWriteSMSActivity.this, new String[] { READ_SMS }, 1);
                    return;
                }

                readSMS();
            }
        });

        write_sms_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(ReadWriteSMSActivity.this, SEND_SMS) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(ReadWriteSMSActivity.this, new String[] { SEND_SMS }, 2);
                    return;
                }

                writeSMS();
            }
        });

    }

    private void writeSMS() {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+6281389688115", null, "Test", null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
        }
    }

    private void readSMS() {
        // Create Inbox box URI
        Uri inboxURI = Uri.parse("content://sms/inbox");

        // List required columns
        String[] reqCols = new String[] { "_id", "address", "body", "read", "date", "type"};

        // Get Content Resolver object, which will deal with Content Provider
        ContentResolver cr = getContentResolver();

        // Fetch Inbox SMS Message from Built-in Content Provider
        Cursor c        = cr.query(inboxURI, reqCols, null, null, null);
        int totalSMS    = c.getCount();
        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {
                String id       = c.getString(c.getColumnIndexOrThrow("_id"));
                String address  = c.getString(c.getColumnIndexOrThrow("address"));
                String body     = c.getString(c.getColumnIndexOrThrow("body"));
                String read     = c.getString(c.getColumnIndex("read"));
                String date     = c.getString(c.getColumnIndex("date"));
                if (c.getString(c.getColumnIndex("type")).contains("1")) {
                    String folder_name = "inbox";
                } else {
                    String folder_name = "sent";
                }

                // Make the Content - START
                String text_to_show = String.format(" ID is %s.\n Address is %s.\n Body is %s.\n Read is %s.\n Date is %s", id, address, body, read, date);
                TextView text_view = new TextView(this);
                text_view.setText(text_to_show);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(15, 15, 15, 0);
                text_view.setLayoutParams(params);
                // R.id won't be generated for us, so we need to create one
                text_view.setId(generateViewId());
                // add generated button to view
                linear_layout.addView(text_view);
                // Make the Content - END

                c.moveToNext();
            }
        }
        else {
            Toast.makeText(this, "You have no SMS", Toast.LENGTH_SHORT).show();
        }
        c.close();
    }
}