package com.example.jalt.se15_client;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.LessonTO;


/**
 *
 * @author Mußenbrock
 */

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Es muss ein Datum empfangbar sein. Wenn dieses NULL ist wird das aktuelle Datum verwendet
        Date today = new Date();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String savedUser = sharedPreferences.getString("USER", "");


        Intent settingsIntent = getIntent();
        if(settingsIntent.getExtras().getString("origin").equals("settings"))
        {
            Toast.makeText(this, R.string.welcome + " " + savedUser, Toast.LENGTH_SHORT).show();
        }


        // AB HIER NUR PORTRAIT LOGIK
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            // Abfrage: Welche Fächer gibt es am heutigen Tag?
            // Objekt mit Array mit Veranstaltungsobjekten wird zurückgeliefert
            //List<ILesson> lessionList = new ArrayList<ILesson>();
            // lessonList = IStudeasyScheduleService.getLessonsByDate(1, today);
            List<LessonTO> lessonList = TestLessons.getLessons();

            // Hier wird das heutige Datum für die Anzeige im Kopf der Tabelle aufbereitet.
            DateFormat dfmt = new SimpleDateFormat("dd.MM.yy");
            final TextView textViewToChange = (TextView) findViewById(R.id.daytoday);
            textViewToChange.setText(dfmt.format(today).toString());

            final TableLayout color1 = (TableLayout) findViewById(R.id.dayclass1);
            final TextView teacher1 = (TextView) findViewById(R.id.dayclass1teacher);
            final TextView subject1 = (TextView) findViewById(R.id.dayclass1subject);
            final TextView room1 = (TextView) findViewById(R.id.dayclass1room);
            final TableLayout color2 = (TableLayout) findViewById(R.id.dayclass2);
            final TextView teacher2 = (TextView) findViewById(R.id.dayclass2teacher);
            final TextView subject2 = (TextView) findViewById(R.id.dayclass2subject);
            final TextView room2 = (TextView) findViewById(R.id.dayclass2room);
            final TableLayout color3 = (TableLayout) findViewById(R.id.dayclass3);
            final TextView teacher3 = (TextView) findViewById(R.id.dayclass3teacher);
            final TextView subject3 = (TextView) findViewById(R.id.dayclass3subject);
            final TextView room3 = (TextView) findViewById(R.id.dayclass3room);
            final TableLayout color4 = (TableLayout) findViewById(R.id.dayclass4);
            final TextView teacher4 = (TextView) findViewById(R.id.dayclass4teacher);
            final TextView subject4 = (TextView) findViewById(R.id.dayclass4subject);
            final TextView room4 = (TextView) findViewById(R.id.dayclass4room);
            final TableLayout color5 = (TableLayout) findViewById(R.id.dayclass5);
            final TextView teacher5 = (TextView) findViewById(R.id.dayclass5teacher);
            final TextView subject5 = (TextView) findViewById(R.id.dayclass5subject);
            final TextView room5 = (TextView) findViewById(R.id.dayclass5room);
            final TableLayout color6 = (TableLayout) findViewById(R.id.dayclass6);
            final TextView teacher6 = (TextView) findViewById(R.id.dayclass6teacher);
            final TextView subject6 = (TextView) findViewById(R.id.dayclass6subject);
            final TextView room6 = (TextView) findViewById(R.id.dayclass6room);

            for (LessonTO lesson : lessonList) {
                if (lesson.getLessonHour() == 1) {
                    color1.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    teacher1.setText(GenderChooser.getTitleByGender(lesson.getTeacher().getGender()) + " " + lesson.getTeacher().getName());
                    subject1.setText(lesson.getSubject().getDescription());
                    room1.setText(lesson.getRoom().getRoomID());
                } else if (lesson.getLessonHour() == 2) {
                    color2.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    teacher2.setText(GenderChooser.getTitleByGender(lesson.getTeacher().getGender()) + " " + lesson.getTeacher().getName());
                    subject2.setText(lesson.getSubject().getDescription());
                    room2.setText(lesson.getRoom().getRoomID());
                } else if (lesson.getLessonHour() == 3) {
                    color3.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    teacher3.setText(GenderChooser.getTitleByGender(lesson.getTeacher().getGender()) + " " + lesson.getTeacher().getName());
                    subject3.setText(lesson.getSubject().getDescription());
                    room3.setText(lesson.getRoom().getRoomID());
                } else if (lesson.getLessonHour() == 4) {
                    color4.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    teacher4.setText(GenderChooser.getTitleByGender(lesson.getTeacher().getGender()) + " " + lesson.getTeacher().getName());
                    subject4.setText(lesson.getSubject().getDescription());
                    room4.setText(lesson.getRoom().getRoomID());
                } else if (lesson.getLessonHour() == 5) {
                    color5.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    teacher5.setText(GenderChooser.getTitleByGender(lesson.getTeacher().getGender()) + " " + lesson.getTeacher().getName());
                    subject5.setText(lesson.getSubject().getDescription());
                    room5.setText(lesson.getRoom().getRoomID());
                } else if (lesson.getLessonHour() == 6) {
                    color6.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    teacher6.setText(GenderChooser.getTitleByGender(lesson.getTeacher().getGender()) + " " + lesson.getTeacher().getName());
                    subject6.setText(lesson.getSubject().getDescription());
                    room6.setText(lesson.getRoom().getRoomID());
                }
            }
        }

        // AB HIER NUR LANDSCAPE LOGIK
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            //Montag
            List<LessonTO> lessonList = TestLessons.getLessons();
            final TextView mo1 = (TextView) findViewById(R.id.mo1);
            final TextView mo2 = (TextView) findViewById(R.id.mo2);
            final TextView mo3 = (TextView) findViewById(R.id.mo3);
            final TextView mo4 = (TextView) findViewById(R.id.mo4);
            final TextView mo5 = (TextView) findViewById(R.id.mo5);
            final TextView mo6 = (TextView) findViewById(R.id.mo6);

            for (LessonTO lesson : lessonList) {
                if (lesson.getLessonHour() == 1) {
                    mo1.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    mo1.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 2) {
                    mo2.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    mo2.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 3) {
                    mo3.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    mo3.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 4) {
                    mo4.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    mo4.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 5) {
                    mo5.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    mo5.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 6) {
                    mo6.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    mo6.setText(lesson.getSubject().getDescription());
                }
            }

            //Dienstag
            final TextView tu1 = (TextView) findViewById(R.id.tu1);
            final TextView tu2 = (TextView) findViewById(R.id.tu2);
            final TextView tu3 = (TextView) findViewById(R.id.tu3);
            final TextView tu4 = (TextView) findViewById(R.id.tu4);
            final TextView tu5 = (TextView) findViewById(R.id.tu5);
            final TextView tu6 = (TextView) findViewById(R.id.tu6);

            for (LessonTO lesson : lessonList) {
                if (lesson.getLessonHour() == 1) {
                    tu1.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    tu1.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 2) {
                    tu2.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    tu2.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 3) {
                    tu3.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    tu3.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 4) {
                    tu4.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    tu4.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 5) {
                    tu5.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    tu5.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 6) {
                    tu6.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    tu6.setText(lesson.getSubject().getDescription());
                }
            }
            
            //Mittwoch
            final TextView we1 = (TextView) findViewById(R.id.we1);
            final TextView we2 = (TextView) findViewById(R.id.we2);
            final TextView we3 = (TextView) findViewById(R.id.we3);
            final TextView we4 = (TextView) findViewById(R.id.we4);
            final TextView we5 = (TextView) findViewById(R.id.we5);
            final TextView we6 = (TextView) findViewById(R.id.we6);
            //Donnerstag
            final TextView th1 = (TextView) findViewById(R.id.th1);
            final TextView th2 = (TextView) findViewById(R.id.th2);
            final TextView th3 = (TextView) findViewById(R.id.th3);
            final TextView th4 = (TextView) findViewById(R.id.th4);
            final TextView th5 = (TextView) findViewById(R.id.th5);
            final TextView th6 = (TextView) findViewById(R.id.th6);
            //Freitag
            final TextView fr1 = (TextView) findViewById(R.id.fr1);
            final TextView fr2 = (TextView) findViewById(R.id.fr2);
            final TextView fr3 = (TextView) findViewById(R.id.fr3);
            final TextView fr4 = (TextView) findViewById(R.id.fr4);
            final TextView fr5 = (TextView) findViewById(R.id.fr5);
            final TextView fr6 = (TextView) findViewById(R.id.fr6);






        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSettingsClick(MenuItem item) {

        Intent getSettingsIntent = new Intent(this, SettingsActivity.class);

        startActivity(getSettingsIntent);
    }

    public void onSubjectClick(View view) {

        Intent getSubjectIntent = new Intent(this, SubjectActivity.class);

        int SubjectId = view.getId();
        String SubjectName = getResources().getResourceEntryName(SubjectId);

        getSubjectIntent.putExtra("SubjectName", SubjectName);

        startActivity(getSubjectIntent);
    }
}
