package com.example.jalt.se15_client.tasks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.jalt.se15_client.MainActivity;
import com.example.jalt.se15_client.StudeasyScheduleApplication;

import java.util.Date;

import common.LessonByIDResponse;
import common.LessonTO;
import common.RoomTO;
import common.SubjectTO;
import common.TeacherTO;
import common.UserLoginResponse;

/**
 * Created by ErfiMac on 19.06.15.
 * AsyncTask zum Abrufen der Lessons
 * @author Lukas Erfkämper
 */

public class LessonTask  extends AsyncTask<Integer, Void, LessonByIDResponse>{

    private Context context;
    private StudeasyScheduleApplication myApp;
    private int lessonID;

    public LessonTask(Context context, StudeasyScheduleApplication myApp) {
        this.context = context;
        this.myApp = myApp;
    }

    @Override
    /**
     * myResponse vorbereiten
     */
    protected LessonByIDResponse doInBackground(Integer... params){
        if(params.length != 1)
            return null;
        lessonID = (int) params[0];
        try {
            LessonByIDResponse myResponse = myApp.getStudeasyScheduleService().findLessonById(lessonID);
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
     * result Auswertung, bei Erfolg werden die Lessons angezeigt, ansonsten Fehlermeldung
     * @param result
     */
    protected void onPostExecute(UserLoginResponse result)
    {
        if(result != null)
        {
            //Toast anzeigen
            Toast.makeText(context, "hat geklappt", Toast.LENGTH_LONG).show();
            }
        else
        {
            //Toast anzeigen
            Toast.makeText(context, "nöööööö", Toast.LENGTH_LONG).show();
        }
    }
}
