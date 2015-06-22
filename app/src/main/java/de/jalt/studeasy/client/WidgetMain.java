package de.jalt.studeasy.client;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.jalt.studeasy.client.R;


/**
 * Klasse des Widgets
 * @author Lukas Erfkämper und Jan Mußenbrock
 */
public class WidgetMain extends AppWidgetProvider {

    /**
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_main);
        remoteViews.setOnClickPendingIntent(R.id.refresh_button, buildButtonPendingIntent(context));
        pushWidgetUpdate(context, remoteViews);
    }

    /**
     *
     * @param context
     * @return
     */
    public static PendingIntent buildButtonPendingIntent(Context context) {
        Intent intent = new Intent();
        intent.setAction("pl.looksok.intent.action.REFRESH");
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    /**
     *
     * @param context
     * @param remoteViews
     */
    public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
        ComponentName myWidget = new ComponentName(context, WidgetMain.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, remoteViews);
    }
}

