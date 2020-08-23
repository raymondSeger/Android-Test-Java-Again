package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuContextActivity extends AppCompatActivity {

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // with menu
        // getMenuInflater().inflate(R.menu.example_menu, menu);

        // dynamic
        menu.add(Menu.NONE, 1, 1, "Share");
        menu.add(Menu.NONE, 2, 2, "Comment");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(getApplicationContext(), "You have chosen Share", Toast.LENGTH_SHORT).show();
                return true;
            case 2:
                Toast.makeText(getApplicationContext(), "You have chosen Comment", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_context);

        Button popup_menu_button            = (Button) findViewById(R.id.popup_menu_button);
        final Button context_menu_button    = (Button) findViewById(R.id.context_menu_button);
        registerForContextMenu(context_menu_button); // won't work without android:focusable="false"


        popup_menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(MenuContextActivity.this, view);
                menu.getMenu().add(Menu.NONE, 1, 1, "Share");
                menu.getMenu().add(Menu.NONE, 2, 2, "Comment");
                menu.show();

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case 1:
                                Toast.makeText(getApplicationContext(), "You have chosen Share", Toast.LENGTH_SHORT).show();
                                return true;
                            case 2:
                                Toast.makeText(getApplicationContext(), "You have chosen Comment", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return true;
                        }
                    }

                });

            }
        });

    }
}