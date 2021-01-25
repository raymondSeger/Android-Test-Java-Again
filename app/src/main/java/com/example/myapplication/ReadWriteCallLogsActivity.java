package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.Toast;

import java.util.Date;

import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.WRITE_CALL_LOG;

public class ReadWriteCallLogsActivity extends AppCompatActivity {

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readCallLogs();
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

        if (ActivityCompat.checkSelfPermission(ReadWriteCallLogsActivity.this, READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ReadWriteCallLogsActivity.this, WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(ReadWriteCallLogsActivity.this, new String[] { READ_CALL_LOG, WRITE_CALL_LOG }, 1);
            return;
        }

        String all_call_logs = readCallLogs();

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

    private String readCallLogs() {
        StringBuffer stringBuffer   = new StringBuffer();
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
            stringBuffer.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- "
                    + dir + " \nCall Date:--- " + callDayTime
                    + " \nCall duration in sec :--- " + callDuration);
            stringBuffer.append("\n----------------------------------");
        }
        cursor.close();
        return stringBuffer.toString();
    }
}