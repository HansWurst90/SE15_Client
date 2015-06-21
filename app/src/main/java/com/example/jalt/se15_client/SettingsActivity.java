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
import com.example.jalt.se15_client.tasks.LogoutTask;


/**
 * Login und Logout
 * @author Lukas Erfkämper
 */

public class SettingsActivity extends Activity{
    EditText username;
    EditText password;
    Button loginButton;
    int sessionId;
    SharedPreferences sharedPreferences;

    @Override
    /**
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login_button);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String User = sharedPreferences.getString("USER", "");
        String Password = sharedPreferences.getString("PASSWORD", "");

        username.setText(User);
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
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if(user.equals("") && pass.equals(""))
                Toast.makeText(this, R.string.missingBoth, Toast.LENGTH_SHORT).show();
            else if(user.equals("") && !pass.equals(""))
                Toast.makeText(this, R.string.missingUsername, Toast.LENGTH_SHORT).show();
            else if(!user.equals("") && pass.equals(""))
                Toast.makeText(this, R.string.missingPasswort, Toast.LENGTH_SHORT).show();
            else if (!user.equals("") && !pass.equals(""))
            {
                StudeasyScheduleApplication myApp = (StudeasyScheduleApplication) getApplication();
                LoginTask loginTask = new LoginTask(this, myApp);
                loginTask.execute(Integer.parseInt(user), pass);
            }
        }
        else {
            StudeasyScheduleApplication myApp = (StudeasyScheduleApplication) getApplication();
            LogoutTask logoutTask = new LogoutTask(this, myApp);
            sessionId = Integer.parseInt(sharedPreferences.getString("SESSIONID", ""));
            logoutTask.execute(sessionId);
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent getMainIntent = new Intent(this, MainActivity.class);
        startActivity(getMainIntent);
    }


    private void backToMain(View view, String button)
    {
        Intent getMainIntent = new Intent(this, MainActivity.class);
        getMainIntent.putExtra("origin", button);
        startActivity(getMainIntent);
    }
}
