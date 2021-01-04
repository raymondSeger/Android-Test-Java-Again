package com.example.myapplication;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class ExampleService extends Service {

    /*

    Service - This runs on the same main thread which invokes this service and performs some background operation.
    For any long running operation happening on the main thread it is recommended to create a new thread and do the job (eg; Handler)
    by not impacting the main thread's performance.
    Drawback : Runs on main thread

    IntentService - Intent service also helps in doing some long running (indefinite) background task.
    The only difference is that it creates a new thread to perform this task and does not run on the main thread.
    Does the given job on it's onHandleIntent.
    Drawback: The job given to the IntentService would get lost when the application is killed

    JobIntentService - Job intent service is very similar to IntentService but with few benefits like the application can kill this
    job at any time and it can start the job from the beginning once the application gets recreated/up.

    Since Android Oreo, idle apps can’t keep background services running anymore.
    So if you need to do operations even if your app is not running in the foreground, you should use a JobScheduler instead.

     */

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");

        //do heavy work on a background thread
        //stopSelf();

        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public ExampleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}