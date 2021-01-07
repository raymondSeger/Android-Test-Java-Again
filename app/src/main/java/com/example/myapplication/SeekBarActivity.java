package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;
    private ProgressBar progressBar;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        textView    = (TextView) findViewById(R.id.textView);
        textView2   = (TextView) findViewById(R.id.textView2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        seekBar     = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress);
                textView.setText("" + progress + "%");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView2.setText("start tracking touch ");
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView2.setText("stop tracking touch ");
            }
        });

    }
}