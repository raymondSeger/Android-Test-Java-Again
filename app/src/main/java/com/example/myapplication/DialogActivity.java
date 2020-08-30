package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.fragments.CustomLayoutDialogFragment;
import com.example.myapplication.fragments.FireMissilesDialogFragment;
import com.example.myapplication.fragments.MultiChoiceDialogFragment;
import com.example.myapplication.fragments.SingleChoiceDialogFragment;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button the_button   = (Button) findViewById(R.id.the_button);
        Button the_button_2 = (Button) findViewById(R.id.the_button_2);
        Button the_button_3 = (Button) findViewById(R.id.the_button_3);
        Button the_button_4 = (Button) findViewById(R.id.the_button_4);

        the_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FireMissilesDialogFragment fragmentDialog = new FireMissilesDialogFragment();
                fragmentDialog.show(getSupportFragmentManager(), "fire missile");
            }
        });

        the_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleChoiceDialogFragment fragmentDialog = new SingleChoiceDialogFragment();
                fragmentDialog.show(getSupportFragmentManager(), "single_choice");
            }
        });

        the_button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiChoiceDialogFragment fragmentDialog = new MultiChoiceDialogFragment();
                fragmentDialog.show(getSupportFragmentManager(), "multi_choice");
            }
        });

        the_button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomLayoutDialogFragment fragmentDialog = new CustomLayoutDialogFragment();
                fragmentDialog.show(getSupportFragmentManager(), "custom_layout");
            }
        });

    }
}