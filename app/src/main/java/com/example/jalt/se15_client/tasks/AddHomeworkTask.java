package com.example.jalt.se15_client.tasks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.jalt.se15_client.MainActivity;
import com.example.jalt.se15_client.StudeasyScheduleApplication;

import common.UserLoginResponse;

/**
 * Created by Jan on 21.06.15.
 */
public class AddHomeworkTask extends AsyncTask<Object, Void, Boolean> {

    private Context context;
    private StudeasyScheduleApplication myApp;
    SharedPreferences sharedPreferences;

    public AddHomeworkTask (Context context, StudeasyScheduleApplication myApp) {
        this.context = context;
        this.myApp = myApp;
    }

    @Override
    /**
     * myResponse wird vorbereitet
     */
    protected Boolean doInBackground(Object... params){
        if(params.length != 3)
            return false;
        int sessionID = (int) params[0];
        int lessonID = (int) params[1];
        String description = (String) params[2];
        try {
            myApp.getStudeasyScheduleService().createHomework(sessionID, lessonID, description);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void onProgessUpdate(Integer... values)
    {
        //wird in diesem Beispiel nicht verwendet
    }

    /**
     * result Auswertung und sharedPreference erzeugen.
     * Bei Erfolg werden User und SessionId und Passwort wieder gelöscht
     * Ebenfalls wird ein Toast angezeigt um den User zu verabschieden.
     * Bei Misserfolg wird darauf hingewiesen, dass das Logout nicht funktioniert hat.
     * @param result
     */
    protected void onPostExecute(Boolean result)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if(result)
        {
            CharSequence text = "Hausaufgaben angelegt";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else
        {
            //Toast anzeigen
            CharSequence text = "Hausaufgaben anlegen fehlgeschlagen";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
