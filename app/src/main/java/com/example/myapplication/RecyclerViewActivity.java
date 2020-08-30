package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.adapters.MyChosenInterestAdapter;
import com.example.myapplication.adapters.OnItemClickListener;
import com.example.myapplication.objects.Interest;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView my_interest_recylerview;
    public List<Interest> chosen_interest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        my_interest_recylerview = (RecyclerView) findViewById(R.id.my_interest_recylerview);

        // set what the user choose as his / her interest
        //!TODO replace with real data from server
        chosen_interest         = new ArrayList<>();
        chosen_interest.add(new Interest(0, R.drawable.interest_icon_all , "Semua"));
        chosen_interest.add(new Interest(1, R.drawable.interest_icon_percintaan , "Percintaan"));
        chosen_interest.add(new Interest(2, R.drawable.interest_icon_hewan_kesayangan , "Hewan Kesayangan"));
        chosen_interest.add(new Interest(3, R.drawable.interest_icon_film_interest , "Film"));
        chosen_interest.add(new Interest(4, R.drawable.interest_icon_kecantikan , "Kecantikan"));
        chosen_interest.add(new Interest(5, R.drawable.interest_icon_buku , "Buku"));
        chosen_interest.add(new Interest(6, R.drawable.interest_icon_fitness_kesehatan , "Fitness & Kesehatan"));
        chosen_interest.add(new Interest(7, R.drawable.interest_icon_olahraga , "Olahraga"));
        chosen_interest.add(new Interest(8, R.drawable.interest_icon_travel , "Travel"));
        chosen_interest.add(new Interest(9, R.drawable.interest_icon_teknologi , "Teknologi"));
        chosen_interest.add(new Interest(10, R.drawable.interest_icon_pendidikan , "Pendidikan"));
        chosen_interest.add(new Interest(11, R.drawable.interest_icon_fotografi , "Fotografi"));
        chosen_interest.add(new Interest(12, R.drawable.interest_icon_komik , "Komik"));
        chosen_interest.add(new Interest(13, R.drawable.interest_icon_musik , "Musik"));
        chosen_interest.add(new Interest(14, R.drawable.interest_icon_berita , "Berita"));
        chosen_interest.add(new Interest(15, R.drawable.interest_icon_anime , "Anime"));

        // recycler view is fixed size and horizontal
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        my_interest_recylerview.setLayoutManager(layoutManager);
        my_interest_recylerview.setHasFixedSize(true);

        // set the RecyclerView
        MyChosenInterestAdapter the_adapter = new MyChosenInterestAdapter(chosen_interest, new OnItemClickListener() {
            @Override
            public void onItemClick(Interest item) {
                Toast.makeText(getApplicationContext(), "You want topic with tag of: " + item.getName() + " , ID of interest: " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
        my_interest_recylerview.setAdapter(the_adapter);
    }
}

