package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT;

public class AppBarActivity extends AppCompatActivity {

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                return true;

            case R.id.action_favorite2:
                return true;

            case R.id.action_favorite3:
                return true;

            case R.id.action_favorite4:
                return true;

            case R.id.action_favorite5:
                return true;

            case R.id.action_settings:
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // set theme no toolbar so we can use the toolcar we used on this layout
        // can only work one time, before onCreate, store user preference before set theme maybe if you want
        setTheme(R.style.LightThemeNoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar);

        Button button       = (Button) findViewById(R.id.button);
        Toolbar myToolbar   = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Button the_button = (Button) findViewById(R.id.button);

        the_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // doesn't work, read
                // can only work one time, before onCreate, store user preference before set theme maybe if you want
                setTheme(R.style.AppTheme);
            }
        });
    }
}