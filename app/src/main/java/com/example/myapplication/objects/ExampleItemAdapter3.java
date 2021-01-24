package com.example.myapplication.objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

public class ExampleItemAdapter3 extends RecyclerView.Adapter<ExampleItemAdapter3.ExampleViewHolder3> {
    private Context mContext;
    private ArrayList<ExampleItem3> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ExampleItemAdapter3(Context context, ArrayList<ExampleItem3> exampleList) {
        mContext        = context;
        mExampleList    = exampleList;
    }

    @Override
    public ExampleViewHolder3 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item_3, parent, false);
        return new ExampleViewHolder3(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder3 holder, int position) {
        ExampleItem3 currentItem    = mExampleList.get(position);
        String imageUrl             = currentItem.getImageUrl();
        String creatorName          = currentItem.getCreator();
        int likeCount               = currentItem.getLikeCount();

        holder.mTextViewCreator.setText(creatorName);
        holder.mTextViewLikes.setText("Likes: " + likeCount);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder3 extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;

        public ExampleViewHolder3(View itemView) {
            super(itemView);
            mImageView          = itemView.findViewById(R.id.image_view);
            mTextViewCreator    = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes      = itemView.findViewById(R.id.text_view_likes);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}