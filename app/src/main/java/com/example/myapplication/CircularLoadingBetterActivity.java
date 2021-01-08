package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CircularLoadingBetterActivity extends AppCompatActivity {

    private int progress = 0;
    private ProgressBar progress_bar;
    private TextView text_view_progress;
    private Button button_decr;
    private Button button_incr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_loading_better);

        progress_bar            = (ProgressBar) findViewById(R.id.progress_bar);
        text_view_progress      = (TextView) findViewById(R.id.text_view_progress);
        button_decr             = (Button) findViewById(R.id.button_decr);
        button_incr             = (Button) findViewById(R.id.button_incr);

        button_decr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress -= 10;
                updateProgressBar();
            }
        });

        button_incr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress += 10;
                updateProgressBar();
            }
        });

    }

    private void updateProgressBar() {
        progress_bar.setProgress(progress);
        text_view_progress.setText( String.valueOf(progress) );
    }
}