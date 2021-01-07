package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.adapters.CountryAdapter;
import com.example.myapplication.objects.CountryItem;

import java.util.ArrayList;

public class CustomSpinnerActivity extends AppCompatActivity {

    private ArrayList<CountryItem> mCountryList;
    private CountryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_spinner);

        mCountryList = new ArrayList<>();
        mCountryList.add(new CountryItem("Afghanistan", R.drawable.flag_afghanistan));
        mCountryList.add(new CountryItem("Albania", R.drawable.flag_albania));
        mCountryList.add(new CountryItem("Algeria", R.drawable.flag_algeria));

        Spinner spinnerCountries    = findViewById(R.id.spinner_countries);
        mAdapter                    = new CountryAdapter(this, mCountryList);

        spinnerCountries.setAdapter(mAdapter);
        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryItem clickedItem     = (CountryItem) parent.getItemAtPosition(position);
                String clickedCountryName   = clickedItem.getCountryName();
                Toast.makeText(CustomSpinnerActivity.this, clickedCountryName + " selected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}