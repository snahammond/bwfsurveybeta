package com.example.bwfsurveybeta;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.InitialSurvey;

public class MenuActivity extends AppCompatActivity {
    String namebwe = null;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("NAME_BWE")!=null)
            namebwe = getIntent().getStringExtra("NAME_BWE");
        Log.i("Tutorials", "namebwe: " + namebwe);
        doProgress();
        //showMenu();
    }

    public void doProgress(){
        setContentView(R.layout.activity_menu_progress);
        i = 0;
        ProgressBar determinateBar = (ProgressBar)findViewById(R.id.determinateBar);
        determinateBar.setMax(100);
        determinateBar.setProgress(i);
        CountDownTimer mCountDownTimer=new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
                i++;
                determinateBar.setProgress((int)i*100/(10000/1000));
            }
            @Override
            public void onFinish() {
                //Do what you want
                showMenu();
                i++;
                determinateBar.setProgress(100);
            }
        };
        mCountDownTimer.start();
    }

    public void showMenu(){
        setContentView(R.layout.activity_menu);
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
                Intent i = new Intent(getApplicationContext(), FamilyCardSelect.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("SURVEY_TYPE","FOLLOWUP");
                startActivity(i);
            }
        });

    }

    public void tryDownloadAgain(){
        try{
            Amplify.DataStore.query(
                    InitialSurvey.class,
                    allInitialSurveyFamilys -> {
                        Log.i("Tutorials", "DataStore is queried.");
                        while (allInitialSurveyFamilys.hasNext()) {
                            InitialSurvey aFamily = allInitialSurveyFamilys.next();
                            Log.i("Tutorials", "Title: " + aFamily.getHeadHouseholdName());
                        }
                        runOnUiThread(new Runnable() {
                            public void run() {
                                showMenu();
                            }
                        });
                    },
                    failure ->{
                        Log.e("Tutorials", "Query failed.", failure);
                        showMenu();
                    }
            );
        }catch (Exception x){
            //show less menu
            Log.i("Tutorials", "show less menu.");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //doProgress();
    }
}
