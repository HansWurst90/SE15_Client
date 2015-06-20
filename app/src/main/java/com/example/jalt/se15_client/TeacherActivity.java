package com.example.jalt.se15_client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import common.HomeworkTO;

/**
 * Created by ErfiMac on 15.06.15.
 */
public class TeacherActivity extends Activity {

    EditText homeworkText;
    Button finishButton;
    int lessonID;


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

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (heigth*.6));

    }

    //Abrufen der Lesson mit dazugehörigen ID und Methode addHomework
    public void finishButtonPress(View view) {
        String homework = homeworkText.getText().toString();
        HomeworkTO homeworkto = new HomeworkTO();
        homeworkto.setDescription(homework);
        Toast.makeText(this, homework + " " + lessonID, Toast.LENGTH_SHORT).show();

        /** TestLessons.getLessonById(lessonID).getHomeworks().add(homeworkto); */
    }
}
