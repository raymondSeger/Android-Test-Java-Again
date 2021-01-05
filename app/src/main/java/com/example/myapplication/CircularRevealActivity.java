package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;

public class CircularRevealActivity extends AppCompatActivity {

    private Button buttonReveal;
    private Button buttonHide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);

        buttonReveal    = findViewById(R.id.button_reveal);
        buttonHide      = findViewById(R.id.button_hide);

        buttonReveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealFAB();
            }
        });

        buttonHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFAB();
            }
        });

    }

    private void revealFAB() {
        View view           = findViewById(R.id.fab);
        int cx              = view.getWidth() / 2;
        int cy              = view.getHeight() / 2;
        float finalRadius   = (float) Math.hypot(cx, cy);
        Animator anim       = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        view.setVisibility(View.VISIBLE);
        anim.start();
    }

    private void hideFAB() {
        final View view     = findViewById(R.id.fab);
        int cx              = view.getWidth() / 2;
        int cy              = view.getHeight() / 2;
        float initialRadius = (float) Math.hypot(cx, cy);
        Animator anim       = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }

}