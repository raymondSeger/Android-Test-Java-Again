package com.example.myapplication.ui.TabLayoutActivityData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    public String data_for_fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data_for_fragment = getArguments().getString("data_for_fragment", "");
        }
    }

    @Override
    public View onCreateView(  @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root           = inflater.inflate(R.layout.fragment_tabbed, container, false);
        TextView textView   = root.findViewById(R.id.section_label);
        textView.setText( data_for_fragment );
        return root;
    }
}