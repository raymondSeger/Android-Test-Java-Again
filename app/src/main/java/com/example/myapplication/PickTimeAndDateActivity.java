package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myapplication.fragments.DatePickerFragment;
import com.example.myapplication.fragments.TimePickerFragment;

import java.util.Calendar;

public class PickTimeAndDateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        int a = 0;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        int a = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_time_and_date);

        Button the_button   = (Button) findViewById(R.id.the_button);
        Button the_button_2 = (Button) findViewById(R.id.the_button_2);

        the_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        the_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

    }
}