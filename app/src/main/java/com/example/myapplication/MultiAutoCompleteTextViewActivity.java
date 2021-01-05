package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

public class MultiAutoCompleteTextViewActivity extends AppCompatActivity {

    private static final String[] COUNTRIES = new String[]{
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola"
    };
    private MultiAutoCompleteTextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_auto_complete_text_view);

        editText                        = findViewById(R.id.mactv);
        ArrayAdapter<String> adapter    = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, COUNTRIES);
        editText.setAdapter(adapter);
        editText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    public void showInput(View v) {
        String input            = editText.getText().toString().trim();
        String[] singleInputs   = input.split("\\s*,\\s*");
        String toastText        = "";
        for (int i = 0; i < singleInputs.length; i++) {
            toastText += "Item " + i + ": " + singleInputs[i] + "\n";
        }
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }
}