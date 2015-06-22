package de.jalt.studeasy.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        sharedPreferences = context.getSharedPreferences(WidgetMainIntentReceiver.class + "",Context.MODE_WORLD_READABLE);
        remoteViews.setTextViewText(R.id.widgetRow1, sharedPreferences.getString("LESSONROW1", ""));
        remoteViews.setTextViewText(R.id.widgetRow2, sharedPreferences.getString("LESSONROW2", ""));
        remoteViews.setTextViewText(R.id.widgetRow3, sharedPreferences.getString("LESSONROW3", ""));
        remoteViews.setTextViewText(R.id.widgetRow4, sharedPreferences.getString("LESSONROW4", ""));
        remoteViews.setTextViewText(R.id.widgetRow5, sharedPreferences.getString("LESSONROW5", ""));
        remoteViews.setTextViewText(R.id.widgetRow6, sharedPreferences.getString("LESSONROW6", ""));

        //Aktualisierung des ButtonClickListeners
        remoteViews.setOnClickPendingIntent(R.id.refresh_button, WidgetMain.buildButtonPendingIntent(context));

        WidgetMain.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
    }
}
