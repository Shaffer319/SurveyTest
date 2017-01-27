package edu.umass.cs.sensors.surveytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static String DISPLAY_SURVEY_ACTION = "display";
    public static String ESCAPED_SURVEY = "survey-escaped";
    public static String UNESCAPED_SURVEY = "survey-unescaped";
    public static String ESCAPED_RESPONSE = "response-escaped";
    public static String UNESCAPED_RESPONSE = "response-unescaped";
    public static int SURVEY_RESULT = 1;
    public static int SUCCESSFUL_SURVEY_RESPONSE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openSurvey(View view) {
        Intent surveyIntent = new Intent(this, DummySurvey.class);
        surveyIntent.setAction(DISPLAY_SURVEY_ACTION);
        surveyIntent.putExtra(UNESCAPED_SURVEY, getResources().getString(R.string.survey_JSON_unescaped));
        surveyIntent.putExtra(ESCAPED_SURVEY, getResources().getString(R.string.survey_JSON_escaped));
        startActivityForResult(surveyIntent, SURVEY_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SURVEY_RESULT) {
            if (resultCode == SUCCESSFUL_SURVEY_RESPONSE) {
                String response = data.getStringExtra(ESCAPED_RESPONSE);
                try {
                    String prettyResponse = new JSONObject(response).toString(2);
                    Toast toast = Toast.makeText(this, prettyResponse, Toast.LENGTH_SHORT);
                    toast.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.d("result code", "" + resultCode);
            }
        }
    }
}
