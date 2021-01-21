package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.myapplication.fragments.BlankFragment;
import com.example.myapplication.fragments.BlankFragment3;

public class TestBackOnFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_back_on_fragment);

        BlankFragment newFragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString("param1", "raymond");
        args.putString("param2", "seger");
        newFragment.setArguments(args);

        BlankFragment3 newFragment2 = new BlankFragment3();
        Bundle args2 = new Bundle();
        args2.putString("param1", "raymond");
        args2.putString("param2", "seger");
        newFragment2.setArguments(args2);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, newFragment, "tag1");
        transaction.commit();
        transaction.addToBackStack(null);
        
        FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
        transaction3.replace(R.id.fragment, newFragment2, "tag2");
        transaction3.commit();
        transaction3.addToBackStack(null);

    }
}