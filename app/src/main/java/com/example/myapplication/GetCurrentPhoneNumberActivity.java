package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class GetCurrentPhoneNumberActivity extends AppCompatActivity {

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getPhoneNumber();
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
        setContentView(R.layout.activity_get_current_phone_number);

        getPhoneNumber();
    }

    private void getPhoneNumber() {

        if (ActivityCompat.checkSelfPermission(this, READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(GetCurrentPhoneNumberActivity.this, new String[] { READ_PHONE_NUMBERS }, 1);
            return;
        }

        TelephonyManager tMgr   = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        String mPhoneNumber     = tMgr.getLine1Number(); // usually empty, this TelephonyManager is closed system now. Can't get IMEI too
        Toast.makeText(this, mPhoneNumber, Toast.LENGTH_SHORT).show();

    }
}