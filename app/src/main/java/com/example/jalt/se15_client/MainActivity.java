package com.example.jalt.se15_client;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jalt.se15_client.tasks.IsTeacherTask;
import com.example.jalt.se15_client.tasks.LessonByDateTask;
import com.example.jalt.se15_client.tasks.LessonTask;
import com.example.jalt.se15_client.tasks.LoginTask;
import com.example.jalt.se15_client.tasks.LogoutTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import common.IStudeasyScheduleService;
import common.LessonResponse;
import common.LessonTO;

/**
 *
 * @author Jan Mußenbrock und Lukas Erfkämper
 */
public class MainActivity extends ActionBarActivity {

    SharedPreferences sharedPreferences;
    Calendar dateTo;
    Calendar date;
    long dateInMillis;
    SparseArray<LessonTO> lessonMap;
    SparseArray<TableLayout> cellMap;
    SparseArray<TextView> teacherMap;
    SparseArray<TextView> roomMap;
    SparseArray<TextView> subjectMap;
    SparseArray<TextView> textMap;
    SparseArray<String> dateMap;

    int sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        StudeasyScheduleApplication myApp = (StudeasyScheduleApplication) getApplication();

        // LessonByDateTask lessonByDate = new LessonByDateTask(this, myApp); <--------------------- LÖSCHEN
        // lessonByDate.execute(52,"22062015", 4);

