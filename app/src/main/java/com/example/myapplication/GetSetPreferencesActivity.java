package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GetSetPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_set_preferences);

        Button set_button       = (Button) findViewById(R.id.setbutton);
        Button get_button       = (Button) findViewById(R.id.getbutton);
        Button remove_button    = (Button) findViewById(R.id.removebutton);

        final SharedPreferences settings = getSharedPreferences("ojekTaxiPreferences", 0);

        settings.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                int c = 0;
            }
        });

        set_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor shared_preference_editor   = settings.edit();
                shared_preference_editor.putString("key1", "value1");
                shared_preference_editor.apply();
                Toast.makeText(view.getContext(), "Get Done", Toast.LENGTH_SHORT).show();
            }
        });

        get_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key1                 = settings.getString("key1", "0");
                Toast.makeText(view.getContext(), key1, Toast.LENGTH_SHORT).show();
            }
        });

        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor shared_preference_editor   = settings.edit();
                shared_preference_editor.remove("key1");
                shared_preference_editor.apply();
                Toast.makeText(view.getContext(), "removed the key", Toast.LENGTH_SHORT).show();
            }
        });

    }
}