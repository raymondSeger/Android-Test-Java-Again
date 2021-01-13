package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.adapters.ExampleAdapter3;
import com.example.myapplication.objects.ExampleItem3;
import com.example.myapplication.objects.ExampleItemAdapter3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerViewWithPicassoActivity extends AppCompatActivity implements ExampleItemAdapter3.OnItemClickListener {

    public static final String EXTRA_URL        = "imageUrl";
    public static final String EXTRA_CREATOR    = "creatorName";
    public static final String EXTRA_LIKES      = "likeCount";

    private RecyclerView mRecyclerView;
    private ExampleItemAdapter3 mExampleAdapter;
    private ArrayList<ExampleItem3> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_with_picasso);

        mRecyclerView   = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mExampleList    = new ArrayList<>();
        mRequestQueue   = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit      = jsonArray.getJSONObject(i);
                                String creatorName  = hit.getString("user");
                                String imageUrl     = hit.getString("webformatURL");
                                int likeCount       = hit.getInt("likes");
                                mExampleList.add(new ExampleItem3(imageUrl, creatorName, likeCount));
                            }
                            mExampleAdapter = new ExampleItemAdapter3(RecyclerViewWithPicassoActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListener(RecyclerViewWithPicassoActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailRecyclerViewWithPicassoActivity.class);
        ExampleItem3 clickedItem = mExampleList.get(position);
        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR, clickedItem.getCreator());
        detailIntent.putExtra(EXTRA_LIKES, clickedItem.getLikeCount());
        startActivity(detailIntent);
    }

}