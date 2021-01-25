package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.myapplication.fragments.BlankFragment;
import com.example.myapplication.fragments.DashboardFragment;
import com.example.myapplication.fragments.FragmentA;
import com.example.myapplication.fragments.HomeFragment;

public class ChangeFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_fragment);

        FrameLayout frame_layout        = (FrameLayout) findViewById(R.id.frame_layout);
        Button change_fragment_btn_1    = (Button) findViewById(R.id.change_fragment_btn_1);
        Button change_fragment_btn_2    = (Button) findViewById(R.id.change_fragment_btn_2);
        Button change_fragment_btn_3    = (Button) findViewById(R.id.change_fragment_btn_3);

        change_fragment_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeFragment newFragment = new HomeFragment();
                Bundle args = new Bundle();
                args.putString("param1", "raymond");
                args.putString("param2", "seger");
                newFragment.setArguments(args);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, newFragment, "tag1");
                transaction.commit();
                transaction.addToBackStack(null);
            }
        });

        change_fragment_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DashboardFragment newFragment = new DashboardFragment();
                Bundle args = new Bundle();
                args.putString("param1", "jett");
                args.putString("param2", "seger");
                newFragment.setArguments(args);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, newFragment, "tag1");
                transaction.commit();
                transaction.addToBackStack(null);

            }
        });

    }
}