package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.fragments.BlankFragment;
import com.example.myapplication.fragments.BlankFragment2;
import com.example.myapplication.fragments.BlankFragment3;

public class FragmentsActivity extends AppCompatActivity {

    public void can_be_used_by_fragment(String the_text) {
        Toast.makeText(getBaseContext(), the_text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        BlankFragment newFragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString("param1", "raymond");
        args.putString("param2", "seger");
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, newFragment, "tag1");
        transaction.commit();
        transaction.addToBackStack(null);

        BlankFragment3 newFragment2 = new BlankFragment3();
        Bundle args2 = new Bundle();
        args2.putString("param1", "raymond");
        args2.putString("param2", "seger");
        newFragment2.setArguments(args2);

        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        transaction2.replace(R.id.fragment2, newFragment2, "tag2");
        transaction2.commit();
        transaction2.addToBackStack(null);


        Button change_fragment = (Button) findViewById(R.id.change_fragment);
        change_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BlankFragment2 newFragment = new BlankFragment2();
                Bundle args = new Bundle();
                args.putString("param1", "Billy");
                args.putString("param2", "Seger");
                newFragment.setArguments(args);

                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                transaction3.replace(R.id.fragment, newFragment);
                transaction3.commit();
                transaction3.addToBackStack(null);
            }
        });

    }
}