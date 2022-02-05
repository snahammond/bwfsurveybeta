package com.bwfsurvey.bwfsurveybeta.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.ConfigDefinitions;
import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.activities.collect.SubMenuMonthlySummaryActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.CommunityCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.CommunityWaterCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.HealthCheckSurveyCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.HouseholdCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.CommunityWaterTestCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.FollowUpSurveyCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.HouseholdWaterTestCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.VolunteerHouseholdCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.VolunteerHouseholdWaterTestCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.update.UpdateSubMenuMonthlySummaryActivity;
import com.bwfsurvey.bwfsurveybeta.activities.view.ViewSubMenuMonthlySummaryActivity;
import com.bwfsurvey.bwfsurveybeta.dialogs.CreateNewVolunteerHousehold;
import com.bwfsurvey.bwfsurveybeta.dialogs.SelectCountryDialogFragment;
import com.bwfsurvey.bwfsurveybeta.types.Community;
import com.bwfsurvey.bwfsurveybeta.types.Config;
import com.bwfsurvey.bwfsurveybeta.utils.PhoneLocation;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements SelectCountryDialogFragment.SelectCountryDialogFragmentListener{

    String namebwe = null;
    String countrybwe = null;
    String positionbwe = null;
    boolean calledAMPStart = true;
    String lat = null;
    String lng = null;

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

        PhoneLocation phoneLocation = new PhoneLocation(MenuActivity.this);
        String[] arraylatlng = phoneLocation.getLocation();
        if(arraylatlng!=null){
            this.lat = arraylatlng[0];
            this.lng = arraylatlng[1];
        }
        Log.i("Tutorials", "lat " +this.lat );
        Log.i("Tutorials", "lat " +this.lng );

        setContentView(R.layout.activity_menu);
        progressBar = findViewById(R.id.llProgressBar);
        progressBarText = findViewById(R.id.pbText);

        progressBarText.setText("Please wait... Synchronizing with cloud!");
        progressBar.setVisibility(View.VISIBLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //first of all we will query the cloud to get lastest version of app
        Amplify.API.query(
                ModelQuery.get(ConfigDefinitions.class, "f5ccce23-64a7-4bb6-8f79-02ac8d03a659"),
                response -> {
                    int remoteAppVersion = Integer.parseInt(response.getData().getValue());
                    int localFileAppVersion = BwfSurveyAmplifyApplication.APPVERSION;
                    Log.i("BwfSurveyAmplifyApp", "remoteAppVersion: "+ remoteAppVersion );
                    Log.i("BwfSurveyAmplifyApp", "localFileAppVersion: "+ localFileAppVersion );
                    if(remoteAppVersion>localFileAppVersion){
                        //we need to get ready to do sync, but first lets check local storage if we have the correct app version
                        ArrayList<Config> configsInner = new ArrayList<>();
                        ArrayList<ConfigDefinitions> configsInnerFromLocalStore = new ArrayList<>();
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
                                        configsInnerFromLocalStore.add(configDef);
                                    }
                                    Log.i("BwfSurveyAmplifyApp", "DataStore is queried for configs. No of configs is "+configsInner.size() );
                                    if(configsInner.size()>0){
                                        BwfSurveyAmplifyApplication.configs = configsInner;
                                        BwfSurveyAmplifyApplication.interchangePool = BwfSurveyAmplifyApplication.makeAllInterchanges();
                                        //get the local data store app version
                                        int localStoreAppVersion = BwfSurveyAmplifyApplication.getAPPVERSION();
                                        Log.i("BwfSurveyAmplifyApp", "remoteAppVersion: "+ remoteAppVersion );
                                        Log.i("BwfSurveyAmplifyApp", "localStoreAppVersion: "+ localStoreAppVersion );
                                        if(remoteAppVersion>localStoreAppVersion){
                                            //call api to get you all configs because we do not have the configs locally
                                            Log.i("BwfSurveyAmplifyApp", "remoteAppVersion greater ");
                                            Log.i("BwfSurveyAmplifyApp", "remoteAppVersion: "+ remoteAppVersion );
                                            Log.i("BwfSurveyAmplifyApp", "localStoreAppVersion: "+ localStoreAppVersion );
                                            Amplify.API.query(
                                                    ModelQuery.list(ConfigDefinitions.class),
                                                    responseinner -> {
                                                        if (responseinner.hasData()) {
                                                            for (ConfigDefinitions configDefFromRemote : responseinner.getData()) {
                                                                //check before saving
                                                                //find the config here in configsInner
                                                                boolean found = false;
                                                                for(ConfigDefinitions configFromLocalStore : configsInnerFromLocalStore){
                                                                    if(configDefFromRemote.getId().contentEquals(configFromLocalStore.getId())){
                                                                        found = true;
                                                                        //check if all things are ok
                                                                        if(!configDefFromRemote.equals(configFromLocalStore)){
                                                                            Amplify.DataStore.save(configDefFromRemote,
                                                                                    updateDS -> Log.i("BwfSurveyAmplifyApp", "Manaul Config Saved Successfully "),
                                                                                    failureDS -> Log.e("BwfSurveyAmplifyApp", "Manaul Config Save Failed "+ failureDS.getMessage())
                                                                            );
                                                                        }
                                                                    }
                                                                }
                                                                if(!found){
                                                                    Amplify.DataStore.save(configDefFromRemote,
                                                                            updateDS -> Log.i("BwfSurveyAmplifyApp", "Manaul Config Saved Successfully "),
                                                                            failureDS -> Log.e("BwfSurveyAmplifyApp", "Manaul Config Save Failed "+ failureDS.getMessage())
                                                                    );
                                                                }
                                                            }
                                                        }
                                                        //everything looks ok we can show the menu
                                                        runOnUiThread(() -> showMenu());
                                                    },
                                                    failureinner ->{
                                                        //we failed when trying to pull down the remote configs
                                                        runOnUiThread(() -> showMenu());
                                                        Log.e("BwfSurveyAmplifyApp", "Manaul Fetch list "+ failureinner.getMessage());
                                                    }
                                            );
                                        }
                                        else{
                                            //everything looks ok we can show the menu
                                            runOnUiThread(() -> showMenu());
                                            Log.i("BwfSurveyAmplifyApp", "remoteAppVersion equal or less ");
                                            Log.i("BwfSurveyAmplifyApp", "remoteAppVersion: "+ remoteAppVersion );
                                            Log.i("BwfSurveyAmplifyApp", "localStoreAppVersion: "+ localStoreAppVersion );
                                        }

                                    }
                                    else{
                                        //call api to get you all configs because we do not have the configs locally
                                        Amplify.API.query(
                                                ModelQuery.list(ConfigDefinitions.class),
                                                responseinner -> {
                                                    if (responseinner.hasData()) {
                                                        for (ConfigDefinitions configDefFromRemote : responseinner.getData()) {
                                                            Amplify.DataStore.save(configDefFromRemote,
                                                                    updateDS -> Log.i("BwfSurveyAmplifyApp", "Manaul Config Saved Successfully "),
                                                                    failureDS -> Log.e("BwfSurveyAmplifyApp", "Manaul Config Save Failed "+ failureDS.getMessage())
                                                            );
                                                        }
                                                    }
                                                    runOnUiThread(() -> showMenu());
                                                },
                                                failureinner ->{
                                                    runOnUiThread(() -> showMenu());
                                                    Log.e("BwfSurveyAmplifyApp", "Manaul Fetch list "+ failureinner.getMessage());
                                                }
                                        );
                                    }

                                },
                                failure ->{
                                    Log.i("Tutorials", "Query failed, going to use file " );
                                    Log.e("Tutorials", "Query failed.", failure);
                                    //we need to do sync now because when we tried to query we failed, maybe ConfigDefinitions
                                    //has not even been created
                                    Amplify.API.query(
                                        ModelQuery.list(ConfigDefinitions.class),
                                        responseinner -> {
                                            if (responseinner.hasData()) {
                                                for (ConfigDefinitions configDefFromRemote : responseinner.getData()) {
                                                    Amplify.DataStore.save(configDefFromRemote,
                                                            updateDS -> Log.i("BwfSurveyAmplifyApp", "Manaul Config Saved Successfully "),
                                                            failureDS -> Log.e("BwfSurveyAmplifyApp", "Manaul Config Save Failed "+ failureDS.getMessage())
                                                    );
                                                }
                                            }
                                            runOnUiThread(() -> showMenu());
                                        },
                                        failureinner ->{
                                            runOnUiThread(() -> showMenu());
                                            Log.e("BwfSurveyAmplifyApp", "Manaul Fetch list "+ failureinner.getMessage());
                                        }
                                    );
                                }
                        );
                    }
                    else{
                        runOnUiThread(() -> showMenu());
                    }
                },
                error -> runOnUiThread(() -> {
                    if(progressBarText != null)
                        progressBarText.setText("Please note that you are offline");
                    showMenu();
                })
        );

    }

    public void showMenu(){

        //if(calledAMPStart){
            //wait for sync to finish, because Authenication called start
            startProgress("Please wait... Setting Up!");
            CountDownTimer countDownTimer = new CountDownTimer(BwfSurveyAmplifyApplication.manualTimer,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    Log.i("Tutorials", "endProgress in showMenu" );
                    progressBar.setVisibility(View.GONE);
                    endProgress();
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    initView();
                }
            };
            countDownTimer.start();

        //}else
          //  initView();
    }

    private void initView() {

        //create menu items
        Button initialFullSurvey = findViewById(R.id.button_initialFullSurvey);
        initialFullSurvey.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), CommunityCardSelectActivity.class);
            i.putExtra("NAME_BWE", namebwe);
            i.putExtra("COUNTRY_BWE", countrybwe);
            i.putExtra("POSITION_BWE", positionbwe);
            i.putExtra("SURVEY_TYPE","INITIALSURVEY");
            i.putExtra("LAT",lat);
            i.putExtra("LNG",lng);
            startActivity(i);
        });

        Button followUpSurvey = findViewById(R.id.button_followUpSurvey);
        followUpSurvey.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), HouseholdCardSelectActivity.class);
            i.putExtra("NAME_BWE", namebwe);
            i.putExtra("SURVEY_TYPE","FOLLOWUPSURVEY");
            i.putExtra("OPERATION","CREATE");
            i.putExtra("LAT",lat);
            i.putExtra("LNG",lng);
            startActivity(i);
        });

        Button healthCheckSurvey = findViewById(R.id.button_healthCheck);
        healthCheckSurvey.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), HouseholdCardSelectActivity.class);
            i.putExtra("NAME_BWE", namebwe);
            i.putExtra("SURVEY_TYPE","HEALTHCHECKSURVEY");
            i.putExtra("OPERATION","CREATE");
            i.putExtra("LAT",lat);
            i.putExtra("LNG",lng);
            startActivity(i);
        });

        Button sweMonthlySummary = findViewById(R.id.button_SWEMonthlySummary);
        sweMonthlySummary.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), SubMenuMonthlySummaryActivity.class);
            i.putExtra("NAME_BWE", namebwe);
            i.putExtra("COUNTRY_BWE", countrybwe);
            i.putExtra("POSITION_BWE", positionbwe);
            i.putExtra("SURVEY_TYPE","SWESUMMARY");
            i.putExtra("OPERATION","CREATE");
            i.putExtra("LAT",lat);
            i.putExtra("LNG",lng);
            startActivity(i);
        });

        Button waterSurvey = findViewById(R.id.button_waterSurvey);
        waterSurvey.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), HouseholdCardSelectActivity.class);
            i.putExtra("NAME_BWE", namebwe);
            i.putExtra("COUNTRY_BWE", countrybwe);
            i.putExtra("POSITION_BWE", positionbwe);
            i.putExtra("SURVEY_TYPE","WATERSURVEYHOUSEHOLD");
            i.putExtra("OPERATION","CREATE");
            i.putExtra("LAT",lat);
            i.putExtra("LNG",lng);
            startActivity(i);
        });

        Button waterSurveyComm = findViewById(R.id.button_waterSurveyComm);
        waterSurveyComm.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), CommunityWaterCardSelectActivity.class);
            i.putExtra("NAME_BWE", namebwe);
            i.putExtra("COUNTRY_BWE", countrybwe);
            i.putExtra("POSITION_BWE", positionbwe);
            i.putExtra("OPERATION", "CREATE");
            i.putExtra("SURVEY_TYPE","WATERSURVEYCOMMUNITY");
            i.putExtra("LAT",lat);
            i.putExtra("LNG",lng);
            startActivity(i);
        });


        Button waterSurveyFromVol = findViewById(R.id.button_waterSurveyFromVol);
        waterSurveyFromVol.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), VolunteerHouseholdCardSelectActivity.class);
            i.putExtra("NAME_BWE", namebwe);
            i.putExtra("COUNTRY_BWE", countrybwe);
            i.putExtra("POSITION_BWE", positionbwe);
            i.putExtra("SURVEY_TYPE","WATERSURVEYHOUSEHOLD");
            i.putExtra("OPERATION","CREATE");
            i.putExtra("LAT",lat);
            i.putExtra("LNG",lng);
            startActivity(i);
        });

        //view menu items
        Button initialFullSurveyView = findViewById(R.id.button_initialFullSurveyView);
        initialFullSurveyView.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), HouseholdCardSelectActivity.class);
            i.putExtra("OPERATION","VIEW");
            startActivity(i);
        });

        Button followUpSurveyView = findViewById(R.id.button_followUpSurveyView);
        followUpSurveyView.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), FollowUpSurveyCardSelectActivity.class);
            i.putExtra("OPERATION","VIEW");
            startActivity(i);
        });

        Button waterSurveyCommView = findViewById(R.id.button_waterSurveyCommView);
        waterSurveyCommView.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), CommunityWaterTestCardSelectActivity.class);
            i.putExtra("OPERATION","VIEW");
            startActivity(i);
        });

        Button waterSurveyView = findViewById(R.id.button_waterSurveyView);
        waterSurveyView.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), HouseholdWaterTestCardSelectActivity.class);
            i.putExtra("OPERATION","VIEW");
            startActivity(i);
        });

        Button waterSurveyVolView = findViewById(R.id.button_waterSurveyVolView);
        waterSurveyVolView.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), VolunteerHouseholdWaterTestCardSelectActivity.class);
            i.putExtra("OPERATION","VIEW");
            startActivity(i);
        });

        Button healthCheckView = findViewById(R.id.button_healthCheckView);
        healthCheckView.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), HealthCheckSurveyCardSelectActivity.class);
            i.putExtra("OPERATION","VIEW");
            startActivity(i);
        });

        Button sweMonthlySummaryView = findViewById(R.id.button_SWEMonthlySummaryView);
        sweMonthlySummaryView.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ViewSubMenuMonthlySummaryActivity.class);
            i.putExtra("OPERATION","VIEW");
            i.putExtra("LAT",lat);
            i.putExtra("LNG",lng);
            startActivity(i);
        });

        //update incompleted menu items
        Button initialFullSurveyUC = findViewById(R.id.button_initialFullSurveyUC);
        initialFullSurveyUC.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), HouseholdCardSelectActivity.class);
            i.putExtra("OPERATION","UPDATE");
            startActivity(i);
        });

        Button followUpSurveyUpdate = findViewById(R.id.button_followUpSurveyUC);
        followUpSurveyUpdate.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), FollowUpSurveyCardSelectActivity.class);
            i.putExtra("OPERATION","UPDATE");
            startActivity(i);
        });

        Button waterSurveyCommUC = findViewById(R.id.button_waterSurveyCommUC);
        waterSurveyCommUC.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), CommunityWaterTestCardSelectActivity.class);
            i.putExtra("OPERATION","UPDATE");
            startActivity(i);
        });

        Button waterSurveyUC = findViewById(R.id.button_waterSurveyUC);
        waterSurveyUC.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), HouseholdWaterTestCardSelectActivity.class);
            i.putExtra("OPERATION","UPDATE");
            startActivity(i);
        });

        Button waterSurveyFromVolUC = findViewById(R.id.button_waterSurveyFromVolUC);
        waterSurveyFromVolUC.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), VolunteerHouseholdWaterTestCardSelectActivity.class);
            i.putExtra("OPERATION","UPDATE");
            startActivity(i);
        });

        Button healthCheckUC = findViewById(R.id.button_healthCheckUC);
        healthCheckUC.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), HealthCheckSurveyCardSelectActivity.class);
            i.putExtra("OPERATION","UPDATE");
            startActivity(i);
        });

        Button sweMonthlySummaryUC = findViewById(R.id.button_SWEMonthlySummaryUC);
        sweMonthlySummaryUC.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), UpdateSubMenuMonthlySummaryActivity.class);
            i.putExtra("OPERATION","UPDATE");
            startActivity(i);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //doProgress();
    }

    private void startProgress(String s) {
        runOnUiThread(() -> {
            if(progressBar==null)
                progressBar = findViewById(R.id.llProgressBar);
            if(progressBarText==null)
                progressBarText = findViewById(R.id.pbText);

            progressBarText.setText(s);
            progressBar.setVisibility(View.VISIBLE);
        });
    }

    private void endProgress() {
        runOnUiThread(() -> {
            if(progressBar==null)
                progressBar = findViewById(R.id.llProgressBar);

            progressBar.setVisibility(View.GONE);
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
            ArrayList<String> listOfCountries = BwfSurveyAmplifyApplication.getCountries();
            showSelectCountry(listOfCountries);
            /*DialogFragment dialog = new SelectCountryDialogFragment(listOfCountries, countryName -> {
                countrybwe = countryName;
                Log.i("Tutorials", "country selected is " + countrybwe );
            });
            dialog.show(getSupportFragmentManager(), "countries");*/
        }

        return super.onOptionsItemSelected(item);
    }

    private DialogFragment selectCountry;
    public void showSelectCountry(ArrayList<String> listOfCountries) {
        selectCountry = SelectCountryDialogFragment.newInstance(listOfCountries);
        selectCountry.show(getSupportFragmentManager(), "selectCountry");
        selectCountry.setCancelable(false);
    }

    @Override
    public void onSelectedCountry(String countryName) {
        countrybwe = countryName;
    }
}
