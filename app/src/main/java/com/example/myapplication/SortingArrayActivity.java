package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.adapters.ExampleAdapter3;
import com.example.myapplication.objects.ExampleItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SortingArrayActivity extends AppCompatActivity {

    private ArrayList<ExampleItem> mExampleList;
    private ArrayAdapter<String> mListViewAdapter;
    private ExampleAdapter3 mRecyclerViewAdapter;
    private String[] mArrayNames = new String[]{"Daryl", "Rick", "Abraham", "Eugene"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting_array);

        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.flag_albania, "Baba", "Baba"));
        mExampleList.add(new ExampleItem(R.drawable.flag_algeria, "Dada", "Dada"));
        mExampleList.add(new ExampleItem(R.drawable.flag_afghanistan, "Cada", "Cada"));
        mExampleList.add(new ExampleItem(R.drawable.flag_algeria, "Ada", "Ada"));

        // recycler view
        RecyclerView recyclerView                   = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager    = new LinearLayoutManager(this);
        mRecyclerViewAdapter                        = new ExampleAdapter3(mExampleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mRecyclerViewAdapter);

        // list view
        ListView listView   = findViewById(R.id.listView);
        mListViewAdapter    = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mArrayNames);
        listView.setAdapter(mListViewAdapter);

        Button buttonSort = findViewById(R.id.button_sort);
        buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortArray();
                sortArrayList();
            }
        });

    }

    private void sortArray() {
        Arrays.sort(mArrayNames);
        mListViewAdapter.notifyDataSetChanged();
    }

    private void sortArrayList() {
        Collections.sort(mExampleList, new Comparator<ExampleItem>() {
            @Override
            public int compare(ExampleItem o1, ExampleItem o2) {
                return o1.getText2().compareTo(o2.getText2());
            }
        });
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

}