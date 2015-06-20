package com.example.jalt.se15_client.tasks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.jalt.se15_client.MainActivity;
import com.example.jalt.se15_client.StudeasyScheduleApplication;

import java.util.Objects;

import common.UserLoginResponse;

/**
 * Logout as AsyncTask
 * @author Jan Mußenbrock und Lukas Erfkämper
 */
public class LoginTask extends AsyncTask<Object, Void, UserLoginResponse> {

    private Context context;
    private StudeasyScheduleApplication myApp;
    private int personid;
    SharedPreferences sharedPreferences;

    public LoginTask(Context context, StudeasyScheduleApplication myApp) {
        this.context = context;
        this.myApp = myApp;
    }

    @Override
    /**
     * myResponse wird vorbereitet
     */
    protected UserLoginResponse doInBackground(Object... params){
        if(params.length != 2)
            return null;
        personid = (int) params[0];
        String password = (String) params[1];
        try {
            UserLoginResponse myResponse = myApp.getStudeasyScheduleService().login(personid, password);
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
    protected void onPostExecute(UserLoginResponse result)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if(result != null)
        {
            // Persistierung
            SavePreferences("USER", "" + personid);
            SavePreferences("PASSWORD", "******");
            SavePreferences("NAME", result.getName());
            SavePreferences("FIRSTNAME", result.getFirstname());
            SavePreferences("SESSIONID", "" + result.getSessionID());
            //Toast anzeigen
            CharSequence text = "Willkommen User " + personid;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            context.startActivity(new Intent(context, MainActivity.class));
        }
        else
        {
            //Toast anzeigen
            CharSequence text = "Login fehlgeschlagen!";
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