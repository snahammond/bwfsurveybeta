package com.bwfsurvey.bwfsurveybeta.activities.collect;

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
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.generated.model.HealthCheckSurvey;
import com.amplifyframework.datastore.syncengine.OutboxMutationEvent;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.SubscriptionToken;
import com.bwfsurvey.bwfsurveybeta.types.Interchange;
import com.bwfsurvey.bwfsurveybeta.adapters.InterchangeCardAdapter;
import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.utils.IntegerUtils;
import com.bwfsurvey.bwfsurveybeta.utils.InterchangeUtils;
import com.bwfsurvey.bwfsurveybeta.utils.PhoneLocation;
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
    private String lat = null;
    private String lng = null;

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

        if(getIntent().getStringExtra("LAT")!=null)
            lat = getIntent().getStringExtra("LAT");
        if(getIntent().getStringExtra("LNG")!=null)
            lng = getIntent().getStringExtra("LNG");

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
            ArrayList<Interchange> returnedInterchanges = BwfSurveyAmplifyApplication.getInterchanges(surveyType);
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
            ArrayList<Interchange> invalideInterchanges = InterchangeUtils.validateUserAns(interchangesWithUserAns);
            Log.i("Tutorial", "how many invalid interfaces: " + invalideInterchanges.size());


            if(invalideInterchanges.size()>0){
                InterchangeUtils.showInvalidSurveyAlert(invalideInterchanges,HealthCheckSurveyActivity.this);
            }else{
                String lat_ = "";
                String lng_ = "";
                if(lat!=null&&lng!=null){
                    lat_= lat;
                    lng_ = lng;
                }else{
                    //try and get it again
                    PhoneLocation phoneLocation = new PhoneLocation(HealthCheckSurveyActivity.this);
                    String[] arraylatlng = phoneLocation.getLocation();
                    if(arraylatlng!=null){
                        lat_ = arraylatlng[0];
                        lng_ = arraylatlng[1];
                    }
                }
                //make an InitialSurvey object
                HealthCheckSurvey HealthCheckSurveyToSave = makeHealthCheckSurveyObject(interchangesWithUserAns,1,lat_,lng_);

                //save the initialSurvey object
                saveHealthCheckSurvey(HealthCheckSurveyToSave);

            }
        }

        if (id == R.id.suspend) {
            ArrayList<Interchange> interchangesWithUserAns = adapter.retrieveData();

            String lat_ = "";
            String lng_ = "";
            if(lat!=null&&lng!=null){
                lat_= lat;
                lng_ = lng;
            }else{
                //try and get it again
                PhoneLocation phoneLocation = new PhoneLocation(HealthCheckSurveyActivity.this);
                String[] arraylatlng = phoneLocation.getLocation();
                if(arraylatlng!=null){
                    lat_ = arraylatlng[0];
                    lng_ = arraylatlng[1];
                }
            }
            //make an InitialSurvey object
            HealthCheckSurvey HealthCheckSurveyToSave = makeHealthCheckSurveyObject(interchangesWithUserAns,0,lat_,lng_);

            //save the initialSurvey object
            saveHealthCheckSurvey(HealthCheckSurveyToSave);


        }
        return super.onOptionsItemSelected(item);
    }

    SubscriptionToken checkToken = null;
    private void saveHealthCheckSurvey(HealthCheckSurvey healthCheckSurveyToSave) {
        checkToken = Amplify.Hub.subscribe(
                HubChannel.DATASTORE,
                hubEvent -> DataStoreChannelEventName.OUTBOX_MUTATION_ENQUEUED.toString().equals(hubEvent.getName()),
                hubEvent -> {
                    OutboxMutationEvent event = (OutboxMutationEvent) hubEvent.getData();
                    if(event.getModelName().contentEquals("HealthCheckSurvey")){
                        if(event.getElement().getModel().equals(healthCheckSurveyToSave)){
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    progressBar.setVisibility(View.GONE);
                                    showSavedSuccessfulAlert();
                                }
                            });
                        }
                    }
                }
        );
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
        /*
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
         */
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
        if(checkToken!=null)
            Amplify.Hub.unsubscribe(checkToken);
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
        String PersonBeingInterviewed = (String) InterchangeUtils.getInterchangeAns("PersonBeingInterviewed",validatedInterchangesWithAns);
        String WasteDisposalYoungestChild = (String) InterchangeUtils.getInterchangeAns("WasteDisposalYoungestChild",validatedInterchangesWithAns);
        String WashedHandsIn24Hours = (String) InterchangeUtils.getInterchangeAns("WashedHandsIn24Hours",validatedInterchangesWithAns);
        String WhenWashedHandsIn24Hours = (String) InterchangeUtils.getInterchangeAns("WhenWashedHandsIn24Hours",validatedInterchangesWithAns);
        String WhatUsedToWashYourHands = (String) InterchangeUtils.getInterchangeAns("WhatUsedToWashYourHands",validatedInterchangesWithAns);
        Integer NoChildrenWithVomitingOrDiarrheaIn14days = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoChildrenWithVomitingOrDiarrheaIn14days",validatedInterchangesWithAns),0);
        Integer NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek",validatedInterchangesWithAns),0);
        Integer NoChildrenWithVomitingOrDiarrheaIn7days = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoChildrenWithVomitingOrDiarrheaIn7days",validatedInterchangesWithAns),0);
        String DidSickChildrenGoToHospital = (String) InterchangeUtils.getInterchangeAns("DidSickChildrenGoToHospital",validatedInterchangesWithAns);
        String DidSickChildrenGoToHospitalYes = (String) InterchangeUtils.getInterchangeAns("DidSickChildrenGoToHospitalYes",validatedInterchangesWithAns);
        String SickChildrenBreastfeeding = (String) InterchangeUtils.getInterchangeAns("SickChildrenBreastfeeding",validatedInterchangesWithAns);
        String OutcomeMostRecentVomiting_DiarrheaAtHospital = (String) InterchangeUtils.getInterchangeAns("OutcomeMostRecentVomiting_DiarrheaAtHospital",validatedInterchangesWithAns);
        Integer NoDaysNoWorkBecauseOfOwnIllness = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoDaysNoWorkBecauseOfOwnIllness",validatedInterchangesWithAns),0);
        Integer NoDaysNoWorkBecauseOfIllnessFamilyMembers = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoDaysNoWorkBecauseOfIllnessFamilyMembers",validatedInterchangesWithAns),0);
        Integer MoneySpentMedicalTreatmentLast4weeks = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("MoneySpentMedicalTreatmentLast4weeks",validatedInterchangesWithAns),0);
        String WaterTreatment24Hours = (String) InterchangeUtils.getInterchangeAns("WaterTreatment24Hours",validatedInterchangesWithAns);
        String MainReasonNoWaterTreatment24Hour = (String) InterchangeUtils.getInterchangeAns("MainReasonNoWaterTreatment24Hour",validatedInterchangesWithAns);
        String WaterTreatment24HourMethod = (String) InterchangeUtils.getInterchangeAns("WaterTreatment24HourMethod",validatedInterchangesWithAns);

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
}
