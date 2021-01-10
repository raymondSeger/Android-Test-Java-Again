package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class CalendarAddActivity extends AppCompatActivity {
    private TextView textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_add);

        textViewDate = findViewById(R.id.text_view_date);
    }

    public void getDate(View v) {
        Calendar c = Calendar.getInstance();
        Date today = c.getTime();
        c.add(Calendar.DATE, 3);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.YEAR, 1);
        Date future = c.getTime();
        textViewDate.setText("Today: " + today + "\n" + "Future: " + future);
    }

}