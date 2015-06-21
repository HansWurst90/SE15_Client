package com.example.jalt.se15_client.tasks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.jalt.se15_client.MainActivity;
import com.example.jalt.se15_client.StudeasyScheduleApplication;
import com.example.jalt.se15_client.SubjectActivity;

import common.UserLoginResponse;

/**
 * Created by Jan on 21.06.15.
 */
public class AddHomeworkTask extends AsyncTask<Object, Void, Boolean> {

    private Context context;
    private StudeasyScheduleApplication myApp;
    SharedPreferences sharedPreferences;
    int lessonID;
    String teacherName;

    public AddHomeworkTask (Context context, StudeasyScheduleApplication myApp) {
        this.context = context;
        this.myApp = myApp;
    }

    @Override
    /**
     * myResponse wird vorbereitet
     */
    protected Boolean doInBackground(Object... params){
        if(params.length != 4)
            return false;
        int sessionID = (int) params[0];
        lessonID = (int) params[1];
        String description = (String) params[2];
        teacherName = (String) params[3];
        try {
            Log.i("AddHomeworkTask", "sessionID: " + sessionID + "lessonID: " + lessonID + "description: " + description + "teacherName: " + teacherName);
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
            Log.i("AddHomeworkTask", "erfolgreich");
            CharSequence text = "Hausaufgaben angelegt";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Intent getSubjectIntent = new Intent(context, SubjectActivity.class);
            getSubjectIntent.putExtra("lessonId", lessonID);
            getSubjectIntent.putExtra("name", teacherName);
            context.startActivity(getSubjectIntent);
        }
        else
        {
            Log.i("AddHomeworkTask", "fehlgeschlagen");
            //Toast anzeigen
            CharSequence text = "Hausaufgabe anlegen fehlgeschlagen";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
