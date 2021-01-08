package com.example.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_With_Animation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_With_Animation extends Fragment {

    private static final String TEXT = "text";
    private String mText;
    private OnFragmentInteractionListener mListener;
    private EditText editTextFragment;
    private Button buttonFragment;

    public Fragment_With_Animation() {
        // Required empty public constructor
    }

    public static Fragment_With_Animation newInstance(String text) {
        Fragment_With_Animation fragment  = new Fragment_With_Animation();
        Bundle args             = new Bundle();

        args.putString(TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mText = getArguments().getString(TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view           = inflater.inflate(R.layout.fragment__with__animation, container, false);
        buttonFragment      = view.findViewById(R.id.button_fragment);
        editTextFragment    = view.findViewById(R.id.edittext_fragment);
        editTextFragment.setText(mText);
        editTextFragment.requestFocus();
        buttonFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendBackText = editTextFragment.getText().toString();
                sendBack(sendBackText);
            }
        });
        return view;
    }

    public void sendBack(String sendBackText) {
        if (mListener != null) {
            mListener.onFragmentInteraction(sendBackText);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String sendBackText);
    }

}