package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.myapplication.adapters.ExampleAdapter3;
import com.example.myapplication.objects.ExampleItem;

import java.util.ArrayList;

public class RecyclerViewWithEditTextSearchActivity extends AppCompatActivity {

    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter3 mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_with_edit_text_search);

        createExampleList();
        buildRecyclerView();

        EditText editText = findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<ExampleItem> filteredList = new ArrayList<>();
        for (ExampleItem item : mExampleList) {
            if (item.getText1().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        mAdapter.filterList(filteredList);
    }

    private void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.flag_afghanistan, "One", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.flag_algeria, "Two", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.flag_albania, "Three", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.flag_afghanistan, "Four", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.flag_algeria, "Five", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.flag_albania, "Six", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.flag_afghanistan, "Seven", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.flag_algeria, "Eight", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.flag_albania, "Nine", "Line 2"));
    }

    private void buildRecyclerView() {
        mRecyclerView   = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager  = new LinearLayoutManager(this);
        mAdapter        = new ExampleAdapter3(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}