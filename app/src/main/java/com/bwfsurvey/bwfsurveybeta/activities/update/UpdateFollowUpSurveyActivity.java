package com.bwfsurvey.bwfsurveybeta.activities.update;

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
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.FollowUpSurvey;
import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.InterchangeCardAdapter;
import com.bwfsurvey.bwfsurveybeta.types.Interchange;
import com.bwfsurvey.bwfsurveybeta.utils.PhoneLocation;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class UpdateFollowUpSurveyActivity extends AppCompatActivity {
    FollowUpSurvey theFollowUpSurvey;
    private String uuidFollowUpSurvey;
    private RecyclerView recyclerView;
    private InterchangeCardAdapter adapter;

    private static ArrayList<Interchange> interchanges;
    private LinearLayout progressBar;

    private String lat = null;
    private String lng = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().getStringExtra("UUID")!=null)
            uuidFollowUpSurvey = getIntent().getStringExtra("UUID");

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createFollowUpSurveyUpdateInterchanges();
    }

    private void createFollowUpSurveyUpdateInterchanges() {
        interchanges = new ArrayList<>();
        createInterchangesAndShowOnRecyclerView();
    }

    private void createInterchangesAndShowOnRecyclerView() {
        Amplify.DataStore.query(
                FollowUpSurvey.class,
                Where.matches(FollowUpSurvey.ID.eq(uuidFollowUpSurvey)),
                initialSurvey -> {
                    Log.i("Tutorials", "DataStore is queried.");
                    while (initialSurvey.hasNext()) {
                        theFollowUpSurvey = initialSurvey.next();
                        Log.i("Tutorials", "DataStore is queried. theFollowUpSurvey " +theFollowUpSurvey.getId());
                    }

                    runOnUiThread(new Runnable() {
                        public void run() {
                            createInterchangesAndShow();
                        }
                    });
                },
                failure ->{
                    Log.e("Tutorials", "Query failed.", failure);
                }
        );
    }

    private void createInterchangesAndShow() {
        if(theFollowUpSurvey!=null){
            ArrayList<Interchange> returnedInterchanges = BwfSurveyAmplifyApplication.getInterchanges("FOLLOWUPSURVEY");
            if(returnedInterchanges!=null){
                UpdateFollowUpSurveyActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    //set answers
                    String answer = "";
                    String nameOfAns = interchange.getAnswer().getAnswerDef().getName();
                    String methodName = "get"+nameOfAns;
                    java.lang.reflect.Method method;
                    try {
                        method = theFollowUpSurvey.getClass().getMethod(methodName);
                        Object ansObject = method.invoke(theFollowUpSurvey);
                        answer = ansObject.toString();
                    } catch (Exception e) {
                        Log.e("Tutorials", "Could not get answer " + nameOfAns);
                    }
                    interchange.getAnswer().setAns(answer);

                    interchange.setPositionOnRecyler(positionOnRecyler);
                    UpdateFollowUpSurveyActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
                //sort the interchanges
                Collections.sort(UpdateFollowUpSurveyActivity.interchanges);
                showViewOnlyInterchanges();
            }
        }
    }

    private void showViewOnlyInterchanges() {
        //wait a lil bit so that if we are offline things will settle
        //this is for the progress bar
        progressBar = (LinearLayout) findViewById(R.id.llProgressBar);
        TextView progressBarText = (TextView) findViewById(R.id.pbText);
        progressBarText.setText("Please wait... Getting records!");
        progressBar.setVisibility(View.VISIBLE);
        CountDownTimer countDownTimer = new CountDownTimer(BwfSurveyAmplifyApplication.manualTimer,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                progressBar.setVisibility(View.GONE);
                initViewElements();
            }
        };
        countDownTimer.start();
    }

    private void initViewElements() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InterchangeCardAdapter(UpdateFollowUpSurveyActivity.this, UpdateFollowUpSurveyActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for(Interchange interchange: UpdateFollowUpSurveyActivity.interchanges){
            interchange.getAnswer().setAns(null);
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
                String lat_ = "";
                String lng_ = "";
                if(lat!=null&&lng!=null){
                    lat_= lat;
                    lng_ = lng;
                }else{
                    //try and get it again
                    PhoneLocation phoneLocation = new PhoneLocation(UpdateFollowUpSurveyActivity.this);
                    String[] arraylatlng = phoneLocation.getLocation();
                    if(arraylatlng!=null){
                        lat_ = arraylatlng[0];
                        lng_ = arraylatlng[1];
                    }
                }
                //make an InitialSurvey object
                FollowUpSurvey followUpSurveyToSave = makeFollowUpSurveyObjectWithId(interchangesWithUserAns,1,lat_,lng_);

                //save the initialSurvey object
                saveFollowUpSurvey(followUpSurveyToSave);
            }
        }

        if(id== R.id.suspend){
            ArrayList<Interchange> interchangesWithUserAns = adapter.retrieveData();
            String lat_ = "";
            String lng_ = "";
            if(lat!=null&&lng!=null){
                lat_= lat;
                lng_ = lng;
            }else{
                //try and get it again
                PhoneLocation phoneLocation = new PhoneLocation(UpdateFollowUpSurveyActivity.this);
                String[] arraylatlng = phoneLocation.getLocation();
                if(arraylatlng!=null){
                    lat_ = arraylatlng[0];
                    lng_ = arraylatlng[1];
                }
            }
            //make an follow Up survey object
            FollowUpSurvey followUpSurveyToSave = makeFollowUpSurveyObjectWithId(interchangesWithUserAns,0,lat_,lng_);

            //save the follow Up survey object
            saveFollowUpSurvey(followUpSurveyToSave);

        }
        return super.onOptionsItemSelected(item);
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

    private void showInvalidSurveyAlert(){
        new AlertDialog.Builder(UpdateFollowUpSurveyActivity.this)
                .setTitle("Invalid Questions")
                .setMessage("Some questions have not been correctly answered \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private FollowUpSurvey makeFollowUpSurveyObjectWithId(ArrayList<Interchange> validatedInterchangesWithAns,int completed, String lat, String lng){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = theFollowUpSurvey.getNamebwe();
        String Country = theFollowUpSurvey.getCountry();
        String Community = theFollowUpSurvey.getCommunity();
        Integer SurveyId = theFollowUpSurvey.getSurveyId();
        Temporal.Date date = new Temporal.Date(date_s);
        String HeadHouseholdName = (String) theFollowUpSurvey.getHeadHouseholdName();
        String PersonBeingInterviewed = (String) getInterchangeAns("PersonBeingInterviewed",validatedInterchangesWithAns);
        String WaterTreatmentBeforeDrinking = (String) getInterchangeAns("WaterTreatmentBeforeDrinking",validatedInterchangesWithAns);
        String MainReasonNoWaterTreatmentBeforeDrinking = (String) getInterchangeAns("MainReasonNoWaterTreatmentBeforeDrinking",validatedInterchangesWithAns);
        String WaterTreatmentMethod = (String) getInterchangeAns("WaterTreatmentMethod",validatedInterchangesWithAns);
        String HowLongUsingWaterTreatment = (String) getInterchangeAns("HowLongUsingWaterTreatment",validatedInterchangesWithAns);
        String FrequencyWaterTreatment = (String) getInterchangeAns("FrequencyWaterTreatment",validatedInterchangesWithAns);
        String WaterStorageAtHome = (String) getInterchangeAns("WaterStorageAtHome",validatedInterchangesWithAns);
        String WaterStorageContainerHaveLid = (String) getInterchangeAns("WaterStorageContainerHaveLid",validatedInterchangesWithAns);
        String TakingWaterFromStorage = (String) getInterchangeAns("TakingWaterFromStorage",validatedInterchangesWithAns);
        String WashedHandsIn24Hours = (String) getInterchangeAns("WashedHandsIn24Hours",validatedInterchangesWithAns);
        String WhenWashedHandsIn24Hours = (String) getInterchangeAns("WhenWashedHandsIn24Hours",validatedInterchangesWithAns);
        String WhatUsedToWashYourHands = (String) getInterchangeAns("WhatUsedToWashYourHands",validatedInterchangesWithAns);
        String CommonIllnessAffectingAllChildrenInHousehold = (String) getInterchangeAns("CommonIllnessAffectingAllChildrenInHousehold",validatedInterchangesWithAns);
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
        String HealthChangeInAYear = (String) getInterchangeAns("HealthChangeInAYear",validatedInterchangesWithAns);
        String HealthChangeFamilyInAYear = (String) getInterchangeAns("HealthChangeFamilyInAYear",validatedInterchangesWithAns);
        String BenefitSWP = (String) getInterchangeAns("BenefitSWP",validatedInterchangesWithAns);

        FollowUpSurvey followUpSurvey = FollowUpSurvey.builder()
                .namebwe(Namebwe)
                .country(Country)
                .community(Community)
                .surveyId(SurveyId)
                .headHouseholdName(HeadHouseholdName)
                .personBeingInterviewed(PersonBeingInterviewed)
                .waterTreatmentBeforeDrinking(WaterTreatmentBeforeDrinking)
                .mainReasonNoWaterTreatmentBeforeDrinking(MainReasonNoWaterTreatmentBeforeDrinking)
                .waterTreatmentMethod(WaterTreatmentMethod)
                .howLongUsingWaterTreatment(HowLongUsingWaterTreatment)
                .frequencyWaterTreatment(FrequencyWaterTreatment)
                .waterStorageAtHome(WaterStorageAtHome)
                .waterStorageContainerHaveLid(WaterStorageContainerHaveLid)
                .takingWaterFromStorage(TakingWaterFromStorage)
                .washedHandsIn24Hours(WashedHandsIn24Hours)
                .whenWashedHandsIn24Hours(WhenWashedHandsIn24Hours)
                .whatUsedToWashYourHands(WhatUsedToWashYourHands)
                .commonIllnessAffectingAllChildrenInHousehold(CommonIllnessAffectingAllChildrenInHousehold)
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
                .healthChangeInAYear(HealthChangeInAYear)
                .healthChangeFamilyInAYear(HealthChangeFamilyInAYear)
                .benefitSwp(BenefitSWP)
                .completed(completed)
                .lat(lat)
                .lng(lng)
                .date(date)
                .id(theFollowUpSurvey.getId())
                .build();
        return followUpSurvey;

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

    public static int parseIntegerWithDefault(Object s, int defaultVal) {
        if (s instanceof Integer) {
            return (Integer) s;
        }else if (s instanceof String){
            String str = (String) s;
            return str.matches("-?\\d+") ? Integer.parseInt(str): defaultVal;
        }else
            return defaultVal;

    }

    private void saveFollowUpSurvey(FollowUpSurvey followUpSurveyToSave){
        Amplify.DataStore.save(
                followUpSurveyToSave,
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

    private void showSavedSuccessfulAlert(){
        new AlertDialog.Builder(UpdateFollowUpSurveyActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Follow Up Survey Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all interchange answers
                        for(Interchange interchange: UpdateFollowUpSurveyActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        UpdateFollowUpSurveyActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(UpdateFollowUpSurveyActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Follow Up Survey Save Failed Please try again\n" )
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                        .setCanceledOnTouchOutside(false);
            }
        });
    }



}
