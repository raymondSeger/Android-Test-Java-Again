package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.WRITE_CALL_LOG;
import static android.view.View.generateViewId;

public class ReadWriteCallLogsActivity extends AppCompatActivity {

    public LinearLayout linear_layout;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeContent();
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
        setContentView(R.layout.activity_read_write_call_logs);

        linear_layout = (LinearLayout) findViewById(R.id.linear_layout);

        if (ActivityCompat.checkSelfPermission(ReadWriteCallLogsActivity.this, READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ReadWriteCallLogsActivity.this, WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(ReadWriteCallLogsActivity.this, new String[] { READ_CALL_LOG, WRITE_CALL_LOG }, 1);
            return;
        }

        makeContent();

        // write call logs
        /*
        ContentValues values = new ContentValues();
        values.put(CallLog.Calls.NUMBER, "+628112866066");
        values.put(CallLog.Calls.DATE, System.currentTimeMillis());
        values.put(CallLog.Calls.DURATION, 0);
        values.put(CallLog.Calls.TYPE, CallLog.Calls.OUTGOING_TYPE);
        values.put(CallLog.Calls.NEW, 1);
        values.put(CallLog.Calls.CACHED_NAME, "");
        values.put(CallLog.Calls.CACHED_NUMBER_TYPE, 0);
        values.put(CallLog.Calls.CACHED_NUMBER_LABEL, "");
        getBaseContext().getContentResolver().insert(CallLog.Calls.CONTENT_URI, values);
         */
    }

    private void makeContent() {
        readCallLogs();
    }

    private void readCallLogs() {
        Cursor cursor               = getBaseContext().getContentResolver().query(CallLog.Calls.CONTENT_URI,null, null, null, CallLog.Calls.DATE + " DESC");
        int number                  = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type                    = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date                    = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration                = cursor.getColumnIndex(CallLog.Calls.DURATION);
        while (cursor.moveToNext()) {
            String phNumber     = cursor.getString(number);
            String callType     = cursor.getString(type);
            String callDate     = cursor.getString(date);
            Date callDayTime    = new Date(Long.valueOf(callDate));
            String callDuration = cursor.getString(duration);
            String dir          = null;
            int dircode         = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }

            // Make the Content - START
            String text_to_show = String.format(" Phone Number is %s.\n Call Date is %s.\n Call duration is %s.", phNumber, callDayTime, callDuration);
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

        }
        cursor.close();
    }
}