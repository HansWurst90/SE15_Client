package com.example.jalt.se15_client;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import common.HomeworkTO;
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

        // Beispiellesson:
        List<LessonTO> lessonList = new ArrayList<>();
        lessonList.addAll(TestLessons.getLessons1());
        lessonList.addAll(TestLessons.getLessons2());
        lessonList.addAll(TestLessons.getLessons3());
        lessonList.addAll(TestLessons.getLessons4());
        lessonList.addAll(TestLessons.getLessons5());
        LessonTO thisLesson = lessonList.get(3);

        // "Abholen" der entsprechenden Lesson vom Server:
        for (LessonTO lesson : lessonList) {
            if (lesson.getLessonID() == lessonId) {
                thisLesson = lesson;
            }
        }

        // Sammlen der Daten
        String thisLessonDescription = thisLesson.getSubject().getDescription();
        String thisLessonTeacherName = thisLesson.getTeacher().getName();
        char thisLessonTeacherGender = thisLesson.getTeacher().getGender();
        int thisLessonHour = thisLesson.getLessonHour();
        String thisLessonRoom = thisLesson.getRoom();
        /** String thisLessonHomework = String.join(", ", thisLesson.getHomeworks()); */
        String thisLessonHomework = HomeworkArrayToString(thisLesson.getHomeworks());
        int thisLessonColor = ColorChooser.getColorFromId(thisLesson.getSubject().getSubjectID());
        // thisLesson.getHomeworks();
        //Abhängig des Geschlechts wird die Anrede gesetzt
        String thisLessonTeacherTitle = GenderChooser.getTitleByGender(thisLessonTeacherGender);
        saveKey = "lesson"+thisLesson.getLessonID();

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
        teacherTextView.setText(thisLessonTeacherTitle + " " + thisLessonTeacherName);
        final TextView fromTexView = (TextView) findViewById(R.id.from_value);
        fromTexView.setText(from);
        final TextView toTexView = (TextView) findViewById(R.id.to_value);
        toTexView.setText(to);
        final TextView roomTextView = (TextView) findViewById(R.id.room_value);
        roomTextView.setText(thisLessonRoom);
        final TextView homeworkTextView = (TextView) findViewById(R.id.homework_value);
        homeworkTextView.setText(thisLessonHomework);
    }

    private void teacherLogin()
    {
        if (teacherLogin.equals("true"))
        {
            homeworkButton.setVisibility(View.GONE);
        }
    }

    public void homeworkTextClick(View view) {
        Toast.makeText(this, "passt", Toast.LENGTH_SHORT).show();
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


    public void homeworkTextClick2(View view) {
        Toast.makeText(this, "passt", Toast.LENGTH_SHORT).show();
    }
}
