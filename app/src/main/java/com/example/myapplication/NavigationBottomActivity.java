package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.fragments.BlankFragment3;
import com.example.myapplication.fragments.DashboardFragment;
import com.example.myapplication.fragments.HomeFragment;
import com.example.myapplication.fragments.NotificationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationBottomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bottom);

        // with navigation graph
        //setContentView(R.layout.activity_navigation_bottom_with_graph);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        HomeFragment newFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("param1", "Home");
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, newFragment, "the_tag");
        transaction.commit();
        transaction.addToBackStack(null);

        // use fragment change - old way, comment to use the new way
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String menu_title               = (String) item.getTitle();
                String[] menu_id_and_app_name   = getResources().getResourceName(item.getItemId()).split("\\/");
                String menu_id                  = menu_id_and_app_name[1];

                switch (menu_id) {
                    case "navigation_home": {
                        HomeFragment newFragment = new HomeFragment();
                        Bundle args = new Bundle();
                        args.putString("param1", "Home");
                        newFragment.setArguments(args);
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, newFragment, "the_tag");
                        transaction.commit();
                        transaction.addToBackStack(null);
                        break;
                    }
                    case "navigation_dashboard": {
                        DashboardFragment newFragment = new DashboardFragment();
                        Bundle args = new Bundle();
                        args.putString("param1", "Dashboard");
                        newFragment.setArguments(args);
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, newFragment, "the_tag");
                        transaction.commit();
                        transaction.addToBackStack(null);
                        break;
                    }
                    case "navigation_notifications": {
                        NotificationFragment newFragment = new NotificationFragment();
                        Bundle args = new Bundle();
                        args.putString("param1", "Notification");
                        newFragment.setArguments(args);
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, newFragment, "the_tag");
                        transaction.commit();
                        transaction.addToBackStack(null);
                        break;
                    }
                }

                return true;
            }
        });


        // Use Navigation Graph - new way, uncomment to use it
        /*
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        */
    }
}