package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GridLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);

        GridView gv = (GridView) findViewById(R.id.gridview);
        gv.setAdapter(new ImageAdapter(this));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(GridLayoutActivity.this, "Image Position: " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        public ImageAdapter(Context c) {
            mContext = c;
        }
        public int getCount() {
            return thumbImages.length;
        }
        public Object getItem(int position) {
            return null;
        }
        public long getItemId(int position) {
            return 0;
        }
        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setImageResource(thumbImages[position]);
            return imageView;
        }
        // Add all our images to arraylist
        public Integer[] thumbImages = {
                R.drawable.flag_indonesia, R.drawable.flag_algeria,
                R.drawable.flag_albania, R.drawable.flag_afghanistan,
                R.drawable.flag_indonesia, R.drawable.flag_algeria,
                R.drawable.flag_albania, R.drawable.flag_afghanistan,
                R.drawable.flag_indonesia, R.drawable.flag_algeria,
                R.drawable.flag_albania, R.drawable.flag_afghanistan,
                R.drawable.flag_indonesia, R.drawable.flag_algeria,
                R.drawable.flag_albania, R.drawable.flag_afghanistan,
                R.drawable.flag_indonesia, R.drawable.flag_algeria,
                R.drawable.flag_albania, R.drawable.flag_afghanistan,
                R.drawable.flag_indonesia, R.drawable.flag_algeria,
                R.drawable.flag_albania, R.drawable.flag_afghanistan,
                R.drawable.flag_indonesia, R.drawable.flag_algeria,
                R.drawable.flag_albania, R.drawable.flag_afghanistan,
                R.drawable.flag_indonesia, R.drawable.flag_algeria,
                R.drawable.flag_albania, R.drawable.flag_afghanistan,
                R.drawable.flag_indonesia, R.drawable.flag_algeria,
                R.drawable.flag_albania, R.drawable.flag_afghanistan,
        };
    }

}