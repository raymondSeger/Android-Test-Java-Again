package com.example.myapplication.objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import com.example.myapplication.R;

public class InterestAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Interest> interests;

    public InterestAdapter(Context context, ArrayList<Interest> bookings) {
        this.context    = context;
        this.interests   = bookings;
    }

    @Override
    public int getCount() {
        return interests.size();
    }

    @Override
    public Object getItem(int position) {
        return interests.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView             = (View) inflater.inflate( R.layout.layout_my_chosen_interests, parent, false);

            ImageView interest_image  = (ImageView) convertView.findViewById(R.id.interest_image);
            TextView mTextView       = (TextView) convertView.findViewById(R.id.interest_name);
        }

        return convertView;

    }
}