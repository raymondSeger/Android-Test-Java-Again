package com.example.myapplication.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.example.myapplication.R;
import com.example.myapplication.WidgetActivity;
import com.example.myapplication.WidgetActivity2;

import static com.example.myapplication.ExampleAppWidgetConfig.KEY_BUTTON_TEXT;
import static com.example.myapplication.ExampleAppWidgetConfig.SHARED_PREFS;

public class ExampleAppWidgetProvider2 extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent intent               = new Intent(context, WidgetActivity2.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            SharedPreferences prefs     = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            String buttonText           = prefs.getString(KEY_BUTTON_TEXT + appWidgetId, "Press me");
            RemoteViews views           = new RemoteViews(context.getPackageName(), R.layout.example_widget_2);
            views.setOnClickPendingIntent(R.id.example_widget_button, pendingIntent);
            views.setCharSequence(R.id.example_widget_button, "setText", buttonText);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}