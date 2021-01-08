package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.fragments.FragmentA;
import com.example.myapplication.fragments.FragmentB;

public class SendDataBetweenFragmentsActivity extends AppCompatActivity implements FragmentA.FragmentAListener, FragmentB.FragmentBListener {

    private FragmentA fragmentA;
    private FragmentB fragmentB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_between_fragments);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_a, fragmentA)
                .replace(R.id.container_b, fragmentB)
                .commit();
    }

    @Override
    public void onInputASent(CharSequence input) {
        fragmentB.updateEditText(input);
    }
    @Override
    public void onInputBSent(CharSequence input) {
        fragmentA.updateEditText(input);
    }

}