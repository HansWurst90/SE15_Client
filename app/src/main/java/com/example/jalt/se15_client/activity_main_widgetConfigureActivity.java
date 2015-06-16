package com.example.jalt.se15_client;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * The configuration screen for the {@link activity_main_widget activity_main_widget} AppWidget.
 */
public class activity_main_widgetConfigureActivity extends Activity {

    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    Button hochX;
    Button runterX;
    Button hochY;
    Button runterY;
    TextView xCoordinate;
    TextView yCoordinate;
    Button finishButton;

    public activity_main_widgetConfigureActivity() {
        super();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        setContentView(R.layout.activity_main_widget_configure);

        hochX = (Button) findViewById(R.id.hochX);
        runterX = (Button) findViewById(R.id.runterX);
        hochY = (Button) findViewById(R.id.hochY);
        runterY = (Button) findViewById(R.id.runterY);
        xCoordinate = (TextView) findViewById(R.id.xCoordinate);
        yCoordinate = (TextView) findViewById(R.id.yCoordinate);
        finishButton = (Button) findViewById(R.id.widgetConfigureFinishButton);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }
    }

    public void scaleWidgetUpX(View view) {
        String xCoordinateField = xCoordinate.getText().toString();
        int x  = Integer.parseInt(xCoordinateField);
        if(x < 4) {
            x++;
            xCoordinateField = Integer.toString(x);
            xCoordinate.setText(xCoordinateField);
        }
    }

    public void scaleWidgetDownX(View view) {
        String xCoordinateField = xCoordinate.getText().toString();
        int x  = Integer.parseInt(xCoordinateField);
        if(x > 2) {
            x--;
            xCoordinateField = Integer.toString(x);
            xCoordinate.setText(xCoordinateField);
        }
    }

    public void scaleWidgetUpY(View view) {
        String yCoordinateField = yCoordinate.getText().toString();
        int y  = Integer.parseInt(yCoordinateField);
        if(y < 4) {
            y++;
            yCoordinateField = Integer.toString(y);
            yCoordinate.setText(yCoordinateField);
        }
    }

    public void scaleWidgetDownY(View view) {
        String yCoordinateField = yCoordinate.getText().toString();
        int y  = Integer.parseInt(yCoordinateField);
        if(y > 2) {
            y--;
            yCoordinateField = Integer.toString(y);
            yCoordinate.setText(yCoordinateField);
        }
    }

    public void widgetConfigureFinish(View view) {
        finish();
    }
}

