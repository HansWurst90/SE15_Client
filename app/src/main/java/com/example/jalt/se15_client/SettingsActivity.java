package com.example.jalt.se15_client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 *
 * @author Mußenbrock
 */

public class SettingsActivity extends Activity{
    EditText username;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login_button);
        LoadPreferences();
    }

    public void loginClick(View view)
    {
        SavePreferences("USER", username.getText().toString());
        SavePreferences("PASSWORD", password.getText().toString());
        backToMain(view);
    }

    private void SavePreferences(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private void LoadPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String savedUser = sharedPreferences.getString("USER", "");
        username.setText(savedUser);
    }

    private void backToMain(View view)
    {
        Intent getMainIntent = new Intent(this, MainActivity.class);

        int MainId = view.getId();
        String SubjectName = getResources().getResourceEntryName(MainId);

        //getMainIntent.putExtra("SubjectName", SubjectName);

        startActivity(getMainIntent);
    }

}
