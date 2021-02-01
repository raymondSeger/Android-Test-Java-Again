package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GetStringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_string);

        Button button       = (Button) findViewById(R.id.the_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String the_string = getString(R.string.homepage_text_1);
                String[] languages = getResources().getStringArray(R.array.languages_array);

                for (String language : languages) {
                    Toast.makeText(view.getContext(), language, Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(view.getContext(), the_string, Toast.LENGTH_SHORT).show();
            }
        });

        int b = 0;
    }
}