        // Logik zum Überspringen des Wochenendes für das HEUTIGE DATUM
        dateTo = Calendar.getInstance();
        date = Calendar.getInstance();
        if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            date.add(Calendar.DATE, 2);
        }
        if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            date.add(Calendar.DATE, 1);
        }

        // Abfrage zu Herkunfts-Activities
        Intent intent = getIntent();
        if (intent.getExtras() != null)
        {
            // Woher kommt der Activity-Aufruf? Davon hängt ab, welche extras mitgesandt wurden
            if (intent.getExtras().getString("origin") != null) {
                // Wenn man von Main im Hochformat kommt kann man ein neues Datum erwarten. (Vorheriger oder folgender Tag wie in der Herkunfts-Aktivity)
                if (intent.getExtras().getString("origin").equals("main_portrait")) {
                    dateInMillis = intent.getExtras().getLong("dateInMillis");
                    date.setTimeInMillis(dateInMillis);
                    // Übergabe der Animation abhängig von Next- oder Previous-Methode
                    if (intent.getExtras().getString("direction").equals("right")) {
                        this.overridePendingTransition(R.anim.slide_in_right, 17432577);
                    }
                    // Übergabe der Animation abhängig von Next- oder Previous-Methode
                    if (intent.getExtras().getString("direction").equals("left")) {
                        this.overridePendingTransition(R.anim.slide_in_left, 17432577);
                    }
                }
                // Wenn man von Main im Querformat kommt kann man ein neues Datum erwarten. (/ Tage vor oder nach dem Datum der Herkunfts-Aktivity)
                if (intent.getExtras().getString("origin").equals("main_landscape")) {
                    dateInMillis = intent.getExtras().getLong("dateInMillis");
                    date.setTimeInMillis(dateInMillis);
                    // Übergabe der Animation abhängig von Next- oder Previous-Methode
                    if (intent.getExtras().getString("direction").equals("right")) {
                        this.overridePendingTransition(R.anim.slide_in_right, 17432577);
                    }
                    // Übergabe der Animation abhängig von Next- oder Previous-Methode
                    if (intent.getExtras().getString("direction").equals("left")) {
                        this.overridePendingTransition(R.anim.slide_in_left, 17432577);
                    }
                }
            }
        }

        // AB HIER NUR PORTRAIT LOGIK
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            // Abfrage eines Tagesplan für den gegebenen Tag.
            // LessonListResponse lessonListResponse = IStudeasyScheduleService.getLessonsByDate(sessionId, date);
            // List<LessonTO> lessonList = lessonListResponse.getLessonList()
            List<LessonTO> lessonList = TestLessons.getLessons1();

            // Hier wird das Datum für die Anzeige im Kopf der Tabelle aufbereitet und blau gefärbt, wenn es das heutige Datum ist.
            DateFormat dfmt = new SimpleDateFormat("E dd.MM.yy", Locale.GERMAN);
            final TextView dateText = (TextView) findViewById(R.id.daytoday);
            dateText.setText(dfmt.format(date.getTime()).toString());
            if (date.get(Calendar.DAY_OF_YEAR) == dateTo.get(Calendar.DAY_OF_YEAR)){
                dateText.setBackgroundResource(R.color.Light_Blue);
            }

            // Map/Arrays zum einfachen Addressieren der Verschiedenen TextViews in unserem Tabellenlayout
            lessonMap = new SparseArray<>();
            cellMap = new SparseArray<>();
            teacherMap = new SparseArray<>();
            subjectMap = new SparseArray<>();
            roomMap = new SparseArray<>();

            // Zuweisen der Zellen und TextVIews
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

            // Lessons werden nach Unterichtsstunde Sortiert in die LessonMap gespeichert.
            // Zellen 1-6 werden entsprechend mit verlinkt um später die SessionId mitgeben zu können.
            // Wenn die erste Stunde angeklickt wurde bekommt die OnClick-Methode eine 1 mitgeliefert und kann sich so mit "lessonMap.get(1).getLessonId()" der folgenden Activity die Id mitliefern.
            // Hinweis: Die Id kann nicht variabel eingefügt werden, da in Methodenaufruf Int Final sein muss.
            // Hinweis: LessonMap befüllen und OnClick setzten. Kann nicht mit in die Schleife, da in Methodenaufruf Int Final sein muss.

            //Fächerfarben, Lehrernamen, Fachnamen und Raumnummer werden per Schleife anhand der lessonMap eingetragen.
            for (int i=1; i<7; i++) {
                final int j = i;
                new LessonTask(this, myApp) {
                    @Override
                    public void onPostExecute(LessonResponse result) {
                        if(result != null)
                        {
                        Log.i("LessonTask", "erfolgreich");
                        final LessonTO lesson = result.getLesson();
                        cellMap.get(j).setBackgroundResource(ColorChooser.getColorFromId(lesson.getSubject().getSubjectID()));
                        subjectMap.get(j).setText(lesson.getSubject().getDescription());
                        teacherMap.get(j).setText(GenderChooser.getTitleByGender(lesson.getTeacher().getGender()) + " " + lesson.getTeacher().getName());
                        roomMap.get(j).setText(lesson.getRoom());
                        cellMap.get(j).setOnClickListener(new View.OnClickListener(){public void onClick(View v) {onSubjectClick(v, lesson.getLessonID(), lesson.getTeacher().getName());}});
                        }
                        else
                        {
                            Log.i("LessonTask", "fehlgeschlagen");
                        }
                    }
                }.execute(5); // <-------- hier muss später der getLessonBydate()-task hin mit ttmmjjjj und hour als i
            }
        }

        // AB HIER NUR LANDSCAPE LOGIK
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Es werden alle Wochentage der Woche des genannten Datums ermittelt
            dateMap = new SparseArray<>();
            DateFormat ttmmjjjj = new SimpleDateFormat("ddMMyyyy", Locale.GERMAN);
            Calendar dateMo = (Calendar) date.clone();
            dateMo.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            dateMap.put(1, ttmmjjjj.format(dateMo.getTime()));
            Calendar dateTu = (Calendar) date.clone();
            dateTu.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
            dateMap.put(2, ttmmjjjj.format(dateTu.getTime()));
            Calendar dateWe = (Calendar) date.clone();
            dateWe.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
            dateMap.put(3, ttmmjjjj.format(dateWe.getTime()));
            Calendar dateTh = (Calendar) date.clone();
            dateTh.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
            dateMap.put(4, ttmmjjjj.format(dateTh.getTime()));
            Calendar dateFr = (Calendar) date.clone();
            dateFr.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            dateMap.put(5, ttmmjjjj.format(dateFr.getTime()));
            // Overlay Info zur Woche (Datum: Von - Bis)
            DateFormat dfmt = new SimpleDateFormat("E dd.MM.yy", Locale.GERMAN);
            Toast.makeText(this, dfmt.format(dateMo.getTime()) + " - " + dfmt.format(dateFr.getTime()), Toast.LENGTH_SHORT).show();


            // Map und Array zum einfachen Adressieren
            lessonMap = new SparseArray<>();
            textMap = new SparseArray();
            // Befüllen der Map mit allen Zellen: Einerstelle Tag (Mo = 1, Di = 2, ...), Zehnerstelle Stunde (1, 2, ...)
            textMap.put(10, (TextView) findViewById(R.id.mo0));
            textMap.put(11, (TextView) findViewById(R.id.mo1));
            textMap.put(12, (TextView) findViewById(R.id.mo2));
            textMap.put(13, (TextView) findViewById(R.id.mo3));
            textMap.put(14, (TextView) findViewById(R.id.mo4));
            textMap.put(15, (TextView) findViewById(R.id.mo5));
            textMap.put(16, (TextView) findViewById(R.id.mo6));

            textMap.put(20, (TextView) findViewById(R.id.tu0));
            textMap.put(21, (TextView) findViewById(R.id.tu1));
            textMap.put(22, (TextView) findViewById(R.id.tu2));
            textMap.put(23, (TextView) findViewById(R.id.tu3));
            textMap.put(24, (TextView) findViewById(R.id.tu4));
            textMap.put(25, (TextView) findViewById(R.id.tu5));
            textMap.put(26, (TextView) findViewById(R.id.tu6));

            textMap.put(30, (TextView) findViewById(R.id.we0));
            textMap.put(31, (TextView) findViewById(R.id.we1));
            textMap.put(32, (TextView) findViewById(R.id.we2));
            textMap.put(33, (TextView) findViewById(R.id.we3));
            textMap.put(34, (TextView) findViewById(R.id.we4));
            textMap.put(35, (TextView) findViewById(R.id.we5));
            textMap.put(36, (TextView) findViewById(R.id.we6));

            textMap.put(40, (TextView) findViewById(R.id.th0));
            textMap.put(41, (TextView) findViewById(R.id.th1));
            textMap.put(42, (TextView) findViewById(R.id.th2));
            textMap.put(43, (TextView) findViewById(R.id.th3));
            textMap.put(44, (TextView) findViewById(R.id.th4));
            textMap.put(45, (TextView) findViewById(R.id.th5));
            textMap.put(46, (TextView) findViewById(R.id.th6));

            textMap.put(50, (TextView) findViewById(R.id.fr0));
            textMap.put(51, (TextView) findViewById(R.id.fr1));
            textMap.put(52, (TextView) findViewById(R.id.fr2));
            textMap.put(53, (TextView) findViewById(R.id.fr3));
            textMap.put(54, (TextView) findViewById(R.id.fr4));
            textMap.put(55, (TextView) findViewById(R.id.fr5));
            textMap.put(56, (TextView) findViewById(R.id.fr6));

            // MONTAG
            // Färben des Spaltenkopfs wenn das Datum HEUTE ist.
            if (dateMo.get(Calendar.DAY_OF_YEAR) == dateTo.get(Calendar.DAY_OF_YEAR)) {
                textMap.get(10).setBackgroundResource(R.color.Light_Blue);
            }
            if (dateMo.get(Calendar.DAY_OF_YEAR) == dateTo.get(Calendar.DAY_OF_YEAR)) {
                textMap.get(20).setBackgroundResource(R.color.Light_Blue);
            }
            if (dateMo.get(Calendar.DAY_OF_YEAR) == dateTo.get(Calendar.DAY_OF_YEAR)) {
                textMap.get(30).setBackgroundResource(R.color.Light_Blue);
            }
            if (dateMo.get(Calendar.DAY_OF_YEAR) == dateTo.get(Calendar.DAY_OF_YEAR)) {
                textMap.get(40).setBackgroundResource(R.color.Light_Blue);
            }
            if (dateMo.get(Calendar.DAY_OF_YEAR) == dateTo.get(Calendar.DAY_OF_YEAR)) {
                textMap.get(50).setBackgroundResource(R.color.Light_Blue);
            }
            //Fächerfarben, Lehrernamen, Fachnamen und Raumnummer werden per Schleife anhand der lessonMap eingetragen.
            for (int i=10; i<60; i=i+10) {
                for (int j = 1; j < 7; j++) {
                    final int l = i;
                    final int m = j;
                    final int k = i+j;
                    new LessonTask(this, myApp) {
                        @Override
                        public void onPostExecute(LessonResponse result) {
                            if(result != null)
                            {
                                final LessonTO lesson = result.getLesson();
                                textMap.get(k).setBackgroundResource(BorderChooser.getBorderFromId(lesson.getSubject().getSubjectID()));
                                textMap.get(k).setText(lesson.getSubject().getDescription());
                                textMap.get(k).setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        onSubjectClick(v, lesson.getLessonID(), lesson.getTeacher().getName());
                                    }
                                });
                                Log.i(" Zelle: " + k +" : ", " ( " + dateMap.get(l/10) + ", " + m + " ) ");
                            }
                            else
                            {
                                textMap.get(k).setText("Freistunde");
                                Log.i("LessonByDateTask", "fehlgeschlagen oder Freistunde");
                            }
                        }
                    }.execute(5); // <-------- hier muss später der getLessonBydate()-task hin mit ttmmjjjj und hour als i
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

    // Methode für Fachauswahl
    public void onSubjectClick(View view, int lessonId, String name) {
        Intent getSubjectIntent = new Intent(this, SubjectActivity.class);
        getSubjectIntent.putExtra("lessonId", lessonId);
        getSubjectIntent.putExtra("name", name);
        startActivity(getSubjectIntent);
    }
    // Methode nächster TAG
    public void onNextClickP(View view){
        Intent getNextIntent = new Intent(this, MainActivity.class);
        // Datum der aktuellen Activity wird um einen Tag erhöht. Falls dies ein Wochenende ist wird zum Montag gesprungen
        date.add(Calendar.DATE, 1);
        if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            date.add(Calendar.DATE, 2);
        }
        if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            date.add(Calendar.DATE, 1);
        }
        // Datum wird in Millisekunden umgewandelt, da per Intent kein Date-Objekt mitgeliefert werden kann.
        getNextIntent.putExtra("dateInMillis", date.getTimeInMillis());
        // Angabe der Herkunt zum Handeln der Extras
        getNextIntent.putExtra("origin","main_portrait");
        // Angabe in welche Richtung die Animation ablaufen soll.
        getNextIntent.putExtra("direction","right");
        // Unterdrücken der Standardanimation
        getNextIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(getNextIntent);
    }
    // Methode vorheriger TAG (Kommentare wie bei "nächster TAG")
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
        getPreviousIntent.putExtra("direction","left");

        getPreviousIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(getPreviousIntent);
    }
    // Methode nächste WOCHE (ähnlich wie "nächster TAG" jedoch mit 7 Tagen)
    public void onNextClickL(View view){
        Intent getNextIntent = new Intent(this, MainActivity.class);
        date.add(Calendar.DATE, 7);
        getNextIntent.putExtra("dateInMillis", date.getTimeInMillis());
        getNextIntent.putExtra("origin","main_landscape");
        getNextIntent.putExtra("direction","right");
        getNextIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(getNextIntent);
    }
    // Methode vorherige Woche (Kommentare wie bei "nächste "WOCHE")
    public void onPreviousClickL(View view){
        Intent getPreviousIntent = new Intent(this, MainActivity.class);
        date.add(Calendar.DATE, -7);
        getPreviousIntent.putExtra("dateInMillis", date.getTimeInMillis());
        getPreviousIntent.putExtra("origin","main_landscape");
        getPreviousIntent.putExtra("direction","left");
        getPreviousIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(getPreviousIntent);
    }
}