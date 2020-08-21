package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class firstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textviewid = (TextView) findViewById(R.id.textviewid);
        Button button       = (Button) findViewById(R.id.get);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "The Image View", Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Button", Toast.LENGTH_SHORT).show();
            }
        });

        textviewid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.println(Log.DEBUG, "billy", "the message");

                ConstraintLayout mRootView = (ConstraintLayout) findViewById(R.id.activity_main);

                TextView a = (TextView) view;
                a.setVisibility(View.GONE);

                Toast.makeText(mRootView.getContext(), "character", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }
}