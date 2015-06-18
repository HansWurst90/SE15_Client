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

import common.ReturncodeResponse;
import common.UserLoginResponse;

/**
 * Logout as AsyncTask
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

    protected void onPostExecute(Boolean result)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if(result)
        {
            SavePreferences("USER", null);
            SavePreferences("PASSWORD", null);
            SavePreferences("SESSIONID", null);
            //Toast anzeigen
            CharSequence text = "Erfolgreich ausgeloggt";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            context.startActivity(new Intent(context, MainActivity.class));
        }
        else
        {
            //Toast anzeigen
            CharSequence text = "Logout fehlgeschlagen!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    private void SavePreferences(String key, String value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

}