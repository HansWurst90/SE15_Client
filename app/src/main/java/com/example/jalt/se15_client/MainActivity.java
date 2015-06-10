package com.example.jalt.se15_client;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Es muss ein Datum empfangbar sein. Wenn dieses NULL ist wird das aktuelle Datum verwendet
        Date today = new Date();


        // Abfrage: Welche Fächer gibt es am heutigen Tag?
        // Objekt mit Array mit Veranstaltungsobjekten wird zurückgeliefert
        //List<ILesson> lessionList = new ArrayList<ILesson>();
        // lessonList = IStudeasyScheduleService.getLessonsByDate(1, today);

                            Lesson thisLesson = new Lesson();
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


                            List<Lesson> lessionList = new ArrayList<Lesson>();
                            lessionList.add(thisLesson);

        final TableLayout color1 = (TableLayout) findViewById(R.id.dayclass1);
        final TextView teacher1 = (TextView) findViewById(R.id.dayclass1teacher);
        final TextView subject1 = (TextView) findViewById(R.id.dayclass1subject);
        final TextView room1 = (TextView) findViewById(R.id.dayclass1room);

        for (Lesson lesson: lessionList)
                if (lesson.getLessonHour() == 1)
                {
                    color1.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    subject1.setText(lesson.getSubject().getDescription());
                }






        // Hier wird das heutige Datum für die Anzeige im Kopf der Tabelle aufbereitet.
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            DateFormat dfmt = new SimpleDateFormat("dd.MM.yy");
            final TextView textViewToChange = (TextView) findViewById(R.id.daytoday);
            textViewToChange.setText(dfmt.format(today).toString());
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
