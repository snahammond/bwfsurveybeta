package com.bwfsurvey.bwfsurveybeta.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.HealthCheckSurvey;
import com.bwfsurvey.bwfsurveybeta.types.Interchange;
import com.bwfsurvey.bwfsurveybeta.adapters.InterchangeCardAdapter;
import com.bwfsurvey.bwfsurveybeta.MyAmplifyApplication;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class HealthCheckSurveyActivity extends AppCompatActivity {

    private String namebwe = null;
    private String surveyType = null;
    private String country = null;
    private String community = null;
    private String householdName = null;
    private int surveyId = 0;

    private static ArrayList<Interchange> interchanges;
    private RecyclerView recyclerView;
    private InterchangeCardAdapter adapter;

    private LinearLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("NAME_BWE")!=null)
            namebwe = getIntent().getStringExtra("NAME_BWE");
        if(getIntent().getStringExtra("SURVEY_TYPE")!=null)
            surveyType = getIntent().getStringExtra("SURVEY_TYPE");
        if(getIntent().getStringExtra("COUNTRY")!=null)
            country = getIntent().getStringExtra("COUNTRY");
        if(getIntent().getStringExtra("COMMUNITY")!=null)
            community = getIntent().getStringExtra("COMMUNITY");
        if(getIntent().getStringExtra("HHNAME")!=null)
            householdName = getIntent().getStringExtra("HHNAME");

        if(getIntent().getStringExtra("SURVEY_ID")!=null){
            String surveyIdStr = getIntent().getStringExtra("SURVEY_ID");
            surveyId = Integer.parseInt(surveyIdStr);
        }
        Log.i("Tutorials", "Selected family follow up survey class: " + surveyId);
        setContentView(R.layout.activity_recycler);
        getSupportActionBar().setTitle((CharSequence) "Health Check Survey; "+householdName);
        initView();
    }

    private void initView() {
        createHealthCheckSurveyQuestionaire();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InterchangeCardAdapter(HealthCheckSurveyActivity.this, HealthCheckSurveyActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }

    private void createHealthCheckSurveyQuestionaire() {
        try{
            ArrayList<Interchange> returnedInterchanges = MyAmplifyApplication.getInterchanges(surveyType);
            if(returnedInterchanges!=null){
                HealthCheckSurveyActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    interchange.setPositionOnRecyler(positionOnRecyler);
                    HealthCheckSurveyActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
                Collections.sort(HealthCheckSurveyActivity.interchanges);
            }
        }catch (Exception c){
            Log.i("Tutorial", "we cannot get list of interchanges " );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save) {
            ArrayList<Interchange> interchangesWithUserAns = adapter.retrieveData();

            //we have to validate now
            ArrayList<Interchange> invalideInterchanges = validateUserAns(interchangesWithUserAns);
            Log.i("Tutorial", "how many invalid interfaces: " + invalideInterchanges.size());


            if(invalideInterchanges.size()>0){
                showInvalidSurveyAlert();
            }else{

                //make an InitialSurvey object
                HealthCheckSurvey HealthCheckSurveyToSave = makeHealthCheckSurveyObject(interchangesWithUserAns,0,"","");

                //save the initialSurvey object
                saveHealthCheckSurvey(HealthCheckSurveyToSave);

            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveHealthCheckSurvey(HealthCheckSurvey healthCheckSurveyToSave) {
        Amplify.DataStore.save(
                healthCheckSurveyToSave,
                update -> {
                    Log.i("Tutorial", "Saved Successfully ");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            doSyncWaitAndShowSavedSuccessfulAlert();
                        }
                    });
                },
                failure -> {
                    Log.i("Tutorial", "Save Failed ");
                    showSaveFailedAlert();
                }
        );
    }

    private void doSyncWaitAndShowSavedSuccessfulAlert() {
        //show progress bar so that if user is offline, the save will go into pending to be shot into cloud
        //this is for the progress bar
        progressBar = (LinearLayout) findViewById(R.id.llProgressBar);
        TextView progressBarText = (TextView) findViewById(R.id.pbText);
        progressBarText.setText("Please wait... Syncing Up!");
        progressBar.setVisibility(View.VISIBLE);
        CountDownTimer countDownTimer = new CountDownTimer(16000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                progressBar.setVisibility(View.GONE);
                showSavedSuccessfulAlert();
            }
        };
        countDownTimer.start();
    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(HealthCheckSurveyActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Health Check Survey Save Failed, Please try again\n" )
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                        .setCanceledOnTouchOutside(false);
            }
        });
    }

    private void showSavedSuccessfulAlert(){
        new AlertDialog.Builder(HealthCheckSurveyActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Health Check Survey Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all interchange answers
                        for(Interchange interchange: HealthCheckSurveyActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        HealthCheckSurveyActivity.this.finish();

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);

    }

    private HealthCheckSurvey makeHealthCheckSurveyObject(ArrayList<Interchange> validatedInterchangesWithAns,int completed, String lat, String lng){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = namebwe;
        String Country = (String) country;
        String Community = (String) community;
        Integer SurveyId = surveyId;
        Temporal.Date date = new Temporal.Date(date_s);
        String HeadHouseholdName = (String) householdName;
        String PersonBeingInterviewed = (String) getInterchangeAns("PersonBeingInterviewed",validatedInterchangesWithAns);
        String WasteDisposalYoungestChild = (String) getInterchangeAns("WasteDisposalYoungestChild",validatedInterchangesWithAns);
        String WashedHandsIn24Hours = (String) getInterchangeAns("WashedHandsIn24Hours",validatedInterchangesWithAns);
        String WhenWashedHandsIn24Hours = (String) getInterchangeAns("WhenWashedHandsIn24Hours",validatedInterchangesWithAns);
        String WhatUsedToWashYourHands = (String) getInterchangeAns("WhatUsedToWashYourHands",validatedInterchangesWithAns);
        Integer NoChildrenWithVomitingOrDiarrheaIn14days = parseIntegerWithDefault(getInterchangeAns("NoChildrenWithVomitingOrDiarrheaIn14days",validatedInterchangesWithAns),0);
        Integer NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek = parseIntegerWithDefault(getInterchangeAns("NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek",validatedInterchangesWithAns),0);
        Integer NoChildrenWithVomitingOrDiarrheaIn7days = parseIntegerWithDefault(getInterchangeAns("NoChildrenWithVomitingOrDiarrheaIn7days",validatedInterchangesWithAns),0);
        String DidSickChildrenGoToHospital = (String) getInterchangeAns("DidSickChildrenGoToHospital",validatedInterchangesWithAns);
        String DidSickChildrenGoToHospitalYes = (String) getInterchangeAns("DidSickChildrenGoToHospitalYes",validatedInterchangesWithAns);
        String SickChildrenBreastfeeding = (String) getInterchangeAns("SickChildrenBreastfeeding",validatedInterchangesWithAns);
        String OutcomeMostRecentVomiting_DiarrheaAtHospital = (String) getInterchangeAns("OutcomeMostRecentVomiting_DiarrheaAtHospital",validatedInterchangesWithAns);
        Integer NoDaysNoWorkBecauseOfOwnIllness = parseIntegerWithDefault(getInterchangeAns("NoDaysNoWorkBecauseOfOwnIllness",validatedInterchangesWithAns),0);
        Integer NoDaysNoWorkBecauseOfIllnessFamilyMembers = parseIntegerWithDefault(getInterchangeAns("NoDaysNoWorkBecauseOfIllnessFamilyMembers",validatedInterchangesWithAns),0);
        Integer MoneySpentMedicalTreatmentLast4weeks = parseIntegerWithDefault(getInterchangeAns("MoneySpentMedicalTreatmentLast4weeks",validatedInterchangesWithAns),0);
        String WaterTreatment24Hours = (String) getInterchangeAns("WaterTreatment24Hours",validatedInterchangesWithAns);
        String MainReasonNoWaterTreatment24Hour = (String) getInterchangeAns("MainReasonNoWaterTreatment24Hour",validatedInterchangesWithAns);
        String WaterTreatment24HourMethod = (String) getInterchangeAns("WaterTreatment24HourMethod",validatedInterchangesWithAns);

        HealthCheckSurvey healthCheckSurvey = HealthCheckSurvey.builder()
                .namebwe(Namebwe)
                .country(Country)
                .community(Community)
                .surveyId(SurveyId)
                .headHouseholdName(HeadHouseholdName)
                .personBeingInterviewed(PersonBeingInterviewed)
                .wasteDisposalYoungestChild(WasteDisposalYoungestChild)
                .washedHandsIn24Hours(WashedHandsIn24Hours)
                .whenWashedHandsIn24Hours(WhenWashedHandsIn24Hours)
                .whatUsedToWashYourHands(WhatUsedToWashYourHands)
                .noChildrenWithVomitingOrDiarrheaIn14days(NoChildrenWithVomitingOrDiarrheaIn14days)
                .noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek(NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek)
                .noChildrenWithVomitingOrDiarrheaIn7days(NoChildrenWithVomitingOrDiarrheaIn7days)
                .didSickChildrenGoToHospital(DidSickChildrenGoToHospital)
                .didSickChildrenGoToHospitalYes(DidSickChildrenGoToHospitalYes)
                .sickChildrenBreastfeeding(SickChildrenBreastfeeding)
                .outcomeMostRecentVomitingDiarrheaAtHospital(OutcomeMostRecentVomiting_DiarrheaAtHospital)
                .noDaysNoWorkBecauseOfOwnIllness(NoDaysNoWorkBecauseOfOwnIllness)
                .noDaysNoWorkBecauseOfIllnessFamilyMembers(NoDaysNoWorkBecauseOfIllnessFamilyMembers)
                .moneySpentMedicalTreatmentLast4weeks(MoneySpentMedicalTreatmentLast4weeks)
                .waterTreatment24Hours(WaterTreatment24Hours)
                .mainReasonNoWaterTreatment24Hour(MainReasonNoWaterTreatment24Hour)
                .waterTreatment24HourMethod(WaterTreatment24HourMethod)
                .completed(completed)
                .lat(lat)
                .lng(lng)
                .date(date)
                .build();
        return healthCheckSurvey;

    }

    private Object getInterchangeAns(String interchangeName,ArrayList<Interchange> validatedInterchangesWithAns){
        Object ans = null;
        Interchange foundInterchange = null;
        for(Interchange interchange : validatedInterchangesWithAns){
            if(interchange.getName().contentEquals(interchangeName)){
                ans = interchange.getAnswer().getAns();
                foundInterchange = interchange;
            }
        }
        if(ans==null){
            ans = foundInterchange.getValidation().getDefaultValue();
        }
        return ans;
    }

    private void showInvalidSurveyAlert(){
        new AlertDialog.Builder(HealthCheckSurveyActivity.this)
                .setTitle("Invalid Questions")
                .setMessage("Some questions have not been correctly answered \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    //this function will return a list of invalid interchanges
    private ArrayList<Interchange> validateUserAns(ArrayList<Interchange> interchangesWithUserAns) {
        Log.i("Tutorial", "we are now validating " );
        ArrayList<Interchange> invalidinterchange = new ArrayList<>();
        for(Interchange interchange: interchangesWithUserAns){
            //check for its validation
            //Log.i("Tutorial", "interchange: "+interchange.getValidation().getName() +" mandatory: "+interchange.getValidation().isMandatory() + "user answer: "+interchange.getAnswer().getAns());
            if(!interchange.isValid()){
                invalidinterchange.add(interchange);
                Log.i("Tutorial", "invalid interchange: "+interchange.getValidation().getName() +" mandatory: "+interchange.getValidation().isMandatory() + " default value: "+interchange.getValidation().getDefaultValue() + "user answer: "+interchange.getAnswer().getAns());
            }
        }
        return invalidinterchange;
    }

    public static int parseIntegerWithDefault(Object s, int defaultVal) {
        if (s instanceof Integer) {
            return (Integer) s;
        }else if (s instanceof String){
            String str = (String) s;
            return str.matches("-?\\d+") ? Integer.parseInt(str): defaultVal;
        }else
            return defaultVal;

    }
}
