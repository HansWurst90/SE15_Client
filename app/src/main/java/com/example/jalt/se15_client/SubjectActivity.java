package com.example.jalt.se15_client;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import studeasy.common.ILesson;
import studeasy.entities.Course;
import studeasy.entities.Lesson;
import studeasy.entities.Room;
import studeasy.entities.Subject;
import studeasy.entities.Teacher;

/**
 *
 * @author Mußenbrock
 */

public class SubjectActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        // Intent whichSubjectId = getIntent();
        // int subjectId = whichSubjectId.getExtras().getInt("subjectId");


        ILesson thisLesson = new Lesson();
        // Date für diese Ansicht nicht wichtig
        thisLesson.setDate(new Date());
            Subject subject = new Subject();
            subject.setDescription("English");
            subject.setSubjectID(2);
        thisLesson.setSubject(subject);
            Teacher teacher = new Teacher();
            teacher.setName("Mußenbrock");
            teacher.setGender('m');
        thisLesson.setTeacher(teacher);
        thisLesson.setLessonHour(1);
            Room room = new Room();
            room.setRoomID("D422");
        thisLesson.setRoom(new Room());
        // Date für diese Ansicht nicht wichtig
        thisLesson.setCourse(new Course());

        String thisLessonDescription = thisLesson.getSubject().getDescription();
        String thisLessonTeacherName = thisLesson.getTeacher().getName();
        char thisLessonTeacherGender = thisLesson.getTeacher().getGender();
        int thisLessonHour = thisLesson.getLessonHour();
        int thisLessonColor = ColorChooser.getColorFromId(thisLesson.getSubject().getSubjectID());
        // thisLesson.getHomeworks();



        //Abhängig des Geschlechts wird die Anrede gesetzt
        String thisLessonTeacherTitle = GenderChooser.getTitleByGender(thisLessonTeacherGender);

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

    @Override
    public void onPause() {
        final CheckBox homeworkButton = (CheckBox) findViewById(R.id.homework_checkbutton);
        super.onPause();
        save(homeworkButton.isChecked());
    }

    @Override
    public void onResume() {
        final CheckBox homeworkButton = (CheckBox) findViewById(R.id.homework_checkbutton);
        super.onResume();
        homeworkButton.setChecked(load());
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


}
