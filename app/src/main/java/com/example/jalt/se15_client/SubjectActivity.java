package com.example.jalt.se15_client;

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

import com.example.jalt.se15_client.tasks.LessonTask;

import java.util.ArrayList;
import java.util.List;

import common.HomeworkTO;
import common.LessonResponse;
import common.LessonTO;

/**
 *
 * @author Mußenbrock
 */

public class SubjectActivity extends ActionBarActivity {

    CheckBox homeworkButton;
    TextView homeworkText;
    String saveKey;
    String teacherLogin;
    static int lessonId;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        homeworkButton = (CheckBox) findViewById(R.id.homework_checkbutton);
        homeworkText = (TextView) findViewById(R.id.homework_value);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        teacherLogin = sharedPreferences.getString("TEACHER", "");
        teacherLogin();

        // Empfangen der lessonId auf die geklickt wurde
        Intent whichSubjectId = getIntent();
        lessonId = whichSubjectId.getExtras().getInt("lessonId");
        Toast.makeText(this, "LessonId: " + String.valueOf(lessonId), Toast.LENGTH_SHORT).show();
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
        new LessonTask(this, myApp){
            @Override
            public void onPostExecute(LessonResponse result)
            {
                LessonTO lesson = result.getLesson();
                headerRow1.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                headerRow2.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                descriptionTextView.setText(lesson.getSubject().getDescription());
                teacherTextView.setText(GenderChooser.getTitleByGender(lesson.getTeacher().getGender()) + " " + lesson.getTeacher().getName());
                fromTexView.setText(HourChooser.getTimesbyHour(lesson.getLessonHour())[0]);
                toTexView.setText(HourChooser.getTimesbyHour(lesson.getLessonHour())[1]);
                roomTextView.setText(lesson.getRoom());
                homeworkTextView.setText(HomeworkArrayToString(lesson.getHomeworks()));
            }
        }.execute(lessonId);
    }

    private void teacherLogin()
    {
        if (teacherLogin.equals("true"))
        {
            homeworkButton.setVisibility(View.GONE);
        }
    }

    public void homeworkTextClick(View view) {
        if (teacherLogin.equals("true"))
        {
            Intent getSubjectIntent = new Intent(this, TeacherActivity.class);
            getSubjectIntent.putExtra("lessonId", lessonId);
            startActivity(getSubjectIntent);
        }
    }

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
    public void onPause() {
        super.onPause();
        save(homeworkButton.isChecked());
    }

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
