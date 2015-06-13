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
    SparseArray<LessonTO> lessonMap;

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
            lessonMap = new SparseArray<>();
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

            lessonMap = new SparseArray<>();
            SparseArray<TextView> cellMap = new SparseArray();

            cellMap.put(10, (TextView) findViewById(R.id.mo0));
            cellMap.put(11, (TextView) findViewById(R.id.mo1));
            cellMap.put(12, (TextView) findViewById(R.id.mo2));
            cellMap.put(13, (TextView) findViewById(R.id.mo3));
            cellMap.put(14, (TextView) findViewById(R.id.mo4));
            cellMap.put(15, (TextView) findViewById(R.id.mo5));
            cellMap.put(16, (TextView) findViewById(R.id.mo6));

            cellMap.put(20, (TextView) findViewById(R.id.tu0));
            cellMap.put(21, (TextView) findViewById(R.id.tu1));
            cellMap.put(22, (TextView) findViewById(R.id.tu2));
            cellMap.put(23, (TextView) findViewById(R.id.tu3));
            cellMap.put(24, (TextView) findViewById(R.id.tu4));
            cellMap.put(25, (TextView) findViewById(R.id.tu5));
            cellMap.put(26, (TextView) findViewById(R.id.tu6));

            cellMap.put(30, (TextView) findViewById(R.id.we0));
            cellMap.put(31, (TextView) findViewById(R.id.we1));
            cellMap.put(32, (TextView) findViewById(R.id.we2));
            cellMap.put(33, (TextView) findViewById(R.id.we3));
            cellMap.put(34, (TextView) findViewById(R.id.we4));
            cellMap.put(35, (TextView) findViewById(R.id.we5));
            cellMap.put(36, (TextView) findViewById(R.id.we6));

            cellMap.put(40, (TextView) findViewById(R.id.th0));
            cellMap.put(41, (TextView) findViewById(R.id.th1));
            cellMap.put(42, (TextView) findViewById(R.id.th2));
            cellMap.put(43, (TextView) findViewById(R.id.th3));
            cellMap.put(44, (TextView) findViewById(R.id.th4));
            cellMap.put(45, (TextView) findViewById(R.id.th5));
            cellMap.put(46, (TextView) findViewById(R.id.th6));

            cellMap.put(50, (TextView) findViewById(R.id.fr0));
            cellMap.put(51, (TextView) findViewById(R.id.fr1));
            cellMap.put(52, (TextView) findViewById(R.id.fr2));
            cellMap.put(53, (TextView) findViewById(R.id.fr3));
            cellMap.put(54, (TextView) findViewById(R.id.fr4));
            cellMap.put(55, (TextView) findViewById(R.id.fr5));
            cellMap.put(56, (TextView) findViewById(R.id.fr6));

            //Montag
            for (LessonTO lesson : lessonListMo) {
                if (lesson.getLessonHour() == 1) {
                    lessonMap.put(11, lesson);
                } else if (lesson.getLessonHour() == 2) {
                    lessonMap.put(12, lesson);
                } else if (lesson.getLessonHour() == 3) {
                    lessonMap.put(13, lesson);
                } else if (lesson.getLessonHour() == 4) {
                    lessonMap.put(14, lesson);
                } else if (lesson.getLessonHour() == 5) {
                    lessonMap.put(15, lesson);
                } else if (lesson.getLessonHour() == 6) {
                    lessonMap.put(16, lesson);
                }
            }

            if (dateMo.get(Calendar.DAY_OF_YEAR) == dateTo.get(Calendar.DAY_OF_YEAR)) {
                cellMap.get(10).setBackgroundResource(R.color.Light_Blue);
            }

            for (LessonTO lesson : lessonListMo) {
                if (lesson.getLessonHour() == 1) {
                    cellMap.get(11).setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    cellMap.get(11).setText(lesson.getSubject().getDescription());
                    cellMap.get(11).setOnClickListener(new View.OnClickListener() {public void onClick(View v) { onSubjectClickL(v, 11);}});
                } else if (lesson.getLessonHour() == 2) {
                    cellMap.get(12).setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    cellMap.get(12).setText(lesson.getSubject().getDescription());
                    cellMap.get(12).setOnClickListener(new View.OnClickListener() {public void onClick(View v) { onSubjectClickL(v, 12);}});
                } else if (lesson.getLessonHour() == 3) {
                    cellMap.get(13).setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    cellMap.get(13).setText(lesson.getSubject().getDescription());
                    cellMap.get(13).setOnClickListener(new View.OnClickListener() {public void onClick(View v) { onSubjectClickL(v, 13);}});
                } else if (lesson.getLessonHour() == 4) {
                    cellMap.get(14).setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    cellMap.get(14).setText(lesson.getSubject().getDescription());
                    cellMap.get(14).setOnClickListener(new View.OnClickListener() {public void onClick(View v) { onSubjectClickL(v, 14);}});
                } else if (lesson.getLessonHour() == 5) {
                    cellMap.get(15).setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    cellMap.get(15).setText(lesson.getSubject().getDescription());
                    cellMap.get(15).setOnClickListener(new View.OnClickListener() {public void onClick(View v) { onSubjectClickL(v, 15);}});
                } else if (lesson.getLessonHour() == 6) {
                    cellMap.get(16).setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    cellMap.get(16).setText(lesson.getSubject().getDescription());
                    cellMap.get(16).setOnClickListener(new View.OnClickListener() {public void onClick(View v) { onSubjectClickL(v, 16);}});
                }
            }

            //Dienstag
            for (LessonTO lesson : lessonListMo) {
                if (lesson.getLessonHour() == 1) {
                    lessonMap.put(21, lesson);
                } else if (lesson.getLessonHour() == 2) {
                    lessonMap.put(22, lesson);
                } else if (lesson.getLessonHour() == 3) {
                    lessonMap.put(23, lesson);
                } else if (lesson.getLessonHour() == 4) {
                    lessonMap.put(24, lesson);
                } else if (lesson.getLessonHour() == 5) {
                    lessonMap.put(25, lesson);
                } else if (lesson.getLessonHour() == 6) {
                    lessonMap.put(26, lesson);
                }
            }

            if (dateMo.get(Calendar.DAY_OF_YEAR) == dateTo.get(Calendar.DAY_OF_YEAR)) {
                cellMap.get(20).setBackgroundResource(R.color.Light_Blue);
            }

            for (LessonTO lesson : lessonListMo) {
                if (lesson.getLessonHour() == 1) {
                    cellMap.get(21).setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    cellMap.get(21).setText(lesson.getSubject().getDescription());
                    cellMap.get(21).setOnClickListener(new View.OnClickListener() {public void onClick(View v) { onSubjectClickL(v, 21);}});
                } else if (lesson.getLessonHour() == 2) {
                    cellMap.get(22).setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    cellMap.get(22).setText(lesson.getSubject().getDescription());
                    cellMap.get(22).setOnClickListener(new View.OnClickListener() {public void onClick(View v) { onSubjectClickL(v, 22);}});
                } else if (lesson.getLessonHour() == 3) {
                    cellMap.get(23).setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    cellMap.get(23).setText(lesson.getSubject().getDescription());
                    cellMap.get(23).setOnClickListener(new View.OnClickListener() {public void onClick(View v) { onSubjectClickL(v, 23);}});
                } else if (lesson.getLessonHour() == 4) {
                    cellMap.get(24).setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    cellMap.get(24).setText(lesson.getSubject().getDescription());
                    cellMap.get(24).setOnClickListener(new View.OnClickListener() {public void onClick(View v) { onSubjectClickL(v, 24);}});
                } else if (lesson.getLessonHour() == 5) {
                    cellMap.get(25).setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    cellMap.get(25).setText(lesson.getSubject().getDescription());
                    cellMap.get(25).setOnClickListener(new View.OnClickListener() {public void onClick(View v) { onSubjectClickL(v, 25);}});
                } else if (lesson.getLessonHour() == 6) {
                    cellMap.get(26).setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                    cellMap.get(26).setText(lesson.getSubject().getDescription());
                    cellMap.get(26).setOnClickListener(new View.OnClickListener() {public void onClick(View v) { onSubjectClickL(v, 26);}});
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
        lessonMap.get(lessonId).getLessonID();
        startActivity(getSubjectIntent);
    }

    public void onSubjectClickL(View view, int lessonId) {

        Intent getSubjectIntent = new Intent(this, SubjectActivity.class);

        lessonId = lessonMap.get(lessonId).getLessonID();
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
