package com.example.myapplication.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.WidgetResizeActivity;

import static com.example.myapplication.ExampleAppWidgetConfig2.SHARED_PREFS;
import static com.example.myapplication.ExampleAppWidgetConfig2.KEY_BUTTON_TEXT;

public class ExampleAppWidgetProvider3 extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent intent               = new Intent(context, WidgetResizeActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            SharedPreferences prefs     = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            String buttonText           = prefs.getString(KEY_BUTTON_TEXT + appWidgetId, "Press me");
            RemoteViews views           = new RemoteViews(context.getPackageName(), R.layout.example_widget_3);
            views.setOnClickPendingIntent(R.id.example_widget_button, pendingIntent);
            views.setCharSequence(R.id.example_widget_button, "setText", buttonText);
            Bundle appWidgetOptions = appWidgetManager.getAppWidgetOptions(appWidgetId);
            resizeWidget(appWidgetOptions, views);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_widget_3);
        resizeWidget(newOptions, views);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private void resizeWidget(Bundle appWidgetOptions, RemoteViews views) {
        int minWidth    = appWidgetOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        int maxWidth    = appWidgetOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH);
        int minHeight   = appWidgetOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
        int maxHeight   = appWidgetOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT);
        if (maxHeight > 200) {
            views.setViewVisibility(R.id.example_widget_text, View.VISIBLE);
        } else {
            views.setViewVisibility(R.id.example_widget_text, View.GONE);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Toast.makeText(context, "onDeleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context) {
        Toast.makeText(context, "onEnabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisabled(Context context) {
        Toast.makeText(context, "onDisabled", Toast.LENGTH_SHORT).show();
    }
}