package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class AnimatorExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_example);

        final Button button4 = (Button) findViewById(R.id.button4);
        final Button button5 = (Button) findViewById(R.id.button5);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(AnimatorExampleActivity.this, R.animator.property_animator);
                set.setTarget(button4);
                set.start();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation hyperspaceJump = AnimationUtils.loadAnimation(AnimatorExampleActivity.this, R.anim.hyperspace_jump);
                button5.startAnimation(hyperspaceJump);
            }
        });


    }
}