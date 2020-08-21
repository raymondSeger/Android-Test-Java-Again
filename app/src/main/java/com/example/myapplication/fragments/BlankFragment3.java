package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.myapplication.FragmentsActivity;
import com.example.myapplication.R;

public class BlankFragment3 extends Fragment {

    private String mParam1;
    private String mParam2;

    public BlankFragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("param1");
            mParam2 = getArguments().getString("param2");
        }

        getParentFragmentManager().setFragmentResultListener("data_from_blank_fragment", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                String result = bundle.getString("key1");
                int a = 0;
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_blank3, container, false);

        TextView textview1 = fragmentView.findViewById(R.id.textview1);
        TextView textview2 = fragmentView.findViewById(R.id.textview2);

        textview1.setText(mParam1);
        textview2.setText(mParam2);

        Button button_in_fragment   = (Button) fragmentView.findViewById(R.id.button_in_fragment);
        Button call_parent_activity = (Button) fragmentView.findViewById(R.id.call_parent_activity);

        button_in_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Hello from Fragment 3", Toast.LENGTH_LONG).show();
            }
        });

        call_parent_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentsActivity)getActivity()).can_be_used_by_fragment("Text from Fragment");
            }
        });

        return fragmentView;
    }
}