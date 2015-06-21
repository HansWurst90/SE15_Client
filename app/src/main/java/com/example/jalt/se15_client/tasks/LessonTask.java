package com.example.jalt.se15_client.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jalt.se15_client.MainActivity;
import com.example.jalt.se15_client.R;
import com.example.jalt.se15_client.StudeasyScheduleApplication;

import common.LessonResponse;
import common.LessonTO;


/**
 * AsyncTask um eine Lesson abzuholen
 * @author Jan Mußenbrock und Lukas Erfkämper
 */
public class LessonTask  extends AsyncTask<Object, LessonResponse, LessonResponse>{

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
    protected LessonResponse doInBackground(Object... params){
        if(params.length != 1)
            return null;
        lessonID = (int) params[0];
        try {
            Log.i("LessonTask", "ID: " + lessonID);
            LessonResponse myResponse = myApp.getStudeasyScheduleService().findLessonById(lessonID);
            publishProgress(myResponse);
            return myResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onProgressUpdate(LessonResponse result)
    {

    }

    /**
     * onPostExecute wird bei Methodenaufruf überschrieben, um in der jeweiligen Activity die Daten
     * einzufügen
     * @param result
     */
    protected void onPostExecute(LessonResponse result)
    {    }
}
