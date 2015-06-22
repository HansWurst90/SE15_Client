package de.jalt.studeasy.client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jalt.studeasy.client.R;

import de.jalt.studeasy.client.tasks.LoginTask;
import de.jalt.studeasy.client.tasks.LogoutTask;

/**
 * SettingsActivity behandelt das ein- und ausloggen
 * @author Jan Mußenbrock und Lukas Erfkämper
 */
public class SettingsActivity extends Activity{
    EditText username;
    EditText password;
    Button loginButton;
    int sessionId;
    SharedPreferences sharedPreferences;

    /**
     * Bei der onCreate Methode wird die Activity aufgebaut, unterscheidung bei ob angemeldet oder nicht
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialsierung der Variablen
        setContentView(R.layout.activity_settings);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login_button);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String User = sharedPreferences.getString("USER", "");
        String Password = sharedPreferences.getString("PASSWORD", "");

        username.setText(User);
        password.setText(Password);

        //Wenn man angemeldet ist, werden Benutzererkennung und Passwort Eingabe auf nicht bearbeitbar gesetzt
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

    /**
     * Die Methode für den Login/Logout Button
     * @param view
     */
    public void loginButtonPress(View view)
    {
        String buttonText = loginButton.getText().toString();
        String login = getResources().getString(R.string.login);

        //Wenn Button auf Login stand
        if(buttonText.equals(login))
        {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            //Falls etwas bei den Eingaben fehlte
            if(user.equals("") && pass.equals(""))
                Toast.makeText(this, R.string.missingBoth, Toast.LENGTH_SHORT).show();
            else if(user.equals("") && !pass.equals(""))
                Toast.makeText(this, R.string.missingUsername, Toast.LENGTH_SHORT).show();
            else if(!user.equals("") && pass.equals(""))
                Toast.makeText(this, R.string.missingPasswort, Toast.LENGTH_SHORT).show();
            else if (!user.equals("") && !pass.equals(""))
            {
                //Neuer Task wird gestartet
                StudeasyScheduleApplication myApp = (StudeasyScheduleApplication) getApplication();
                LoginTask loginTask = new LoginTask(this, myApp);
                loginTask.execute(Integer.parseInt(user), pass);
            }
        }
        else { //Button steht auf Ausloggen
            StudeasyScheduleApplication myApp = (StudeasyScheduleApplication) getApplication();
            LogoutTask logoutTask = new LogoutTask(this, myApp);
            sessionId = Integer.parseInt(sharedPreferences.getString("SESSIONID", ""));
            logoutTask.execute(sessionId);
        }
    }

    /**
     * Methode für den BackButton des Android Smartphones
     */
    @Override
    public void onBackPressed()
    {
        Intent getMainIntent = new Intent(this, MainActivity.class);
        startActivity(getMainIntent);
    }
}
