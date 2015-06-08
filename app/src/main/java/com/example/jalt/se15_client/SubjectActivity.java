package com.example.jalt.se15_client;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SubjectActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        Intent whichSubjectName = getIntent();
        String SubjectName = whichSubjectName.getExtras().getString("SubjectName");
        final TextView textViewToChange = (TextView) findViewById(R.id.subject_name);
        textViewToChange.setText(SubjectName);
    }
}
