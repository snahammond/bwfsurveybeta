package com.bwfsurvey.bwfsurveybeta.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.ConfigDefinitions;
import com.bwfsurvey.bwfsurveybeta.MyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.dialogs.SelectCountryDialogFragment;
import com.bwfsurvey.bwfsurveybeta.types.Config;
import com.bwfsurvey.bwfsurveybeta.utils.PhoneLocation;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    String namebwe = null;
    String countrybwe = null;
    String positionbwe = null;
    boolean calledAMPStart = true;
    String lat = null;
    String lng = null;

    private LinearLayout progressBar;
    private TextView progressBarText;

    boolean adviceUser = false;

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

        PhoneLocation phoneLocation = new PhoneLocation(MenuActivity.this);
        String[] arraylatlng = phoneLocation.getLocation();
        if(arraylatlng!=null){
            this.lat = arraylatlng[0];
            this.lng = arraylatlng[1];
        }
        Log.i("Tutorials", "lat " +this.lat );
        Log.i("Tutorials", "lat " +this.lng );

        setContentView(R.layout.activity_menu);
        progressBar = (LinearLayout) findViewById(R.id.llProgressBar);
        progressBarText = (TextView) findViewById(R.id.pbText);

        progressBarText.setText("Please wait... Synchronizing with cloud!");
        progressBar.setVisibility(View.VISIBLE);/*
        Amplify.DataStore.start(
                () -> {
                    Log.i("Tutorials", "DataStore started");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            CountDownTimer countDownTimer = new CountDownTimer(30000,1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                }

                                @Override
                                public void onFinish() {
                                    ArrayList<Config> configsInner = new ArrayList<Config>();
                                    Amplify.DataStore.query(
                                            ConfigDefinitions.class,
                                            allConfigDefinitions -> {
                                                Log.i("Tutorials", "DataStore is queried config query.");
                                                while (allConfigDefinitions.hasNext()) {
                                                    ConfigDefinitions configDef = allConfigDefinitions.next();
                                                    if(configDef.getType().contentEquals("C")){
                                                        Log.i("Tutorials", "country config. "+ configDef.getChildvalue());
                                                    }
                                                    configsInner.add(new Config(configDef.getType(), configDef.getName(), configDef.getValue(),configDef.getDesc(), configDef.getChildname(), configDef.getChildvalue(), configDef.getChilddesc(),configDef.getParentname(), configDef.getParentvalue(), configDef.getParentdesc()));
                                                }
                                                Log.i("Tutorials", "DataStore is queried for configs. No of configs is "+configsInner.size() );
                                                if(configsInner.size()>0){
                                                    MyAmplifyApplication.configs = configsInner;
                                                    MyAmplifyApplication.interchangePool = MyAmplifyApplication.makeAllInterchanges();
                                                    Log.i("Tutorials", "DataStore is queried for configs. No of configs is "+MyAmplifyApplication.configs.size() );
                                                }

                                                runOnUiThread(new Runnable() {
                                                    public void run() {
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
                                                runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        progressBar.setVisibility(View.GONE);
                                                        showMenu();
                                                    }
                                                });
                                            }
                                    );
                                }
                            };
                            countDownTimer.start();
                        }
                    });
                },
                error -> {
                    Log.e("Tutorials", "Error starting DataStore", error);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            showMenu();
                        }
                    });
                }
        );
        */

        ArrayList<Config> configsInner = new ArrayList<Config>();
        Amplify.DataStore.query(
                ConfigDefinitions.class,
                allConfigDefinitions -> {
                    Log.i("Tutorials", "DataStore is queried config query.");
                    while (allConfigDefinitions.hasNext()) {
                        ConfigDefinitions configDef = allConfigDefinitions.next();
                        if(configDef.getType().contentEquals("C")){
                            Log.i("Tutorials", "country config. "+ configDef.getChildvalue());
                        }
                        configsInner.add(new Config(configDef.getType(), configDef.getName(), configDef.getValue(),configDef.getDesc(), configDef.getChildname(), configDef.getChildvalue(), configDef.getChilddesc(),configDef.getParentname(), configDef.getParentvalue(), configDef.getParentdesc()));
                    }
                    Log.i("Tutorials", "DataStore is queried for configs. No of configs is "+configsInner.size() );
                    if(configsInner.size()>0){
                        MyAmplifyApplication.configs = configsInner;
                        MyAmplifyApplication.interchangePool = MyAmplifyApplication.makeAllInterchanges();
                        Log.i("Tutorials", "DataStore is queried for configs. No of configs is "+MyAmplifyApplication.configs.size() );
                    }
                    runOnUiThread(new Runnable() {
                        public void run() {
                            showMenu();
                        }
                    });

                },
                failure ->{
                    Log.i("Tutorials", "Query failed, going to use file " );
                    Log.e("Tutorials", "Query failed.", failure);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            showMenu();
                        }
                    });
                }
        );


    }

    public void showMenu(){

        //if(calledAMPStart){
            //wait for sync to finish, because Authenication called start
            startProgress("Please wait... Setting Up!");
            CountDownTimer countDownTimer = new CountDownTimer(16000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    Log.i("Tutorials", "endProgress in showMenu" );
                    progressBar.setVisibility(View.GONE);
                    endProgress();
                    initView();
                }
            };
            countDownTimer.start();

        //}else
          //  initView();
    }

    private void initView() {

        //create menu items
        Button initialFullSurvey = (Button) findViewById(R.id.button_initialFullSurvey);
        initialFullSurvey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CommunityCardSelectActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("COUNTRY_BWE", countrybwe);
                i.putExtra("POSITION_BWE", positionbwe);
                i.putExtra("SURVEY_TYPE","INITIALSURVEY");
                i.putExtra("LAT",lat);
                i.putExtra("LNG",lng);
                startActivity(i);
            }
        });

        Button followUpSurvey = (Button) findViewById(R.id.button_followUpSurvey);
        followUpSurvey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HouseholdCardSelectActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("SURVEY_TYPE","FOLLOWUPSURVEY");
                i.putExtra("OPERATION","CREATE");
                i.putExtra("LAT",lat);
                i.putExtra("LNG",lng);
                startActivity(i);
            }
        });

        Button healthCheckSurvey = (Button) findViewById(R.id.button_healthCheck);
        healthCheckSurvey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HouseholdCardSelectActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("SURVEY_TYPE","HEALTHCHECKSURVEY");
                i.putExtra("OPERATION","CREATE");
                i.putExtra("LAT",lat);
                i.putExtra("LNG",lng);
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
                i.putExtra("LAT",lat);
                i.putExtra("LNG",lng);
                startActivity(i);
            }
        });

        Button waterSurvey = (Button) findViewById(R.id.button_waterSurvey);
        waterSurvey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HouseholdCardSelectActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("COUNTRY_BWE", countrybwe);
                i.putExtra("POSITION_BWE", positionbwe);
                i.putExtra("SURVEY_TYPE","WATERSURVEYHOUSEHOLD");
                i.putExtra("OPERATION","CREATE");
                i.putExtra("LAT",lat);
                i.putExtra("LNG",lng);
                startActivity(i);
            }
        });

        Button waterSurveyComm = (Button) findViewById(R.id.button_waterSurveyComm);
        waterSurveyComm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CommunityWaterCardSelectActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("COUNTRY_BWE", countrybwe);
                i.putExtra("POSITION_BWE", positionbwe);
                i.putExtra("OPERATION", "CREATE");
                i.putExtra("SURVEY_TYPE","WATERSURVEYCOMMUNITY");
                i.putExtra("LAT",lat);
                i.putExtra("LNG",lng);
                startActivity(i);
            }
        });


        Button waterSurveyFromVol = (Button) findViewById(R.id.button_waterSurveyFromVol);
        waterSurveyFromVol.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VolunteerHouseholdCardSelectActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("COUNTRY_BWE", countrybwe);
                i.putExtra("POSITION_BWE", positionbwe);
                i.putExtra("SURVEY_TYPE","WATERSURVEYHOUSEHOLD");
                i.putExtra("OPERATION","CREATE");
                i.putExtra("LAT",lat);
                i.putExtra("LNG",lng);
                startActivity(i);
            }
        });

        //view menu items
        Button initialFullSurveyView = (Button) findViewById(R.id.button_initialFullSurveyView);
        initialFullSurveyView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HouseholdCardSelectActivity.class);
                i.putExtra("OPERATION","VIEW");
                startActivity(i);
            }
        });




        //update incompleted menu items
        Button initialFullSurveyUC = (Button) findViewById(R.id.button_initialFullSurveyUC);
        initialFullSurveyUC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HouseholdCardSelectActivity.class);
                i.putExtra("OPERATION","UPDATE");
                startActivity(i);
            }
        });

        Button followUpSurveyUpdate = (Button) findViewById(R.id.button_followUpSurveyUC);
        followUpSurveyUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UpdateFollowUpSurveyCardSelectActivity.class);
                i.putExtra("OPERATION","UPDATE");
                startActivity(i);
            }
        });

        Button waterSurveyCommUC = (Button) findViewById(R.id.button_waterSurveyCommUC);
        waterSurveyCommUC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UpdateCommunityWaterCardSelectActivity.class);
                i.putExtra("OPERATION","UPDATE");
                startActivity(i);
            }
        });

        Button waterSurveyUC = (Button) findViewById(R.id.button_waterSurveyUC);
        waterSurveyUC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UpdateHouseholdWaterCardSelectActivity.class);
                i.putExtra("OPERATION","UPDATE");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.changeSurveyCountry) {
            Log.i("Tutorials", "going to change survey country" );
            ArrayList<String> listOfCountries = MyAmplifyApplication.getCountries();
            DialogFragment dialog = new SelectCountryDialogFragment(listOfCountries, new SelectCountryDialogFragment.SelectCountryDialogFragmentListener() {
                @Override
                public void onSelectedCountry(String countryName) {
                    countrybwe = countryName;
                    Log.i("Tutorials", "country selected is " + countrybwe );
                }
            });
            dialog.show(getSupportFragmentManager(), "countries");
        }

        if (id == R.id.syncCloud) {
            Log.i("Tutorials", "going to sync with cloud" );
            progressBarText.setText("Please wait... Synchronizing with cloud!");
            progressBar.setVisibility(View.VISIBLE);
            Amplify.DataStore.clear(
                    () -> {
                        runOnUiThread(new Runnable() {
                            public void run() {
                            CountDownTimer countDownTimer = new CountDownTimer(10000,1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                }

                                @Override
                                public void onFinish() {
                                    Log.i("Tutorials", "Done clearing datastore under synchronize");
                                    Amplify.DataStore.start(
                                            () -> {
                                                Log.i("Tutorials", "DataStore started");
                                                runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        CountDownTimer countDownTimer = new CountDownTimer(30000,1000) {
                                                            @Override
                                                            public void onTick(long millisUntilFinished) {
                                                            }

                                                            @Override
                                                            public void onFinish() {
                                                                ArrayList<Config> configsInner = new ArrayList<Config>();
                                                                Amplify.DataStore.query(
                                                                        ConfigDefinitions.class,
                                                                        allConfigDefinitions -> {
                                                                            Log.i("Tutorials", "DataStore is queried config query.");
                                                                            while (allConfigDefinitions.hasNext()) {
                                                                                ConfigDefinitions configDef = allConfigDefinitions.next();
                                                                                if(configDef.getType().contentEquals("C")){
                                                                                    Log.i("Tutorials", "country config. "+ configDef.getChildvalue());
                                                                                }
                                                                                configsInner.add(new Config(configDef.getType(), configDef.getName(), configDef.getValue(),configDef.getDesc(), configDef.getChildname(), configDef.getChildvalue(), configDef.getChilddesc(),configDef.getParentname(), configDef.getParentvalue(), configDef.getParentdesc()));
                                                                            }
                                                                            Log.i("Tutorials", "DataStore is queried for configs. No of configs is "+configsInner.size() );
                                                                            if(configsInner.size()>0){
                                                                                MyAmplifyApplication.configs = configsInner;
                                                                                MyAmplifyApplication.interchangePool = MyAmplifyApplication.makeAllInterchanges();
                                                                                Log.i("Tutorials", "DataStore is queried for configs. No of configs is "+MyAmplifyApplication.configs.size() );
                                                                            }

                                                                            runOnUiThread(new Runnable() {
                                                                                public void run() {
                                                                                    CountDownTimer countDownTimer = new CountDownTimer(16000,1000) {
                                                                                        @Override
                                                                                        public void onTick(long millisUntilFinished) {
                                                                                        }

                                                                                        @Override
                                                                                        public void onFinish() {
                                                                                            progressBar.setVisibility(View.GONE);
                                                                                        }
                                                                                    };
                                                                                    countDownTimer.start();
                                                                                }
                                                                            });

                                                                        },
                                                                        failure ->{
                                                                            Log.i("Tutorials", "Query failed, going to use file " );
                                                                            Log.e("Tutorials", "Query failed.", failure);
                                                                            runOnUiThread(new Runnable() {
                                                                                public void run() {
                                                                                    progressBar.setVisibility(View.GONE);
                                                                                }
                                                                            });
                                                                        }
                                                                );
                                                            }
                                                        };
                                                        countDownTimer.start();
                                                    }
                                                });
                                            },
                                            error -> {
                                                Log.e("Tutorials", "Error starting DataStore", error);
                                                runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        progressBar.setVisibility(View.GONE);
                                                    }
                                                });
                                            }
                                    );
                                }
                            };
                            countDownTimer.start();
                            }
                        });
                    },
                    error ->{
                        Log.e("Tutorials", "Error clearing DataStore", error);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    });


        }

        return super.onOptionsItemSelected(item);
    }
}
