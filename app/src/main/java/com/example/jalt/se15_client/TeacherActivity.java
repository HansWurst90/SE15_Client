package com.example.jalt.se15_client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jalt.se15_client.tasks.AddHomeworkTask;

/**
 * Created by ErfiMac on 15.06.15.
 */
public class TeacherActivity extends Activity {

    EditText homeworkText;
    Button finishButton;
    int lessonID;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        homeworkText = (EditText) findViewById(R.id.teacher_editText);
        finishButton = (Button) findViewById(R.id.finish_button);
        Intent subjectIntent = getIntent();
        lessonID = subjectIntent.getExtras().getInt("lessonId");
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        /** View backgroundView = findViewById(R.id.ksdjfös);
        Drawable background = backgroundView.getBackground();
        background.setAlpha(80);*/

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (heigth*.6));

    }

    //Abrufen der Lesson mit dazugehörigen ID und Methode addHomework
    public void finishButtonPress(View view) {
        String homework = homeworkText.getText().toString();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int sessionId = Integer.parseInt(sharedPreferences.getString("SESSIONID", ""));
        StudeasyScheduleApplication myApp = (StudeasyScheduleApplication) getApplication();
        AddHomeworkTask homeworkTask = new AddHomeworkTask(this, myApp);
        homeworkTask.execute(sessionId, lessonID, homework);
        Toast.makeText(this, homework + " " + lessonID, Toast.LENGTH_SHORT).show();

        /** TestLessons.getLessonById(lessonID).getHomeworks().add(homeworkto); */
    }
}
