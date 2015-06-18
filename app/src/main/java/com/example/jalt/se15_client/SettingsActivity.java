package com.example.jalt.se15_client;

import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jalt.se15_client.tasks.LoginTask;


/**
 *
 * @author Erfkämper
 */

public class SettingsActivity extends Activity{
    EditText username;
    EditText password;
    Button loginButton;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login_button);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String User = sharedPreferences.getString("USER", "");
        String Password = sharedPreferences.getString("PASSWORD", "");
        String sessionId = sharedPreferences.getString("SESSIONID", "");

        username.setText(User + " und " + sessionId);
        password.setText(Password);

        if(User.equals("")  && Password.equals("")){
            loginButton.setText(R.string.login);
            username.setEnabled(true);
            password.setEnabled(true);
        }
        else {
            loginButton.setText(R.string.logout);
            username.setEnabled(false);
            password.setEnabled(false);
        }

    }

    public void loginButtonPress(View view)
    {
        String buttonText = loginButton.getText().toString();
        String login = getResources().getString(R.string.login);

        if(buttonText.equals(login))
        {
            int user = Integer.parseInt(username.getText().toString());;
            String pass = password.getText().toString();

            if(user == 0 && pass.equals(""))
                Toast.makeText(this, R.string.missingBoth, Toast.LENGTH_SHORT).show();
            else if(user == 0 && !pass.equals(""))
                Toast.makeText(this, R.string.missingUsername, Toast.LENGTH_SHORT).show();
            else if(user != 0 && pass.equals(""))
                Toast.makeText(this, R.string.missingPasswort, Toast.LENGTH_SHORT).show();
            else if (user != 0 && !pass.equals(""))
            {
                StudeasyScheduleApplication myApp = (StudeasyScheduleApplication) getApplication();
                LoginTask loginTask = new LoginTask(this, myApp);
                loginTask.execute(user, pass);
            }
        }
        else {

            SavePreferences("USER", null);
            SavePreferences("PASSWORD", null);
            backToMain(view, "logout");
        }
    }

    private void SavePreferences(String key, String value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    private void backToMain(View view, String button)
    {
        Intent getMainIntent = new Intent(this, MainActivity.class);
        getMainIntent.putExtra("origin", button);
        startActivity(getMainIntent);
    }
}
