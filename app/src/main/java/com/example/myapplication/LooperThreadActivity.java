package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.example.myapplication.handlers.ExampleLooperThread;

import static com.example.myapplication.handlers.ExampleHandler.TASK_A;
import static com.example.myapplication.handlers.ExampleHandler.TASK_B;

public class LooperThreadActivity extends AppCompatActivity {

    private static final String TAG             = "LooperThreadActivity";
    private ExampleLooperThread looperThread    = new ExampleLooperThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper_thread);
    }

    public void startThread(View view) {
        looperThread.start();
    }

    public void stopThread(View view) {
        looperThread.looper.quit();
    }

    public void taskA(View view) {
        Message msg = Message.obtain();
        msg.what    = TASK_A;
        looperThread.handler.sendMessage(msg);
        /*Handler threadHandler = new Handler(looperThread.looper);
        threadHandler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Log.d(TAG, "run: " + i);
                    SystemClock.sleep(1000);
                }
            }
        });*/
    }

    public void taskB(View view) {
        Message msg = Message.obtain();
        msg.what    = TASK_B;
        looperThread.handler.sendMessage(msg);
    }

}