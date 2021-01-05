package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SharedElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element);

        setTitle("Activity 1");

        Fade fade   = new Fade();
        View decor  = getWindow().getDecorView();

        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        final ImageView imageView   = findViewById(R.id.image_activity_1);
        Button button               = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent                   = new Intent(SharedElementActivity.this, SharedElementActivity2.class);
                ActivityOptionsCompat options   = ActivityOptionsCompat.makeSceneTransitionAnimation( SharedElementActivity.this, imageView, ViewCompat.getTransitionName(imageView) );
                startActivity(intent, options.toBundle());
            }
        });

    }
}