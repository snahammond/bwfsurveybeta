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
    boolean calledAMPStart = true;

    private LinearLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("NAME_BWE")!=null)
            namebwe = getIntent().getStringExtra("NAME_BWE");

        calledAMPStart = getIntent().getBooleanExtra("CALLED_AMPSTART",true);

        Log.i("Tutorials", "namebwe: " + namebwe);
        showMenu();
    }

    public void showMenu(){
        setContentView(R.layout.activity_menu);
        if(calledAMPStart){
            //wait for sync to finish, because Authenication called start
            progressBar = (LinearLayout) findViewById(R.id.llProgressBar);
            TextView progressBarText = (TextView) findViewById(R.id.pbText);
            progressBarText.setText("Please wait... Setting Up!");
            progressBar.setVisibility(View.VISIBLE);
            CountDownTimer countDownTimer = new CountDownTimer(16000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    progressBar.setVisibility(View.GONE);
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
                Intent i = new Intent(getApplicationContext(),InitialSurveyActivity.class);
                i.putExtra("NAME_BWE", namebwe);
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        //doProgress();
    }
}
