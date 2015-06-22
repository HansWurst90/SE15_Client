package com.example.jalt.se15_client;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jalt.se15_client.tasks.LessonTask;

import common.LessonResponse;
import common.LessonTO;


/**
 * Implementation of App Widget functionality.
 */
public class WidgetMain extends AppWidgetProvider {

    //RemoteViews views;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_main);
        remoteViews.setOnClickPendingIntent(R.id.refresh_button, buildButtonPendingIntent(context));
        pushWidgetUpdate(context, remoteViews);
    }

    public static PendingIntent buildButtonPendingIntent(Context context) {
        Intent intent = new Intent();
        intent.setAction("pl.looksok.intent.action.REFRESH");
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
        ComponentName myWidget = new ComponentName(context, WidgetMain.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, remoteViews);
    }

    /** @Override
    public void onReceive (Context context, Intent intent)
    {
        if ("Refresh".equals(intent.getAction())) {
            views.setTextViewText(R.id.widgetRow1, "Hat geklappt");
        }
        else
            views.setTextViewText(R.id.widgetRow1, "nooooo");
    } */


    /** @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    } */

}

