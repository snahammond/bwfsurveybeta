package com.example.bwfsurveybeta;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.InitialSurvey;

public class MenuActivity extends AppCompatActivity {
    String namebwe = null;
    String countrybwe = null;
    String positionbwe = null;
    boolean calledAMPStart = true;

    private LinearLayout progressBar;
    private TextView progressBarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("NAME_BWE")!=null)
            namebwe = getIntent().getStringExtra("NAME_BWE");
        if(getIntent().getStringExtra("COUNTRY_BWE")!=null)
            countrybwe = getIntent().getStringExtra("COUNTRY_BWE");
        if(getIntent().getStringExtra("POSITION_BWE")!=null)
            positionbwe = getIntent().getStringExtra("POSITION_BWE");

        calledAMPStart = getIntent().getBooleanExtra("CALLED_AMPSTART",true);
        Log.i("Tutorials", "namebwe: " + namebwe);

        setContentView(R.layout.activity_menu);
        showMenu();
    }

    public void showMenu(){

        if(calledAMPStart){
            //wait for sync to finish, because Authenication called start
            startProgress("Please wait... Setting Up!");
            CountDownTimer countDownTimer = new CountDownTimer(16000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    endProgress();
                    initView();
                }
            };
            countDownTimer.start();

        }else
            initView();
    }

    private void initView() {
        Button initialFullSurvey = (Button) findViewById(R.id.button_initialFullSurvey);
        initialFullSurvey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CommunityCardSelectActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("COUNTRY_BWE", countrybwe);
                i.putExtra("POSITION_BWE", positionbwe);
                i.putExtra("SURVEY_TYPE","INITIALSURVEY");
                startActivity(i);
            }
        });

        Button followUpSurvey = (Button) findViewById(R.id.button_followUpSurvey);
        followUpSurvey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FamilyCardSelectActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("SURVEY_TYPE","FOLLOWUPSURVEY");
                startActivity(i);
            }
        });

        Button healthCheckSurvey = (Button) findViewById(R.id.button_healthCheck);
        healthCheckSurvey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FamilyCardSelectActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("SURVEY_TYPE","HEALTHCHECKSURVEY");
                startActivity(i);
            }
        });

        Button sweMonthlySummary = (Button) findViewById(R.id.button_SWEMonthlySummary);
        sweMonthlySummary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SWEMonthlySummaryActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("POSITION_BWE", positionbwe);
                i.putExtra("SURVEY_TYPE","SWESUMMARY");
                startActivity(i);
            }
        });

        Button waterSurvey = (Button) findViewById(R.id.button_waterSurvey);
        waterSurvey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FamilyCardSelectActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("COUNTRY_BWE", countrybwe);
                i.putExtra("POSITION_BWE", positionbwe);
                i.putExtra("SURVEY_TYPE","WATERSURVEYHOUSEHOLD");
                startActivity(i);
            }
        });

        Button waterSurveyComm = (Button) findViewById(R.id.button_waterSurveyComm);
        waterSurveyComm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CommunityCardSelectActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("COUNTRY_BWE", countrybwe);
                i.putExtra("POSITION_BWE", positionbwe);
                i.putExtra("SURVEY_TYPE","WATERSURVEYCOMMUNITY");
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //doProgress();
    }

    private void startProgress(String s) {
        runOnUiThread(new Runnable() {
            public void run() {
                if(progressBar==null)
                    progressBar = (LinearLayout) findViewById(R.id.llProgressBar);
                if(progressBarText==null)
                    progressBarText = (TextView) findViewById(R.id.pbText);

                progressBarText.setText(s);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void endProgress() {
        runOnUiThread(new Runnable() {
            public void run() {
                if(progressBar==null)
                    progressBar = (LinearLayout) findViewById(R.id.llProgressBar);

                progressBar.setVisibility(View.GONE);
            }
        });

    }
}
