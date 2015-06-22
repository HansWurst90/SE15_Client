package de.jalt.studeasy.client;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.jalt.studeasy.client.R;

import de.jalt.studeasy.client.tasks.LessonTask;

import java.util.List;

import de.jalt.studeasy.common.HomeworkTO;
import de.jalt.studeasy.common.LessonResponse;
import de.jalt.studeasy.common.LessonTO;

/**
 * Diese Aktivität bildet eine Unterichtsstunde ab.
 * Es können Informationen zu Fach, Lehrer, Zeiten und Hausaufgaben entnommen werden.
 * Lehrer können neue Hausaufgaben anlegen.
 * Schüler können ein Häkchen setzten für erledigte Hausaufgaben welches Lokal gespeichert wird.
 * @author Jan Mußenbrock und Lukas Erfkämper
 */
public class SubjectActivity extends ActionBarActivity {

    CheckBox homeworkButton;
    String saveKey;
    String teacherLogin;
    String teacherId;
    int lessonId;
    long dateInMillis;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        homeworkButton = (CheckBox) findViewById(R.id.homework_checkbutton);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        teacherLogin = sharedPreferences.getString("TEACHER", "");
        // Überprüfung ob ein Teacher oder Schüler sich anmeldet, wenn Teacher wird CheckButton ausgeblendet
        teacherLogin();

        // Empfangen der lessonId auf die geklickt wurde
        Intent whichSubjectId = getIntent();
        lessonId = whichSubjectId.getExtras().getInt("lessonId");
        teacherId = whichSubjectId.getExtras().getString("teacherId");
        dateInMillis = whichSubjectId.getExtras().getLong("dateInMillis");
        saveKey = "lesson"+lessonId+sharedPreferences.getString("USER", "");

        // Vorbereiten der Felder zum befüllen
        final TableRow headerRow1 = (TableRow) findViewById(R.id.headerRow1);
        final TableRow headerRow2 = (TableRow) findViewById(R.id.headerRow2);
        final TextView teacherTextView = (TextView) findViewById(R.id.teacher_value);
        final TextView fromTexView = (TextView) findViewById(R.id.from_value);
        final TextView toTexView = (TextView) findViewById(R.id.to_value);
        final TextView roomTextView = (TextView) findViewById(R.id.room_value);
        final TextView homeworkTextView = (TextView) findViewById(R.id.homework_value);
        final TextView descriptionTextView = (TextView) findViewById(R.id.description_value);
        // Abruf der Lesson und befüllung der Felder
        StudeasyScheduleApplication myApp = (StudeasyScheduleApplication) getApplication();
        new LessonTask(this, myApp) {
            @Override
            public void onPostExecute(LessonResponse result) {
                if (result != null) {
                    LessonTO lesson = result.getLesson();
                    headerRow1.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    headerRow2.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    descriptionTextView.setText(lesson.getSubject().getDescription());
                    teacherTextView.setText(GenderChooser.getTitleByGender(lesson.getTeacher().getGender()) + " " + lesson.getTeacher().getName());
                    fromTexView.setText(HourChooser.getTimesbyHour(lesson.getLessonHour())[0]);
                    toTexView.setText(HourChooser.getTimesbyHour(lesson.getLessonHour())[1]);
                    roomTextView.setText(lesson.getRoom());
                    homeworkTextView.setText(HomeworkArrayToString(lesson.getHomeworks()));
                } else {
                    Log.i("LessonTask", "fehlgeschlagen");
                }
            }
        }.execute(lessonId);
    }

    //Überprüfungsmethode
    private void teacherLogin()
    {
        if (teacherLogin.equals("true"))
        {
            homeworkButton.setVisibility(View.GONE);
        }
    }

    //Teacher Methode um neue Hausaufgaben anlegen zu können
    public void homeworkTextClick(View view) {
        if (teacherLogin.equals("true") && teacherId.equals(sharedPreferences.getString("USER", "")))
        {
            Intent getTeacherIntent = new Intent(this, TeacherActivity.class);
            getTeacherIntent.putExtra("lessonId", lessonId);
            getTeacherIntent.putExtra("teacherId", teacherId);
            getTeacherIntent.putExtra("dateInMillis", dateInMillis);
            startActivity(getTeacherIntent);
        }
    }

    //Aus der List werden alle Elemente zu einem String umgewandelt
    private String HomeworkArrayToString(List<HomeworkTO> list)
    {
        StringBuilder homeworkString = new StringBuilder();
        String string = "";
        for (int i = 0; i< list.size(); i++)
        {
            string = list.get(i).getDescription();
            homeworkString.append(string);
            homeworkString.append("\n");
        }
        return homeworkString.toString();
    }

    //Toast für die Schüler wenn Hausaufgaben gemacht oder nicht gemacht
    public void homeworkToast(View view) {
        boolean checked = homeworkButton.isChecked();
        if (checked) {
            Toast.makeText(this, R.string.homeworkDone, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, R.string.homeworkUndone, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent getMainIntent = new Intent(this, MainActivity.class);
        getMainIntent.putExtra("dateInMillis", dateInMillis);
        getMainIntent.putExtra("origin","subject");
        startActivity(getMainIntent);
    }

    //Um den Zustand vom Checkbutton zu speichern, selbst wenn die App geschlossen wurde
    @Override
    public void onPause() {
        super.onPause();
        save(homeworkButton.isChecked());
    }

    //Zustand vom Checkbutton aufrufen
    @Override
    public void onResume() {
        super.onResume();
        homeworkButton.setChecked(load());
    }

    private void save(final boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(saveKey, isChecked);
        editor.commit();
    }

    private boolean load() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(saveKey, false);
    }
}
