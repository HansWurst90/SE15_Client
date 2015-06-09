package com.example.jalt.se15_client;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import studeasy.entities.Lesson;

/**
 *
 * @author Mußenbrock
 */

public class SubjectActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);


        String thisLessonDescription = null;
        String thisLessonTeacher = null;
        int thisLessonHour = 0;
        int thisLessonColor = R.color.white;

        // Intent whichSubjectId = getIntent();
        // int subjectId = whichSubjectId.getExtras().getInt("subjectId");
        // String thisLessonDescription = thisLesson.getSubject().getDescription();
        // String thisLessonTeacher = thisLesson.getTeacher().getName();
        // int thisLessonHour = thisLesson.getLessonHour();
        // thisLesson.getHomeworks();

        // Testcases für 2 Testfächer
        int subjectId = 2;
        switch (subjectId) {
            case 1:
                thisLessonDescription = "Hauswirtschaft";
                thisLessonTeacher = "Hans Wurst";
                thisLessonHour = 1;
                break;
            case 2:
                thisLessonDescription = "Maschinenbau";
                thisLessonTeacher = "Hans Druckluft";
                thisLessonHour = 2;
                break;
        }

        // Abhängig der FachId wird eine Farbe gewählt
        switch (subjectId) {
            default:
                thisLessonColor = R.color.white;
                break;
            case 1:
                thisLessonColor = R.color.Red;
                break;
            case 2:
                thisLessonColor = R.color.Orange;
                break;
            case 3:
                thisLessonColor = R.color.Purple;
                break;
            case 4:
                thisLessonColor = R.color.Indigo;
                break;
            case 5:
                thisLessonColor = R.color.Light_Blue;
                break;
            case 6:
                thisLessonColor = R.color.Teal;
                break;
            case 7:
                thisLessonColor = R.color.Light_Green;
                break;
            case 8:
                thisLessonColor = R.color.Yellow;
                break;
            case 9:
                thisLessonColor = R.color.SuperLight_Blue;
                break;
            // 10. Farbe festlegen
            case 10:
                thisLessonColor = R.color.SuperLight_Blue;
                break;
        }

        //Abhängig der Stunde werdne die Star- und Endzeiten gewählt
        String from = null;
        String to = null;
        switch (thisLessonHour) {
            default:
                from = "Error";
                to = "Error";
                break;
            case 1:
                from = "08:30";
                to = "09:15";
                break;
            case 2:
                from = "09:15";
                to = "10:00";
                break;
            case 3:
                from = "10:15";
                to = "11:00";
                break;
            case 4:
                from = "11:00";
                to = "11:45";
                break;
            case 5:
                from = "12:00";
                to = "12:45";
                break;
            case 6:
                from = "12:45";
                to = "13:30";
                break;
        }
        final TableRow headerRow1 = (TableRow) findViewById(R.id.headerRow1);
        final TableRow headerRow2 = (TableRow) findViewById(R.id.headerRow2);
        headerRow1.setBackgroundResource(thisLessonColor);
        headerRow2.setBackgroundResource(thisLessonColor);
        final TextView descriptionTextView = (TextView) findViewById(R.id.description_value);
        descriptionTextView.setText(thisLessonDescription);
        final TextView teacherTextView = (TextView) findViewById(R.id.teacher_value);
        teacherTextView.setText(thisLessonTeacher);
        final TextView fromTexView = (TextView) findViewById(R.id.from_value);
        fromTexView.setText(from);
        final TextView toTexView = (TextView) findViewById(R.id.to_value);
        toTexView.setText(to);
    }


        public void homeworkToast(View view) {
        final CheckBox homeworkButton = (CheckBox) findViewById(R.id.homework_checkbutton);
        boolean checked = homeworkButton.isChecked();
        if (checked) {
           Toast.makeText(this, R.string.homeworkDone, Toast.LENGTH_SHORT).show();
        }
        else {
           Toast.makeText(this, R.string.homeworkUndone, Toast.LENGTH_SHORT).show();
        }

    }

    /*@Override
    public void onPause()
    {

        super.onPause();
        save(itemChecked);
    }
    @Override
    public void onResume()
    {
        super.onResume();
        checkOld = load();

        for (int i = 0 ; i < checkOld.length; i++)
        {
            notes.ctv.get(i).setChecked(checkOld[i]);
        }
    }
    @Override
    public void onRestart()
    {
        super.onResume();
        checkOld = load();

        for (int i = 0 ; i < checkOld.length; i++)
        {
            notes.ctv.get(i).setChecked(checkOld[i]);
        }
    }

    private void save(final boolean[] isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        insertState();
        for (Integer i = 0; i < isChecked.length; i++) {
            editor.putBoolean(i.toString(), isChecked[i]);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        save(mCheckBox.isChecked());
    }

    @Override
    public void onResume() {
        super.onResume();
        mCheckBox.setChecked(load());
    }

    private void save(final boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("check", isChecked);
        editor.commit();
    }

    private boolean load() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("check", false);
    }
*/

}
