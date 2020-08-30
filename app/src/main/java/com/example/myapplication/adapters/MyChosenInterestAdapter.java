package com.example.myapplication.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.objects.Interest;

import java.util.ArrayList;
import java.util.List;

public class MyChosenInterestAdapter extends RecyclerView.Adapter<MyChosenInterestAdapter.ViewHolder> {
    private List<Interest> chosen_interest;
    private final OnItemClickListener listener;

    public MyChosenInterestAdapter(List<Interest> myDataset, OnItemClickListener listener) {
        chosen_interest = new ArrayList<>(myDataset);
        this.listener   = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView interest_image;

        public ViewHolder(View v) {
            super(v);
            interest_image  = (ImageView) v.findViewById(R.id.interest_image);
            mTextView       = (TextView) v.findViewById(R.id.interest_name);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("BILLY", "clicked on " + getLayoutPosition() );
                }
            });
        }

        public void bind(final Interest item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyChosenInterestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_my_chosen_interests, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText( chosen_interest.get(position).getName() );
        holder.interest_image.setImageResource( chosen_interest.get(position).getImageDrawableId() );
        holder.bind(chosen_interest.get(position), listener);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return chosen_interest.size();
    }


}