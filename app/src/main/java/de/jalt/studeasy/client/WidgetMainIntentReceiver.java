package de.jalt.studeasy.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.jalt.studeasy.client.R;

/**
 * Created by ErfiMac on 22.06.15.
 */
public class WidgetMainIntentReceiver extends BroadcastReceiver {

    SharedPreferences sharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("pl.looksok.intent.action.REFRESH")){
            updateWidgetPictureAndButtonListener(context);
        }
    }

    private void updateWidgetPictureAndButtonListener(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_main);
        sharedPreferences = context.getSharedPreferences(WidgetMainIntentReceiver.class + "",Context.MODE_WORLD_READABLE);
        remoteViews.setTextViewText(R.id.widgetRow1, sharedPreferences.getString("LESSONROW1", ""));
        remoteViews.setTextViewText(R.id.widgetRow2, sharedPreferences.getString("LESSONROW2", ""));
        remoteViews.setTextViewText(R.id.widgetRow3, sharedPreferences.getString("LESSONROW3", ""));
        remoteViews.setTextViewText(R.id.widgetRow4, sharedPreferences.getString("LESSONROW4", ""));
        remoteViews.setTextViewText(R.id.widgetRow5, sharedPreferences.getString("LESSONROW5", ""));
        remoteViews.setTextViewText(R.id.widgetRow6, sharedPreferences.getString("LESSONROW6", ""));

        //REMEMBER TO ALWAYS REFRESH YOUR BUTTON CLICK LISTENERS!!!
        remoteViews.setOnClickPendingIntent(R.id.refresh_button, WidgetMain.buildButtonPendingIntent(context));

        WidgetMain.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
    }
}
