package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.example.myapplication.adapters.ExampleAdapter2;
import com.example.myapplication.objects.ExampleItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewWithSearchViewActivity extends AppCompatActivity {
    private ExampleAdapter2 adapter;
    private List<ExampleItem> exampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_with_search_view);

        fillExampleList();
        setUpRecyclerView();
    }

    private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.flag_afghanistan, "One", "Ten"));
        exampleList.add(new ExampleItem(R.drawable.flag_albania, "Two", "Eleven"));
        exampleList.add(new ExampleItem(R.drawable.flag_algeria, "Three", "Twelve"));
        exampleList.add(new ExampleItem(R.drawable.flag_afghanistan, "Four", "Thirteen"));
        exampleList.add(new ExampleItem(R.drawable.flag_albania, "Five", "Fourteen"));
        exampleList.add(new ExampleItem(R.drawable.flag_algeria, "Six", "Fifteen"));
        exampleList.add(new ExampleItem(R.drawable.flag_afghanistan, "Seven", "Sixteen"));
        exampleList.add(new ExampleItem(R.drawable.flag_albania, "Eight", "Seventeen"));
        exampleList.add(new ExampleItem(R.drawable.flag_algeria, "Nine", "Eighteen"));
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView                   = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager    = new LinearLayoutManager(this);
        adapter                                     = new ExampleAdapter2(exampleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater   = getMenuInflater();
        inflater.inflate(R.menu.example_menu_search, menu);
        MenuItem searchItem     = menu.findItem(R.id.action_search);
        SearchView searchView   = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }

        });

        return true;
    }

}