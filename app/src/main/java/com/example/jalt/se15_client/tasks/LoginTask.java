package com.example.jalt.se15_client.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jalt.se15_client.StudeasyScheduleApplication;

import java.util.Objects;

import common.UserLoginResponse;

/**
 * Logout as AsyncTask
 */
public class LoginTask extends AsyncTask<Object, Void, UserLoginResponse> {

    private Context context;
    private StudeasyScheduleApplication myApp;

    public LoginTask(Context context, StudeasyScheduleApplication myApp) {
        this.context = context;
        this.myApp = myApp;
    }

    @Override
    protected UserLoginResponse doInBackground(Object... params){
        if(params.length != 2)
            return null;
        int personid = (int) params[0];
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
        //wird in diesem Beispiel nicht verwendet
    }

    protected void onPostExecute(UserLoginResponse result)
    {
        if(result != null)
        {
            //Toast anzeigen
            CharSequence text = "Login erfolgreich! ID: " + result.getSessionID();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

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
}