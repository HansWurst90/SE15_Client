package com.example.jalt.se15_client;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Es muss ein Datum empfamgbar sein. Wenn dieses NULL ist wird das aktuelle Datum verwendet
        Date today = new Date();
        // Abfrage: Welche Fächer gibt es am heutigen Tag?
        //Objekt mit Array mit Veranstaltungsobjekten wird zurückgeliefert


        // Hier wird das heutige Datum für die Anzeige im Kopf der Tabelle aufbereitet.
        DateFormat dfmt = new SimpleDateFormat("dd.MM.yy");
        final TextView textViewToChange = (TextView) findViewById(R.id.daytoday);
        textViewToChange.setText(dfmt.format(today).toString());
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

    public void onSubjectClick(View view) {

        Intent getSubjectIntent = new Intent(this, SubjectActivity.class);

        int SubjectId = view.getId();
        String SubjectName = getResources().getResourceEntryName(SubjectId);

        getSubjectIntent.putExtra("SubjectName", SubjectName);

        startActivity(getSubjectIntent);
    }
}
