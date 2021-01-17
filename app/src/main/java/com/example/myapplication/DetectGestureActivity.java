package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class DetectGestureActivity extends AppCompatActivity {

    String DEBUG_TAG = "DetectGestureActivity";
    private int min_distance = 100;
    private float downX, downY, upX, upY;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_gesture);
    }

    /*
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Log.d(DEBUG_TAG,"Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE) :
                Log.d(DEBUG_TAG,"Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d(DEBUG_TAG,"Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d(DEBUG_TAG,"Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d(DEBUG_TAG,"Movement occurred outside bounds " + "of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }
    */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_UP: {
                upX             = event.getX();
                upY             = event.getY();
                float deltaX    = downX - upX;
                float deltaY    = downY - upY;

                if( Math.abs(deltaX) > Math.abs(deltaY)) {
                    //HORIZONTAL SCROLL
                    if(Math.abs(deltaX) > min_distance) {
                        // left or right
                        if(deltaX < 0) {
                            Toast.makeText(this, "Left ro Right", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        if(deltaX > 0) {
                            Toast.makeText(this, "Right to Left", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    } else {
                        //not long enough swipe...
                        return false;
                    }
                } else {
                    //VERTICAL SCROLL
                    if(Math.abs(deltaY) > min_distance){
                        // top or down
                        if(deltaY < 0) {
                            Toast.makeText(this, "Top to Bottom", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        if(deltaY > 0) {
                            Toast.makeText(this, "Bottom to Top", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    } else {
                        //not long enough swipe...
                        return false;
                    }
                }
                return true;
            }
        }
        return super.onTouchEvent(event);
    }


}