package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        final TextView textView     = (TextView) findViewById(R.id.textView);
        final TextView textView2    = (TextView) findViewById(R.id.textView2);
        final TextView textView3    = (TextView) findViewById(R.id.textView3);
        final TextView textView4    = (TextView) findViewById(R.id.textView4);
        final TextView textView5    = (TextView) findViewById(R.id.textView5);
        final Button button1        = (Button) findViewById(R.id.button1);
        final Button button2        = (Button) findViewById(R.id.button2);
        final Button button3        = (Button) findViewById(R.id.button3);
        final Button button4        = (Button) findViewById(R.id.button4);
        final Button button5        = (Button) findViewById(R.id.button5);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValueAnimator va = ValueAnimator.ofInt(1, 100);
                va.setDuration(3000);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        textView.setText( String.valueOf(animation.getAnimatedValue()) );
                    }
                });
                va.setRepeatCount(5);
                va.start();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator animation = ObjectAnimator.ofFloat(textView2, "translationX", 100f);
                animation.setDuration(1000);
                animation.start();

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValueAnimator fadeAnim = ObjectAnimator.ofFloat(textView3, "alpha", 1f, 0f);
                fadeAnim.setDuration(250);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(fadeAnim);
                animatorSet.start();

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator animX    = ObjectAnimator.ofFloat(textView4, "x", 50f);
                ObjectAnimator animY    = ObjectAnimator.ofFloat(textView4, "y", 100f);
                AnimatorSet animSetXY   = new AnimatorSet();
                animSetXY.playTogether(animX, animY);
                animSetXY.start();

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(AnimatorActivity.this, R.animator.property_animator);
                set.setTarget(textView5);
                set.start();

            }
        });

    }
}