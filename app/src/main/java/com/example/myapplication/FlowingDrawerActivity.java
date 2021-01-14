package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

public class FlowingDrawerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowing_drawer);

        FlowingDrawer mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
             @Override
             public void onDrawerStateChange(int oldState, int newState) {
                 if (newState == ElasticDrawer.STATE_CLOSED) {
                     Log.i("FlowingDrawerActivity", "Drawer STATE_CLOSED");
                 }
             }

             @Override
             public void onDrawerSlide(float openRatio, int offsetPixels) {
                 Log.i("FlowingDrawerActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
             }
         });

    }
}