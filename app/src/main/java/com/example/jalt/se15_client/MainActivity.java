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
        thisLesson.setLessonHour(3);
        Room room = new Room();
        room.setRoomID("D422");
        thisLesson.setRoom(room);
        // Date für diese Ansicht nicht wichtig
        thisLesson.setCourse(new Course());


        List<Lesson> lessonList = new ArrayList<Lesson>();
        lessonList.clear();
        lessonList.add(thisLesson);


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
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

        for (Lesson lesson : lessonList) {
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
