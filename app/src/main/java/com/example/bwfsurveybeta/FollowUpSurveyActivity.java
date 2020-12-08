package com.example.bwfsurveybeta;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.FollowUpSurvey;
import com.amplifyframework.datastore.generated.model.InitialSurvey;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FollowUpSurveyActivity extends AppCompatActivity {
    private String namebwe = null;
    private String surveyType = null;
    private String country = null;
    private String community = null;
    private String householdName = null;
    private static ArrayList<Interchange> interchanges;
    private RecyclerView recyclerView;
    private InterchangeCardAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("NAME_BWE")!=null)
            namebwe = getIntent().getStringExtra("SURVEY_TYPE");
        if(getIntent().getStringExtra("SURVEY_TYPE")!=null)
            surveyType = getIntent().getStringExtra("SURVEY_TYPE");
        if(getIntent().getStringExtra("COUNTRY")!=null)
            country = getIntent().getStringExtra("COUNTRY");
        if(getIntent().getStringExtra("COMMUNITY")!=null)
            community = getIntent().getStringExtra("COMMUNITY");
        if(getIntent().getStringExtra("HHNAME")!=null)
            householdName = getIntent().getStringExtra("HHNAME");
        Log.i("Tutorials", "Selected family follow up survey class: " + householdName);
        setContentView(R.layout.activity_recycler);
        getSupportActionBar().setTitle((CharSequence) "FollowUp Survey "+householdName);
        initView();
    }

    private void initView() {
        createFollowSurveyQuestionaire();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InterchangeCardAdapter(FollowUpSurveyActivity.this, FollowUpSurveyActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }

    private void createFollowSurveyQuestionaire() {
        try{
            ArrayList<Interchange> returnedInterchanges = MyAmplifyApplication.getInterchanges("FOLLOWUPSURVEY");
            if(returnedInterchanges!=null){
                FollowUpSurveyActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    interchange.setPositionOnRecyler(positionOnRecyler);
                    FollowUpSurveyActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
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

        if (id == R.id.menu_save) {
            ArrayList<Interchange> interchangesWithUserAns = adapter.retrieveData();

            //we have to validate now
            ArrayList<Interchange> invalideInterchanges = validateUserAns(interchangesWithUserAns);
            Log.i("Tutorial", "how many invalid interfaces: " + invalideInterchanges.size());


            if(invalideInterchanges.size()>0){
                showInvalidSurveyAlert();
            }else{
                //make an InitialSurvey object
                FollowUpSurvey followUpSurveyToSave = makeFollowUpSurveyObject(interchangesWithUserAns);

                //save the initialSurvey object
                saveFollowUpSurvey(followUpSurveyToSave);

            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveFollowUpSurvey(FollowUpSurvey followUpSurveyToSave){
        Amplify.DataStore.save(
                followUpSurveyToSave,
                update -> {
                    Log.i("Tutorial", "Saved Successfully ");
                    showSavedSuccessfulAlert();
                },
                failure -> {
                    Log.i("Tutorial", "Save Failed ");
                    showSaveFailedAlert();
                }
        );
    }

    private void showSavedSuccessfulAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(FollowUpSurveyActivity.this)
                        .setTitle("Saved Succussfully")
                        .setMessage("Follow Up Survey Saved Succussfully \n" )
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //reset all interchange answers
                                for(Interchange interchange: FollowUpSurveyActivity.interchanges){
                                    interchange.getAnswer().setAns(null);
                                }
                                FollowUpSurveyActivity.this.finish();

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            }
        });
    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(FollowUpSurveyActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Follow Up Survey Save Failed Please try again\n" )
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private FollowUpSurvey makeFollowUpSurveyObject(ArrayList<Interchange> validatedInterchangesWithAns){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = namebwe;
        String Country = (String) country;
        String Community = (String) community;
        Integer SurveyId = 0;
        Temporal.Date date = new Temporal.Date(date_s);
        String HeadHouseholdName = (String) householdName;
        String PersonBeingInterviewed = (String) getInterchangeAns("PersonBeingInterviewed",validatedInterchangesWithAns);
        String WaterTreatmentBeforeDrinking = (String) getInterchangeAns("WaterTreatmentBeforeDrinking",validatedInterchangesWithAns);
        String MainReasonNoWaterTreatmentBeforeDrinking = (String) getInterchangeAns("MainReasonNoWaterTreatmentBeforeDrinking",validatedInterchangesWithAns);
        String WaterTreatmentMethod = (String) getInterchangeAns("WaterTreatmentMethod",validatedInterchangesWithAns);
        String HowLongUsingWaterTreatment = (String) getInterchangeAns("HowLongUsingWaterTreatment",validatedInterchangesWithAns);
        String FrequencyWaterTreatment = (String) getInterchangeAns("FrequencyWaterTreatment",validatedInterchangesWithAns);
        String WaterStorageAtHome = (String) getInterchangeAns("WaterStorageAtHome",validatedInterchangesWithAns);
        String TakingWaterFromStorage = (String) getInterchangeAns("TakingWaterFromStorage",validatedInterchangesWithAns);
        String WashedHandsIn24Hours = (String) getInterchangeAns("WashedHandsIn24Hours",validatedInterchangesWithAns);
        String WhenWashedHandsIn24Hours = (String) getInterchangeAns("WhenWashedHandsIn24Hours",validatedInterchangesWithAns);
        String WhatUsedToWashYourHands = (String) getInterchangeAns("WhatUsedToWashYourHands",validatedInterchangesWithAns);
        Integer NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek = (Integer) getInterchangeAns("NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek",validatedInterchangesWithAns);
        String CommonIllnessAffectingChildrenUnder5 = (String) getInterchangeAns("CommonIllnessAffectingChildrenUnder5",validatedInterchangesWithAns);
        Integer NoChildrenWithVomitingOrDiarrheaIn7days = (Integer) getInterchangeAns("NoChildrenWithVomitingOrDiarrheaIn7days",validatedInterchangesWithAns);
        String DidSickChildrenGoToHospital = (String) getInterchangeAns("DidSickChildrenGoToHospital",validatedInterchangesWithAns);
        String DidSickChildrenGoToHospitalYes = (String) getInterchangeAns("DidSickChildrenGoToHospitalYes",validatedInterchangesWithAns);
        String SickChildrenBreastfeeding = (String) getInterchangeAns("SickChildrenBreastfeeding",validatedInterchangesWithAns);
        String OutcomeMostRecentVomiting_DiarrheaAtHospital = (String) getInterchangeAns("OutcomeMostRecentVomiting_DiarrheaAtHospital",validatedInterchangesWithAns);
        Integer NoDaysNoWorkBecauseOfOwnIllness = (Integer) getInterchangeAns("NoDaysNoWorkBecauseOfOwnIllness",validatedInterchangesWithAns);
        Integer NoDaysNoWorkBecauseOfIllnessFamilyMembers = (Integer) getInterchangeAns("NoDaysNoWorkBecauseOfIllnessFamilyMembers",validatedInterchangesWithAns);
        Integer MoneySpentMedicalTreatmentLast4weeks = (Integer) getInterchangeAns("MoneySpentMedicalTreatmentLast4weeks",validatedInterchangesWithAns);
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
                .takingWaterFromStorage(TakingWaterFromStorage)
                .washedHandsIn24Hours(WashedHandsIn24Hours)
                .whenWashedHandsIn24Hours(WhenWashedHandsIn24Hours)
                .whatUsedToWashYourHands(WhatUsedToWashYourHands)
                .noTotalSchoolDaysMissedByAllChildrenIn2LastWeek(NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek)
                .commonIllnessAffectingChildrenUnder5(CommonIllnessAffectingChildrenUnder5)
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
                .date(date)
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

    private void showInvalidSurveyAlert(){
        new AlertDialog.Builder(FollowUpSurveyActivity.this)
                .setTitle("Invalid Questions")
                .setMessage("Some questions have not been correctly answered \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
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
}
