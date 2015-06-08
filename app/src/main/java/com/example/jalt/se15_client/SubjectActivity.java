package com.example.jalt.se15_client;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableRow;
import android.widget.TextView;

import common.ILesson;
import common.ISubject;
import common.ITeacher;

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
        // ILesson thisLesson = findLessonById(subjectId);
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
}
