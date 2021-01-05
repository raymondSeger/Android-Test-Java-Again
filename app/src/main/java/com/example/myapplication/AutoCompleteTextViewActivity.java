package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteTextViewActivity extends AppCompatActivity {

    private static final String[] COUNTRIES = new String[]{
        "Afghanistan", "Albania", "Algeria", "Andorra", "Angola"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);

        String[] countries = new String[]{
                "Afghanistan", "Albania", "Algeria", "Andorra", "Angola"
        };
        AutoCompleteTextView editText = findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.autocomplete_list_item, R.id.text_view_list_item, countries);
        editText.setAdapter(adapter);
        editText.setThreshold(2);
        //get the input like for a normal EditText
        //String input = editText.getText().toString();

    }
}