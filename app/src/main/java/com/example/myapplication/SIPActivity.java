package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.sip.SipAudioCall;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.USE_SIP;

public class SIPActivity extends AppCompatActivity {
    public SipManager sipManager = null;
    public SipProfile sipProfile = null;
    public SipAudioCall call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sip);


        if (ActivityCompat.checkSelfPermission(this, USE_SIP) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(SIPActivity.this, new String[] { USE_SIP, USE_SIP}, 1);
            return;
        }

        // init
        if (sipManager == null) {
            sipManager = SipManager.newInstance(this);
        }

        // init profile
        SipProfile.Builder builder = null;
        try {
            builder = new SipProfile.Builder("raymo", "sip2sip.info");
            builder.setPassword("123123billy");
            sipProfile = builder.build();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent();
        intent.setAction("android.SipDemo.INCOMING_CALL");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, Intent.FILL_IN_DATA);
        try {
            sipManager.open(sipProfile, pendingIntent, new SipRegistrationListener() {
                @Override
                public void onRegistering(String localProfileUri) {
                    Toast.makeText(SIPActivity.this, "Registering with SIP Server...", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRegistrationDone(String localProfileUri, long expiryTime) {
                    Toast.makeText(SIPActivity.this, "Ready", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRegistrationFailed(String localProfileUri, int errorCode, String errorMessage) {
                    Toast.makeText(SIPActivity.this, "Registration failed.  Please check settings.", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (SipException e) {
            e.printStackTrace();
        }

        SipAudioCall.Listener listener = new SipAudioCall.Listener() {

            @Override
            public void onCallEstablished(SipAudioCall call) {
                call.startAudio();
                call.setSpeakerMode(true);
                call.toggleMute();
            }

            @Override

            public void onCallEnded(SipAudioCall call) {
                Toast.makeText(SIPActivity.this, "Call ended", Toast.LENGTH_SHORT).show();
                // Do something.
            }
        };

        try {
            call = sipManager.makeAudioCall(sipProfile.getUriString(), "sip:raymo@192.168.0.19:5060", listener, 30);
        } catch (SipException e) {
            e.printStackTrace();
        }

    }

    public void closeLocalProfile() {
        if (sipManager == null) {
            return;
        }
        try {
            if (sipProfile != null) {
                sipManager.close(sipProfile.getUriString());
            }
        } catch (Exception ee) {
            Log.d("j", "Failed to close local profile.", ee);
        }
    }

}