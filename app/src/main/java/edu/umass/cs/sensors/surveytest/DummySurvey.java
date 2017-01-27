package edu.umass.cs.sensors.surveytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.JsonWriter;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DummySurvey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_survey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        displaySurvey();
    }

    private void displaySurvey() {
        
        if (getIntent().getAction() == MainActivity.DISPLAY_SURVEY_ACTION) {
            JSONObject surveyJSON;
            String prettyString;
            String surveyString = getIntent().getStringExtra(MainActivity.ESCAPED_SURVEY);

            System.out.println(surveyString);
            try {
                surveyJSON = new JSONObject(surveyString + "\n");
                prettyString = surveyJSON.toString(2);
            } catch (JSONException e) {
                e.printStackTrace();
                prettyString = "JSON error";
            }

            TextView surveyTextView = (TextView) findViewById(R.id.survey_text);
            surveyTextView.setText(prettyString);
        }
    }

    public void sendSurveyResponse(View view) {
        Intent response = getIntent();
        String escapedResponse = getResources().getString(R.string.survey_result_escaped);
        String unescapedResponse = getResources().getString(R.string.survey_result_unescaped);
        response.putExtra(MainActivity.ESCAPED_RESPONSE, escapedResponse);
        response.putExtra(MainActivity.UNESCAPED_RESPONSE, unescapedResponse);
        setResult(MainActivity.SUCCESSFUL_SURVEY_RESPONSE, response);
        finish();
    }

}
