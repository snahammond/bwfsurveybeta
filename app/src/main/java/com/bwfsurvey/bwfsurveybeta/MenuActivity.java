package com.bwfsurvey.bwfsurveybeta;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.BWFSURVEYTOTALS;
import com.amplifyframework.datastore.generated.model.ConfigDefinitions;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

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
        progressBar = (LinearLayout) findViewById(R.id.llProgressBar);
        progressBarText = (TextView) findViewById(R.id.pbText);

        //do a fake query just for sync to be done
        Amplify.DataStore.query(
                BWFSURVEYTOTALS.class,
                allBwfSurveyTotals -> {
                    Log.i("Tutorials", "DataStore is queried fake query.");
                    while (allBwfSurveyTotals.hasNext()) {
                        allBwfSurveyTotals.next();
                    }
                    runOnUiThread(new Runnable() {
                          public void run() {
                              progressBarText.setText("Please wait... Setting Up fake query!");
                              progressBar.setVisibility(View.VISIBLE);
                              CountDownTimer countDownTimer = new CountDownTimer(16000,1000) {
                                  @Override
                                  public void onTick(long millisUntilFinished) {
                                  }

                                  @Override
                                  public void onFinish() {
                                      progressBar.setVisibility(View.GONE);
                                      ArrayList<Config> configsInner = new ArrayList<Config>();
                                      Amplify.DataStore.query(
                                              ConfigDefinitions.class,
                                              allConfigDefinitions -> {
                                                  Log.i("Tutorials", "DataStore is queried config query.");
                                                  while (allConfigDefinitions.hasNext()) {
                                                      ConfigDefinitions configDef = allConfigDefinitions.next();
                                                      configsInner.add(new Config(configDef.getType(), configDef.getName(), configDef.getValue(),configDef.getDesc(), configDef.getChildname(), configDef.getChildvalue(), configDef.getChilddesc(),configDef.getParentname(), configDef.getParentvalue(), configDef.getParentdesc()));
                                                  }
                                                  Log.i("Tutorials", "DataStore is queried for configs. No of configs is "+configsInner.size() );
                                                  MyAmplifyApplication.configs = configsInner;
                                                  MyAmplifyApplication.interchangePool = MyAmplifyApplication.makeAllInterchanges();
                                                  Log.i("Tutorials", "DataStore is queried for configs. No of configs is "+MyAmplifyApplication.configs.size() );

                                                  runOnUiThread(new Runnable() {
                                                        public void run() {
                                                            progressBarText.setText("Please wait... Setting Up config query!");
                                                            progressBar.setVisibility(View.VISIBLE);
                                                            CountDownTimer countDownTimer = new CountDownTimer(16000,1000) {
                                                                @Override
                                                                public void onTick(long millisUntilFinished) {
                                                                }

                                                                @Override
                                                                public void onFinish() {
                                                                    progressBar.setVisibility(View.GONE);
                                                                    showMenu();
                                                                }
                                                            };
                                                            countDownTimer.start();
                                                        }
                                                    });

                                              },
                                              failure ->{
                                                  Log.i("Tutorials", "Query failed, going to use file " );
                                                  Log.e("Tutorials", "Query failed.", failure);
                                              }
                                      );
                                  }
                              };
                              countDownTimer.start();
                          }
                      });

                    Log.i("Tutorials", "DataStore is queried for configs. No of configs is ");
                },
                failure ->{
                    Log.i("Tutorials", "Query failed BWFSURVEYTOTALS, going to use file " );
                    Log.e("Tutorials", "Query failed BWFSURVEYTOTALS.", failure);
                }
        );






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
