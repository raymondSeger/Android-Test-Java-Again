package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.fragments.BlankFragment;
import com.example.myapplication.fragments.SettingsFragment;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings_activity);

        SettingsFragment newFragment = new SettingsFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentBox, newFragment);
        transaction.commit();
        // make sure unable to press back to make fragment into empty
        // transaction.addToBackStack(null);

        Button the_button = (Button) findViewById(R.id.the_button);
        the_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                Boolean SwitchPreferenceCompat      = sharedPreferences.getBoolean("SwitchPreferenceCompat", false);
                Boolean CheckBoxPreference          = sharedPreferences.getBoolean("CheckBoxPreference", false);
                Integer SeekBarPreference           = sharedPreferences.getInt("SeekBarPreference", 0);
                String EditTextPreference           = sharedPreferences.getString("EditTextPreference", "");
                String ListPreference               = sharedPreferences.getString("ListPreference", "");
                Boolean SwitchPreferenceCompat2     = sharedPreferences.getBoolean("SwitchPreferenceCompat2", false);
                Boolean SwitchPreferenceCompat3     = sharedPreferences.getBoolean("SwitchPreferenceCompat3", false);
                int a = 0;
            }
        });

    }
}