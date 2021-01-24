package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.myapplication.RecyclerViewWithPicassoActivity.EXTRA_CREATOR;
import static com.example.myapplication.RecyclerViewWithPicassoActivity.EXTRA_URL;
import static com.example.myapplication.RecyclerViewWithPicassoActivity.EXTRA_LIKES;

public class DetailRecyclerViewWithPicassoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defail_recycler_view_with_picasso);

        Intent intent               = getIntent();
        String imageUrl             = intent.getStringExtra(EXTRA_URL);
        String creatorName          = intent.getStringExtra(EXTRA_CREATOR);
        int likeCount               = intent.getIntExtra(EXTRA_LIKES, 0);
        ImageView imageView         = findViewById(R.id.image_view_detail);
        TextView textViewCreator    = findViewById(R.id.text_view_creator_detail);
        TextView textViewLikes      = findViewById(R.id.text_view_like_detail);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);

        textViewCreator.setText(creatorName);
        textViewLikes.setText("Likes: " + likeCount);
    }
}