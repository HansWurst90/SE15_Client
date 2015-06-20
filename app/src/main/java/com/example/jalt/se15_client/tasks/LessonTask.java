package com.example.jalt.se15_client.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jalt.se15_client.StudeasyScheduleApplication;

import common.LessonResponse;
import common.LessonTO;


/**
 * Created by ErfiMac on 19.06.15.
 * AsyncTask zum Abrufen der Lessons
 * @author Lukas Erfkämper
 */

public class LessonTask  extends AsyncTask<Integer, Void, LessonResponse>{

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
    protected LessonResponse doInBackground(Integer... params){
        if(params.length != 1)
            return null;
        lessonID = (int) params[0];
        try {
            LessonResponse myResponse = myApp.getStudeasyScheduleService().findLessonById(lessonID);
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
            //Toast anzeigen
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
            }
        else
        {
            //Toast anzeigen
            Toast.makeText(context, "nöööööö", Toast.LENGTH_LONG).show();
        }
    }
}
