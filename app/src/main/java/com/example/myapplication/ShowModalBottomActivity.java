package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.fragments.ItemListDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ShowModalBottomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_modal_bottom);

        Button the_button = (Button) findViewById(R.id.the_button);
        the_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ItemListDialogFragment addPhotoBottomDialogFragment = ItemListDialogFragment.newInstance(500);
                addPhotoBottomDialogFragment.show(getSupportFragmentManager(), "ItemListDialogFragment");

            }
        });

    }
}