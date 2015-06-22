package de.jalt.studeasy.client.tasks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import de.jalt.studeasy.client.MainActivity;
import de.jalt.studeasy.client.StudeasyScheduleApplication;

/**
 * AsyncTask zum ausloggen der User und beenden der Session.
 * @author Jan Mußenbrock und Lukas Erfkämper
 */
public class LogoutTask extends AsyncTask<Integer, Void, Boolean> {

    private Context context;
    private StudeasyScheduleApplication myApp;
    SharedPreferences sharedPreferences;

    public LogoutTask(Context context, StudeasyScheduleApplication myApp) {
        this.context = context;
        this.myApp = myApp;
    }

    @Override
    protected Boolean doInBackground(Integer... params){
        if(params.length != 1)
            return false;
        int sessionID = (int) params[0];
        try {
            Log.i("LogoutTask", "Session: " + sessionID);
            myApp.getStudeasyScheduleService().logout(sessionID);
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
            Log.i("LogoutTask", "erfolgreich");
            // Login Daten wieder löschen
            SavePreferences("USER", null);
            SavePreferences("PASSWORD", null);
            SavePreferences("SESSIONID", null);
            SavePreferences("TEACHER", "false");
            //Toast anzeigen
            CharSequence text = "Erfolgreich ausgeloggt";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            context.startActivity(new Intent(context, MainActivity.class));
        }
        else
        {
            Log.i("LogoutTask", "fehlgeschlagen");
            //Toast anzeigen
            CharSequence text = "Logout fehlgeschlagen!";
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