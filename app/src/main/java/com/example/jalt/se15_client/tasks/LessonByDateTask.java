package com.example.jalt.se15_client.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.jalt.se15_client.StudeasyScheduleApplication;

import common.LessonResponse;
import common.LessonTO;

/**
 *
 * @author Jan Mußenbrock und Lukas Erfkämper
 */
public class LessonByDateTask extends AsyncTask<Object, Void, LessonResponse> {
    private Context context;
    private StudeasyScheduleApplication myApp;
    private int sessionID;
    private String date;
    private int hour;

    public LessonByDateTask(Context context, StudeasyScheduleApplication myApp) {
        this.context = context;
        this.myApp = myApp;
    }

    @Override
    /**
     * myResponse vorbereiten
     */
    protected LessonResponse doInBackground(Object... params){
        if(params.length != 3)
            return null;
        sessionID = (int) params[0];
        date = (String) params[1];
        hour = (int) params[2];
        try {
            Log.i("LessonByDateTask", "ID: " + sessionID + ", date: " + date + ", hour: " + hour);
            LessonResponse myResponse = myApp.getStudeasyScheduleService().getLessonByDate(sessionID, date, hour);
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
    protected void onPostExecute(LessonResponse result)
    {
        if(result != null)
        {
            Log.i("LessonByDateTask", "erfolgreich");
            LessonTO lesson = new LessonTO();
            lesson = result.getLesson();
            int lessonId = lesson.getLessonID();
            int lessonHour = lesson.getLessonHour();
            String date = lesson.getDate();
            String genderT = "" + lesson.getTeacher().getGender();
            String nameT = lesson.getTeacher().getName();
            String subjectDescription = lesson.getSubject().getDescription();
            int subjectId = lesson.getSubject().getSubjectID();
            String room = lesson.getRoom();
            CharSequence text = lessonId + " " + lessonHour + " " + date + " " + genderT + " " + nameT + " " + subjectDescription + " " + subjectId + " " + room;
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            Toast.makeText(context, "LessonByDate Abfrage erfolgreich.", Toast.LENGTH_LONG).show();

        }
        else
        {

            Log.i("LessonByDateTask", "fehlgeschlagen");
            Toast.makeText(context, "LessonByDate Abfrage fehlgeschlagen.", Toast.LENGTH_LONG).show();
        }
    }
}

