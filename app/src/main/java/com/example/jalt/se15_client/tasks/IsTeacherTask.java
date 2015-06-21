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

import common.BooleanResponse;

/**
 * AsyncTask zur Überprüfung, ob Teacher oder nicht
 * @author Jan Mußenbrock und Lukas Erfkämper
 */
public class IsTeacherTask extends AsyncTask<Object, Void, BooleanResponse> {

    private Context context;
    private StudeasyScheduleApplication myApp;
    private int sessionID;
    SharedPreferences sharedPreferences;

    public IsTeacherTask(Context context, StudeasyScheduleApplication myApp) {
        this.context = context;
        this.myApp = myApp;
    }

    @Override
    /**
     * myResponse wird vorbereitet
     */
    protected BooleanResponse doInBackground(Object... params){
        if(params.length != 1)
            return null;
        sessionID = (int) params[0];
        try {
            Log.i("IsTeacherTask", "ID: " + sessionID);
            BooleanResponse myResponse = myApp.getStudeasyScheduleService().isUserTeacher(sessionID);
            return myResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onProgessUpdate(Integer... values)
    {

    }

    /**
     * result Auswertung und sharedPreference erzeugen.
     * Bei Erfolg werden User und SessionId persistiert, für das Passwort wird zu Anzeigezwecken ****** gespeichert
     * Ebenfalls wird ein Toast angezeigt um den User zu begrüßen.
     * Bei Misserfolg wird darauf hingewiesen, dass das Login nicht funktioniert hat.
     * @param result
     */
    protected void onPostExecute(BooleanResponse result)
    {
        if(result != null)
        {
            Log.i("IsTeacherTask", "erfolgreich");
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            boolean teacher = result.isSuccessfull();
            String teachertext = String.valueOf(teacher);
            SavePreferences("TEACHER", teachertext);
        }
        else
        {
            Log.i("IsTeacherTask", "fehlgeschlagen");
            CharSequence text = "Lehrerabfrage fehlgeschlagen!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    /**
     * Persistieren in die SharedPreference
     * @param key
     * @param value
     */
    private void SavePreferences(String key, String value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}