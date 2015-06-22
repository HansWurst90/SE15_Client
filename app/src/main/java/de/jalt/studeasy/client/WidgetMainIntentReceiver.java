package de.jalt.studeasy.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.jalt.studeasy.client.R;

/**
 * Die Intent Receiver Klasse für das Widget
 * Created by ErfiMac on 22.06.15.
 * @author Lukas Erfkämper und Jan Mußenbrock
 */
public class WidgetMainIntentReceiver extends BroadcastReceiver {

    SharedPreferences sharedPreferences;

    /**
     * OnReceive Methode die überschrieben wird
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("pl.looksok.intent.action.REFRESH")){
            updateWidgetPictureAndButtonListener(context);
        }
    }

    /**
     * Methode zum updaten der TextViews
     * @param context
     */
    private void updateWidgetPictureAndButtonListener(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        remoteViews.setTextViewText(R.id.widgetRow1, sharedPreferences.getString("LESSONROW1", ""));
        remoteViews.setInt(R.id.widgetRow1, "setBackgroundColor", android.graphics.Color.argb(120, 255, 0, 0));
        remoteViews.setTextViewText(R.id.widgetRow2, sharedPreferences.getString("LESSONROW2", ""));
        remoteViews.setInt(R.id.widgetRow2, "setBackgroundColor", android.graphics.Color.argb(120, 0, 0, 255));
        remoteViews.setTextViewText(R.id.widgetRow3, sharedPreferences.getString("LESSONROW3", ""));
        remoteViews.setInt(R.id.widgetRow3, "setBackgroundColor", android.graphics.Color.argb(120,0,255,0));
        remoteViews.setTextViewText(R.id.widgetRow4, sharedPreferences.getString("LESSONROW4", ""));
        remoteViews.setInt(R.id.widgetRow4, "setBackgroundColor", android.graphics.Color.argb(120,255,255,0));
        remoteViews.setTextViewText(R.id.widgetRow5, sharedPreferences.getString("LESSONROW5", ""));
        remoteViews.setInt(R.id.widgetRow5, "setBackgroundColor", android.graphics.Color.argb(120,255,0,255));
        remoteViews.setTextViewText(R.id.widgetRow6, sharedPreferences.getString("LESSONROW6", ""));
        remoteViews.setInt(R.id.widgetRow6, "setBackgroundColor", android.graphics.Color.argb(120,0,255,255));

        //Aktualisierung des ButtonClickListeners
        remoteViews.setOnClickPendingIntent(R.id.refresh_button, WidgetMain.buildButtonPendingIntent(context));

        WidgetMain.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
    }
}
