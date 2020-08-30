package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.objects.Interest;
import com.example.myapplication.objects.InterestAdapter;

import java.util.ArrayList;

public class SwipeRefreshLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);

        final SwipeRefreshLayout swipe_refresh    = (SwipeRefreshLayout) findViewById(R.id.SwipeRefreshLayout);
        ListView list_view                  = (ListView) findViewById(R.id.ListView);

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getApplicationContext(), "refresh", Toast.LENGTH_SHORT).show();
                swipe_refresh.setRefreshing(false);
            }
        });

        Interest interest_1     = new Interest(1, R.drawable.interest_icon_percintaan , "Percintaan");
        Interest interest_2     = new Interest(2, R.drawable.interest_icon_hewan_kesayangan , "Hewan Kesayangan");
        Interest interest_3     = new Interest(3, R.drawable.interest_icon_film_interest , "Film");
        Interest interest_4     = new Interest(4, R.drawable.interest_icon_kecantikan , "Kecantikan");
        Interest interest_5     = new Interest(5, R.drawable.interest_icon_buku , "Buku");
        ArrayList<Interest> interests_data  = new ArrayList<Interest>();
        interests_data.add(interest_1);
        interests_data.add(interest_2);
        interests_data.add(interest_3);
        interests_data.add(interest_4);
        interests_data.add(interest_5);

        InterestAdapter interestAdapter              = new InterestAdapter(getApplicationContext(), interests_data);
        list_view.setAdapter( interestAdapter );

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

    }
}