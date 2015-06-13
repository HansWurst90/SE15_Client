package com.example.jalt.se15_client;
//test
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseArray;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import common.LessonTO;


/**
 *
 * @author Mußenbrock
 */

public class MainActivity extends ActionBarActivity {

    SharedPreferences sharedPreferences;
    Calendar dateTo;
    Calendar date;
    long dateInMillis;
    boolean login;
    private Toast dToast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userTrue = sharedPreferences.getString("USER", "");
        if (userTrue.equals(""))
            login = true;
        else
            login = false;

        dateTo = Calendar.getInstance();
        date = Calendar.getInstance();

        if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            date.add(Calendar.DATE, 2);
        }
        if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            date.add(Calendar.DATE, 1);
        }

        Intent intent = getIntent();
        if (intent.getExtras() != null)
        {
            if (intent.getExtras().getString("origin").equals("main_portrait"))
            {
                dateInMillis = intent.getExtras().getLong("dateInMillis");
                date.setTimeInMillis(dateInMillis);
            }
            if (intent.getExtras().getString("origin").equals("main_landscape"))
            {
                dateInMillis = intent.getExtras().getLong("dateInMillis");
                date.setTimeInMillis(dateInMillis);
            }
            if(intent.getExtras().getString("origin").equals("login"))
            {
                String savedUser = sharedPreferences.getString("USER", "");
                String welcome = getResources().getString(R.string.welcome);
                Toast.makeText(this, welcome + " " + savedUser, Toast.LENGTH_SHORT).show();

            }
            else if(intent.getExtras().getString("origin").equals("logout"))
            {
                String goodbye = getResources().getString(R.string.goodbye);
                Toast.makeText(this, goodbye, Toast.LENGTH_SHORT).show();

            }
        }


        // AB HIER NUR PORTRAIT LOGIK
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            // Abfrage: Welche Fächer gibt es am heutigen Tag?
            // Objekt mit Array mit Veranstaltungsobjekten wird zurückgeliefert
            //List<ILesson> lessionList = new ArrayList<ILesson>();
            // lessonList = IStudeasyScheduleService.getLessonsByDate(1, today);
            List<LessonTO> lessonList = TestLessons.getLessons();

            // Hier wird das heutige Datum für die Anzeige im Kopf der Tabelle aufbereitet.
            DateFormat dfmt = new SimpleDateFormat("E dd.MM.yy", Locale.GERMAN);
            final TextView dateText = (TextView) findViewById(R.id.daytoday);
            dateText.setText(dfmt.format(date.getTime()).toString());
            if (date.get(Calendar.DAY_OF_YEAR) == dateTo.get(Calendar.DAY_OF_YEAR)){
                dateText.setBackgroundResource(R.color.Light_Blue);
            }
            SparseArray<LessonTO> lessonMap = new SparseArray<>();
            SparseArray<TableLayout> cellMap = new SparseArray<>();
            SparseArray<TextView> teacherMap = new SparseArray<>();
            SparseArray<TextView> subjectMap = new SparseArray<>();
            SparseArray<TextView> roomMap = new SparseArray<>();

            cellMap.put(1, (TableLayout) findViewById(R.id.dayclass1));
            teacherMap.put(1, (TextView) findViewById(R.id.dayclass1teacher));
            subjectMap.put(1, (TextView) findViewById(R.id.dayclass1subject));
            roomMap.put(1, (TextView) findViewById(R.id.dayclass1room));

            cellMap.put(2, (TableLayout) findViewById(R.id.dayclass2));
            teacherMap.put(2, (TextView) findViewById(R.id.dayclass2teacher));
            subjectMap.put(2, (TextView) findViewById(R.id.dayclass2subject));
            roomMap.put(2, (TextView) findViewById(R.id.dayclass2room));

            cellMap.put(3, (TableLayout) findViewById(R.id.dayclass3));
            teacherMap.put(3, (TextView) findViewById(R.id.dayclass3teacher));
            subjectMap.put(3, (TextView) findViewById(R.id.dayclass3subject));
            roomMap.put(3, (TextView) findViewById(R.id.dayclass3room));

            cellMap.put(4, (TableLayout) findViewById(R.id.dayclass4));
            teacherMap.put(4, (TextView) findViewById(R.id.dayclass4teacher));
            subjectMap.put(4, (TextView) findViewById(R.id.dayclass4subject));
            roomMap.put(4, (TextView) findViewById(R.id.dayclass4room));

            cellMap.put(5, (TableLayout) findViewById(R.id.dayclass5));
            teacherMap.put(5, (TextView) findViewById(R.id.dayclass5teacher));
            subjectMap.put(5, (TextView) findViewById(R.id.dayclass5subject));
            roomMap.put(5, (TextView) findViewById(R.id.dayclass5room));

            cellMap.put(6, (TableLayout) findViewById(R.id.dayclass6));
            teacherMap.put(6, (TextView) findViewById(R.id.dayclass6teacher));
            subjectMap.put(6, (TextView) findViewById(R.id.dayclass6subject));
            roomMap.put(6, (TextView) findViewById(R.id.dayclass6room));

            // LessonMap befüllen und OnClick setzten. Kann nicht mit in die Schleife da in Methodenaufruf Int Final sein muss.
            for (LessonTO lesson : lessonList) {
                if (lesson.getLessonHour() == 1) {
                    lessonMap.put(1, lesson);
                    cellMap.get(1).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        onSubjectClick(v, 1);
                    }});
                } else if (lesson.getLessonHour() == 2) {
                    lessonMap.put(2, lesson);
                    cellMap.get(2).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            onSubjectClick(v, 2);
                        }});
                } else if (lesson.getLessonHour() == 3) {
                    lessonMap.put(3, lesson);
                    cellMap.get(3).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            onSubjectClick(v, 3);
                        }});
                } else if (lesson.getLessonHour() == 4) {
                    lessonMap.put(4, lesson);
                    cellMap.get(4).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            onSubjectClick(v, 4);
                        }});
                } else if (lesson.getLessonHour() == 5) {
                    lessonMap.put(5, lesson);
                    cellMap.get(5).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            onSubjectClick(v, 5);
                        }});
                } else if (lesson.getLessonHour() == 6) {
                    lessonMap.put(6, lesson);
                    cellMap.get(6).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            onSubjectClick(v, 6);
                        }});
                }
                for(int i=1; i<7; i++){
                    if (lessonMap.get(i) != null) {
                        cellMap.get(i).setBackgroundResource(ColorChooser.getColorFromId(lessonMap.get(i).getSubject().getSubjectID()));
                        teacherMap.get(i).setText(GenderChooser.getTitleByGender(lessonMap.get(i).getTeacher().getGender()) + " " + lessonMap.get(i).getTeacher().getName());
                        subjectMap.get(i).setText(lessonMap.get(i).getSubject().getDescription());
                        roomMap.get(i).setText(lessonMap.get(i).getRoom().getRoomID());
                    }
                }
            }
        }

        // AB HIER NUR LANDSCAPE LOGIK
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            Calendar dateMo = (Calendar) date.clone();
            dateMo.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            Calendar dateTu = (Calendar) date.clone();
            dateTu.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
            Calendar dateWe = (Calendar) date.clone();
            dateWe.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
            Calendar dateTh = (Calendar) date.clone();
            dateTh.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
            Calendar dateFr = (Calendar) date.clone();
            dateFr.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);

            DateFormat dfmt = new SimpleDateFormat("E dd.MM.yy", Locale.GERMAN);
            dToast = Toast.makeText(this, dfmt.format(dateMo.getTime()).toString() + " - " + dfmt.format(dateFr.getTime()).toString(), Toast.LENGTH_SHORT);
            dToast.show();

            List<LessonTO> lessonListMo = TestLessons.getLessons();
            List<LessonTO> lessonListTu = TestLessons.getLessons();
            List<LessonTO> lessonListWe = TestLessons.getLessons();
            List<LessonTO> lessonListTh = TestLessons.getLessons();
            List<LessonTO> lessonListFr = TestLessons.getLessons();

            //Montag
            final TextView mo0 = (TextView) findViewById(R.id.mo0);
            final TextView mo1 = (TextView) findViewById(R.id.mo1);
            final TextView mo2 = (TextView) findViewById(R.id.mo2);
            final TextView mo3 = (TextView) findViewById(R.id.mo3);
            final TextView mo4 = (TextView) findViewById(R.id.mo4);
            final TextView mo5 = (TextView) findViewById(R.id.mo5);
            final TextView mo6 = (TextView) findViewById(R.id.mo6);

            if (dateMo.get(Calendar.DAY_OF_YEAR) == dateTo.get(Calendar.DAY_OF_YEAR)){
                mo0.setBackgroundResource(R.color.Light_Blue);
            }

            for (LessonTO lesson : lessonListMo) {
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

            final TextView tu0 = (TextView) findViewById(R.id.tu0);
            final TextView tu1 = (TextView) findViewById(R.id.tu1);
            final TextView tu2 = (TextView) findViewById(R.id.tu2);
            final TextView tu3 = (TextView) findViewById(R.id.tu3);
            final TextView tu4 = (TextView) findViewById(R.id.tu4);
            final TextView tu5 = (TextView) findViewById(R.id.tu5);
            final TextView tu6 = (TextView) findViewById(R.id.tu6);

            if (dateTu.get(Calendar.DAY_OF_YEAR) == dateTo.get(Calendar.DAY_OF_YEAR)){
                tu0.setBackgroundResource(R.color.Light_Blue);
            }

            for (LessonTO lesson : lessonListTu) {
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
            final TextView we0 = (TextView) findViewById(R.id.we0);
            final TextView we1 = (TextView) findViewById(R.id.we1);
            final TextView we2 = (TextView) findViewById(R.id.we2);
            final TextView we3 = (TextView) findViewById(R.id.we3);
            final TextView we4 = (TextView) findViewById(R.id.we4);
            final TextView we5 = (TextView) findViewById(R.id.we5);
            final TextView we6 = (TextView) findViewById(R.id.we6);

            if (dateWe.get(Calendar.DAY_OF_YEAR) == dateTo.get(Calendar.DAY_OF_YEAR)){
                we0.setBackgroundResource(R.color.Light_Blue);
            }

            for (LessonTO lesson : lessonListWe) {
                if (lesson.getLessonHour() == 1) {
                    we1.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    we1.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 2) {
                    we2.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    we2.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 3) {
                    we3.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    we3.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 4) {
                    we4.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    we4.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 5) {
                    we5.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    we5.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 6) {
                    we6.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                }
            }

            //Donnerstag
            final TextView th0 = (TextView) findViewById(R.id.th0);
            final TextView th1 = (TextView) findViewById(R.id.th1);
            final TextView th2 = (TextView) findViewById(R.id.th2);
            final TextView th3 = (TextView) findViewById(R.id.th3);
            final TextView th4 = (TextView) findViewById(R.id.th4);
            final TextView th5 = (TextView) findViewById(R.id.th5);
            final TextView th6 = (TextView) findViewById(R.id.th6);

            if (dateTh.get(Calendar.DAY_OF_YEAR) == dateTo.get(Calendar.DAY_OF_YEAR)){
                th0.setBackgroundResource(R.color.Light_Blue);
            }

            for (LessonTO lesson : lessonListTh) {
                if (lesson.getLessonHour() == 1) {
                    th1.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    th1.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 2) {
                    th2.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    th2.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 3) {
                    th3.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    th3.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 4) {
                    th4.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    th4.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 5) {
                    th5.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    th5.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 6) {
                    th6.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    th6.setText(lesson.getSubject().getDescription());
                }
            }

            //Freitag
            final TextView fr0 = (TextView) findViewById(R.id.fr0);
            final TextView fr1 = (TextView) findViewById(R.id.fr1);
            final TextView fr2 = (TextView) findViewById(R.id.fr2);
            final TextView fr3 = (TextView) findViewById(R.id.fr3);
            final TextView fr4 = (TextView) findViewById(R.id.fr4);
            final TextView fr5 = (TextView) findViewById(R.id.fr5);
            final TextView fr6 = (TextView) findViewById(R.id.fr6);

            if (dateFr.get(Calendar.DAY_OF_YEAR) == dateTo.get(Calendar.DAY_OF_YEAR)){
                fr0.setBackgroundResource(R.color.Light_Blue);
            }

            for (LessonTO lesson : lessonListFr) {
                if (lesson.getLessonHour() == 1) {
                    fr1.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    fr1.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 2) {
                    fr2.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    fr2.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 3) {
                    fr3.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    fr3.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 4) {
                    fr4.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    fr4.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 5) {
                    fr5.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    fr5.setText(lesson.getSubject().getDescription());
                } else if (lesson.getLessonHour() == 6) {
                    fr6.setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    fr6.setText(lesson.getSubject().getDescription());
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_settings);
        String loginText = getResources().getString(R.string.login);
        String logoutText = getResources().getString(R.string.logout);
        if(login)
            item.setTitle(loginText);
        else
            item.setTitle(logoutText);
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

    public void onSubjectClick(View view, int lessonId) {

        Intent getSubjectIntent = new Intent(this, SubjectActivity.class);

        getSubjectIntent.putExtra("lessonId", lessonId);

        startActivity(getSubjectIntent);
    }


    public void onNextClickP(View view){
        Intent getNextIntent = new Intent(this, MainActivity.class);
        date.add(Calendar.DATE, 1);
        if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            date.add(Calendar.DATE, 2);
        }
        if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            date.add(Calendar.DATE, 1);
        }
        getNextIntent.putExtra("dateInMillis", date.getTimeInMillis());
        getNextIntent.putExtra("origin","main_portrait");

        startActivity(getNextIntent);
    }
    public void onPreviousClickP(View view){
        Intent getPreviousIntent = new Intent(this, MainActivity.class);
        date.add(Calendar.DATE, -1);
        if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            date.add(Calendar.DATE, -2);
        }
        if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            date.add(Calendar.DATE, -1);
        }
        getPreviousIntent.putExtra("dateInMillis", date.getTimeInMillis());
        getPreviousIntent.putExtra("origin","main_portrait");

        startActivity(getPreviousIntent);
    }
    public void onNextClickL(View view){
        Intent getNextIntent = new Intent(this, MainActivity.class);
        date.add(Calendar.DATE, 7);
        getNextIntent.putExtra("dateInMillis", date.getTimeInMillis());
        getNextIntent.putExtra("origin","main_landscape");

        startActivity(getNextIntent);
    }
    public void onPreviousClickL(View view){
        Intent getPreviousIntent = new Intent(this, MainActivity.class);
        date.add(Calendar.DATE, -7);
        getPreviousIntent.putExtra("dateInMillis", date.getTimeInMillis());
        getPreviousIntent.putExtra("origin","main_landscape");

        startActivity(getPreviousIntent);
    }
}
