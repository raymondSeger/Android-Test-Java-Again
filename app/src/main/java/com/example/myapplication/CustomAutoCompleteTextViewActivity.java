package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import com.example.myapplication.adapters.AutoCompleteCountryAdapter;
import com.example.myapplication.objects.CountryItem;

import java.util.ArrayList;
import java.util.List;

public class CustomAutoCompleteTextViewActivity extends AppCompatActivity {

    private List<CountryItem> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_auto_complete_text_view);

        countryList = new ArrayList<>();
        countryList.add(new CountryItem("Afghanistan", R.drawable.flag_afghanistan));
        countryList.add(new CountryItem("Albania", R.drawable.flag_albania));
        countryList.add(new CountryItem("Algeria", R.drawable.flag_algeria));

        AutoCompleteTextView editText = findViewById(R.id.actv);
        AutoCompleteCountryAdapter adapter = new AutoCompleteCountryAdapter(this, countryList);
        editText.setAdapter(adapter);

    }
}