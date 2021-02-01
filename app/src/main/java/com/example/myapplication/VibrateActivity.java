package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VibrateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vibrate);

        Button button       = (Button) findViewById(R.id.the_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(1000);
                }

                Toast.makeText(view.getContext(), "Button", Toast.LENGTH_SHORT).show();
            }
        });

        int b = 0;
    }
}