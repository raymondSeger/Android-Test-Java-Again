package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

public class BluetoothOnOrOffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_on_or_off);

        Button btntOn                   = (Button) findViewById(R.id.btnOn);
        Button btntOff                  = (Button) findViewById(R.id.btnOFF);
        Button findDevice               = (Button) findViewById(R.id.findDevice);
        final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();

        btntOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bAdapter == null) {
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                } else {
                    if( !bAdapter.isEnabled() ) {
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btntOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bAdapter.disable();
                Toast.makeText(getApplicationContext(),"Bluetooth Turned OFF", Toast.LENGTH_SHORT).show();
            }
        });

        findDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( bAdapter.isEnabled() ) {

                    Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
                    if (pairedDevices.size() > 0) {
                        // There are paired devices. Get the name and address of each paired device.
                        for (BluetoothDevice device : pairedDevices) {
                            String deviceName               = device.getName();
                            String deviceHardwareAddress    = device.getAddress(); // MAC address

                            // debug here
                            int a = 0;
                        }
                    }

                }

            }
        });

    }

}