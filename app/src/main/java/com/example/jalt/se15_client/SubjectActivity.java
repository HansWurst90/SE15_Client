package com.example.jalt.se15_client;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.TwoStatePreference;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


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

    public void homeworkToast(View view) {
        final CheckBox homeworkButton = (CheckBox) findViewById(R.id.homework_checkbutton);
        boolean checked = homeworkButton.isChecked();
       if (checked) {
           Toast.makeText(this, R.string.homeworkDone, Toast.LENGTH_SHORT).show();
       }
       else {
           Toast.makeText(this, R.string.homeworkUndone, Toast.LENGTH_SHORT).show();
       }

    }

    /*@Override
    public void onPause()
    {

        super.onPause();
        save(itemChecked);
    }
    @Override
    public void onResume()
    {
        super.onResume();
        checkOld = load();

        for (int i = 0 ; i < checkOld.length; i++)
        {
            notes.ctv.get(i).setChecked(checkOld[i]);
        }
    }
    @Override
    public void onRestart()
    {
        super.onResume();
        checkOld = load();

        for (int i = 0 ; i < checkOld.length; i++)
        {
            notes.ctv.get(i).setChecked(checkOld[i]);
        }
    }

    private void save(final boolean[] isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        insertState();
        for (Integer i = 0; i < isChecked.length; i++) {
            editor.putBoolean(i.toString(), isChecked[i]);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        save(mCheckBox.isChecked());
    }

    @Override
    public void onResume() {
        super.onResume();
        mCheckBox.setChecked(load());
    }

    private void save(final boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("check", isChecked);
        editor.commit();
    }

    private boolean load() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("check", false);
    }
*/

}
