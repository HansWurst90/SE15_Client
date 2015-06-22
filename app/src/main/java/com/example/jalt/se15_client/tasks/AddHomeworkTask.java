package com.example.jalt.se15_client.tasks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.IntegerRes;
import android.util.Log;
import android.widget.Toast;

import com.example.jalt.se15_client.MainActivity;
import com.example.jalt.se15_client.StudeasyScheduleApplication;
import com.example.jalt.se15_client.SubjectActivity;

import common.UserLoginResponse;

/**
 * AsynkTask zum hinzufüge einer Hausaufgabe zur Hausaufgabenliste einer Unterichtsstunde
 * @author Jan Mußenbrock und Lukas Erfkämper
 */
public class AddHomeworkTask extends AsyncTask<Object, Void, Boolean> {

    private Context context;
    private StudeasyScheduleApplication myApp;
    SharedPreferences sharedPreferences;
    int lessonID;
    String teacherId;

    public AddHomeworkTask (Context context, StudeasyScheduleApplication myApp) {
        this.context = context;
        this.myApp = myApp;
    }

    @Override
    protected Boolean doInBackground(Object... params){
        if(params.length != 4)
        {
            return false;
        }
        // Empfangen der Parameter und Cast
        int sessionID = (int) params[0];
        lessonID = (int) params[1];
        String description = (String) params[2];
        teacherId = (String) params[3];
        try {
            Log.i("AddHomeworkTask", "sessionID: " + sessionID + ", lessonID: " + lessonID + ", description: " + description + ", teacherId: " + teacherId);
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

    // Nach Erfolgreicher Anlage der Hausaufgaben soll zur SubjectActivity zurückgekehrt werden
    // Dazu sind lessonID und teacherName nötig. Siehe SubjectActivity.
    protected void onPostExecute(Boolean result)
    {
        if(result)
        {
            Log.i("AddHomeworkTask", "erfolgreich");
            CharSequence text = "Hausaufgaben angelegt";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Intent getSubjectIntent = new Intent(context, SubjectActivity.class);
            getSubjectIntent.putExtra("lessonId", lessonID);
            getSubjectIntent.putExtra("name", teacherId);
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
