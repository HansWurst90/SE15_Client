package com.example.jalt.se15_client;

import android.app.Activity;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        homeworkText = (EditText) findViewById(R.id.teacher_editText);
        finishButton = (Button) findViewById(R.id.finish_button);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (heigth*.6));

    }

    public void finishButtonPress(View view) {
        String homework = homeworkText.getText().toString();
        int lessonID = SubjectActivity.getLessonID();
        Toast.makeText(this, homework + " " + lessonID, Toast.LENGTH_SHORT).show();
        HomeworkTO homeworkto = new HomeworkTO();
        homeworkto.setDescription(homework);
        /** TestLessons.getLessonById(lessonID).getHomeworks().add(homeworkto); */
    }
}
