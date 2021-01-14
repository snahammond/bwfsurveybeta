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
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.InitialSurvey;
import com.bwfsurvey.bwfsurveybeta.MyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.InterchangeCardAdapter;
import com.bwfsurvey.bwfsurveybeta.adapters.ViewOnlyInterchangeCardAdapter;
import com.bwfsurvey.bwfsurveybeta.types.Interchange;
import com.bwfsurvey.bwfsurveybeta.types.ViewOnlyInterchange;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class UpdateInitialSurveyActivity extends AppCompatActivity {
    InitialSurvey theInitialSurvey;
    private String uuidInitialSurvey;
    private RecyclerView recyclerView;
    private InterchangeCardAdapter adapter;

    private static ArrayList<Interchange> interchanges;
    private LinearLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().getStringExtra("UUID")!=null)
            uuidInitialSurvey = getIntent().getStringExtra("UUID");

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createInitialSurveyUpdateInterchanges();
    }

    private void createInitialSurveyUpdateInterchanges() {
        interchanges = new ArrayList<>();
        createInterchangesAndShowOnRecyclerView();
    }

    private void createInterchangesAndShowOnRecyclerView() {
        Amplify.DataStore.query(
                InitialSurvey.class,
                Where.matches(InitialSurvey.ID.eq(uuidInitialSurvey)),
                initialSurvey -> {
                    Log.i("Tutorials", "DataStore is queried.");
                    while (initialSurvey.hasNext()) {
                        theInitialSurvey = initialSurvey.next();
                        Log.i("Tutorials", "DataStore is queried. theInitialSurvey " +theInitialSurvey.getId());
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
        if(theInitialSurvey!=null){
            ArrayList<Interchange> returnedInterchanges = MyAmplifyApplication.getInterchanges("INITAILSURVEY");
            if(returnedInterchanges!=null){
                UpdateInitialSurveyActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    //set answers
                    String answer = "";
                    String nameOfAns = interchange.getAnswer().getAnswerDef().getName();
                    String methodName = "get"+nameOfAns;
                    java.lang.reflect.Method method;
                    try {
                        method = theInitialSurvey.getClass().getMethod(methodName);
                        Object ansObject = method.invoke(theInitialSurvey);
                        answer = ansObject.toString();
                    } catch (Exception e) {
                        Log.e("Tutorials", "Could not get answer");
                    }
                    interchange.getAnswer().setAns(answer);

                    interchange.setPositionOnRecyler(positionOnRecyler);
                    UpdateInitialSurveyActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
                //sort the interchanges
                Collections.sort(UpdateInitialSurveyActivity.interchanges);
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
        CountDownTimer countDownTimer = new CountDownTimer(16000,1000) {
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
        adapter = new InterchangeCardAdapter(UpdateInitialSurveyActivity.this, UpdateInitialSurveyActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        for(Interchange interchange: UpdateInitialSurveyActivity.interchanges){
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
                //make an InitialSurvey object
                InitialSurvey initialSurveyToSave = makeInitialSurveyObjectWithId(interchangesWithUserAns,1,"","");
                saveIntialSurvey(initialSurveyToSave);
            }
        }

        if(id== R.id.suspend){
            ArrayList<Interchange> interchangesWithUserAns = adapter.retrieveData();

            //make an InitialSurvey object to save
            InitialSurvey initialSurveyToSave = makeInitialSurveyObjectWithId(interchangesWithUserAns,0,"","");
            saveIntialSurvey(initialSurveyToSave);

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
        new AlertDialog.Builder(UpdateInitialSurveyActivity.this)
                .setTitle("Invalid Questions")
                .setMessage("Some questions have not been correctly answered \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private InitialSurvey makeInitialSurveyObjectWithId(ArrayList<Interchange> validatedInterchangesWithAns,int completed, String lat, String lng){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = theInitialSurvey.getNamebwe();
        String Country = theInitialSurvey.getCountry();
        String Community = theInitialSurvey.getCommunity();
        Integer SurveyId = theInitialSurvey.getSurveyId();
        Temporal.Date date = new Temporal.Date(date_s);
        String HeadHouseholdName = (String) getInterchangeAns("HeadHouseholdName",validatedInterchangesWithAns);
        String HeadHouseholdSex = (String) getInterchangeAns("HeadHouseholdSex",validatedInterchangesWithAns);
        String HeadHouseholdMaritalStatus = (String) getInterchangeAns("HeadHouseholdMaritalStatus",validatedInterchangesWithAns);
        Integer HeadHouseholdAge = parseIntegerWithDefault( getInterchangeAns("HeadHouseholdAge",validatedInterchangesWithAns),0);
        String HeadHouseholdOccupation = (String) getInterchangeAns("HeadHouseholdOccupation",validatedInterchangesWithAns);
        String HeadHouseholdEducation = (String) getInterchangeAns("HeadHouseholdEducation",validatedInterchangesWithAns);
        String PersonBeingInterviewed = (String) getInterchangeAns("PersonBeingInterviewed",validatedInterchangesWithAns);
        Integer TotalNoPeopleHousehold = parseIntegerWithDefault(getInterchangeAns("TotalNoPeopleHousehold",validatedInterchangesWithAns),0);
        Integer NoHouseholdMale0_1Year = parseIntegerWithDefault(getInterchangeAns("NoHouseholdMale0_1Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdFemale0_1Year = parseIntegerWithDefault(getInterchangeAns("NoHouseholdMale1_5Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdMale1_5Year = parseIntegerWithDefault(getInterchangeAns("NoHouseholdMale1_5Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdFemale1_5Year = parseIntegerWithDefault(getInterchangeAns("NoHouseholdFemale1_5Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdMale5_12Year = parseIntegerWithDefault(getInterchangeAns("NoHouseholdMale5_12Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdFemale5_12Year = parseIntegerWithDefault(getInterchangeAns("NoHouseholdFemale5_12Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdMale13_17Year = parseIntegerWithDefault(getInterchangeAns("NoHouseholdMale13_17Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdFemale13_17Year = parseIntegerWithDefault(getInterchangeAns("NoHouseholdFemale13_17Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdMale18_Year = parseIntegerWithDefault(getInterchangeAns("NoHouseholdMale18_Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdFemale18_Year = parseIntegerWithDefault(getInterchangeAns("NoHouseholdFemale18_Year",validatedInterchangesWithAns),0);
        String ReasonNoSchoolChildren5_17Year = (String) getInterchangeAns("ReasonNoSchoolChildren5_17Year",validatedInterchangesWithAns);
        String MainSourceDrinkingWater = (String) getInterchangeAns("MainSourceDrinkingWater",validatedInterchangesWithAns);
        String MainSourceOtherPurposeWater = (String) getInterchangeAns("MainSourceOtherPurposeWater",validatedInterchangesWithAns);
        Integer TimeToWaterSourceGetReturn = parseIntegerWithDefault(getInterchangeAns("TimeToWaterSourceGetReturn",validatedInterchangesWithAns),0);
        String HouseholdFrequencyAtWaterSource = (String) getInterchangeAns("HouseholdFrequencyAtWaterSource",validatedInterchangesWithAns);
        String UsualHouseholdWaterFetcher = (String) getInterchangeAns("UsualHouseholdWaterFetcher",validatedInterchangesWithAns);
        String ContainerCarryWater = (String) getInterchangeAns("ContainerCarryWater",validatedInterchangesWithAns);
        String WaterTreatmentBeforeDrinking = (String) getInterchangeAns("WaterTreatmentBeforeDrinking",validatedInterchangesWithAns);
        String MainReasonNoWaterTreatmentBeforeDrinking = (String) getInterchangeAns("MainReasonNoWaterTreatmentBeforeDrinking",validatedInterchangesWithAns);
        String WaterTreatmentMethod = (String) getInterchangeAns("WaterTreatmentMethod",validatedInterchangesWithAns);
        String HowLongUsingWaterTreatment = (String) getInterchangeAns("HowLongUsingWaterTreatment",validatedInterchangesWithAns);
        String FrequencyWaterTreatment = (String) getInterchangeAns("FrequencyWaterTreatment",validatedInterchangesWithAns);
        String WaterStorageAtHome = (String) getInterchangeAns("WaterStorageAtHome",validatedInterchangesWithAns);
        String WaterStorageContainerHaveLid = (String) getInterchangeAns("WaterStorageContainerHaveLid",validatedInterchangesWithAns);
        String TakingWaterFromStorage = (String) getInterchangeAns("TakingWaterFromStorage",validatedInterchangesWithAns);
        String RubbishDisposal = (String) getInterchangeAns("RubbishDisposal",validatedInterchangesWithAns);
        String HouseholdDefecationMethod = (String) getInterchangeAns("HouseholdDefecationMethod",validatedInterchangesWithAns);
        String WasteDisposalYoungestChild = (String) getInterchangeAns("WasteDisposalYoungestChild",validatedInterchangesWithAns);
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


        InitialSurvey initialSurvey = InitialSurvey.builder()
                .namebwe(Namebwe)
                .country(Country)
                .community(Community)
                .surveyId(SurveyId)
                .headHouseholdName(HeadHouseholdName)
                .headHouseholdSex(HeadHouseholdSex)
                .headHouseholdMaritalStatus(HeadHouseholdMaritalStatus)
                .headHouseholdAge(HeadHouseholdAge)
                .headHouseholdOccupation(HeadHouseholdOccupation)
                .headHouseholdEducation(HeadHouseholdEducation)
                .personBeingInterviewed(PersonBeingInterviewed)
                .totalNoPeopleHousehold(TotalNoPeopleHousehold)
                .noHouseholdMale0_1Year(NoHouseholdMale0_1Year)
                .noHouseholdFemale0_1Year(NoHouseholdFemale0_1Year)
                .noHouseholdMale1_5Year(NoHouseholdMale1_5Year)
                .noHouseholdFemale1_5Year(NoHouseholdFemale1_5Year)
                .noHouseholdMale5_12Year(NoHouseholdMale5_12Year)
                .noHouseholdFemale5_12Year(NoHouseholdFemale5_12Year)
                .noHouseholdMale13_17Year(NoHouseholdMale13_17Year)
                .noHouseholdFemale13_17Year(NoHouseholdFemale13_17Year)
                .noHouseholdMale18Year(NoHouseholdMale18_Year)
                .noHouseholdFemale18Year(NoHouseholdFemale18_Year)
                .reasonNoSchoolChildren5_17Year(ReasonNoSchoolChildren5_17Year)
                .mainSourceDrinkingWater(MainSourceDrinkingWater)
                .mainSourceOtherPurposeWater(MainSourceOtherPurposeWater)
                .timeToWaterSourceGetReturn(TimeToWaterSourceGetReturn)
                .householdFrequencyAtWaterSource(HouseholdFrequencyAtWaterSource)
                .usualHouseholdWaterFetcher(UsualHouseholdWaterFetcher)
                .containerCarryWater(ContainerCarryWater)
                .waterTreatmentBeforeDrinking(WaterTreatmentBeforeDrinking)
                .mainReasonNoWaterTreatmentBeforeDrinking(MainReasonNoWaterTreatmentBeforeDrinking)
                .waterTreatmentMethod(WaterTreatmentMethod)
                .howLongUsingWaterTreatment(HowLongUsingWaterTreatment)
                .frequencyWaterTreatment(FrequencyWaterTreatment)
                .waterStorageAtHome(WaterStorageAtHome)
                .waterStorageContainerHaveLid(WaterStorageContainerHaveLid)
                .takingWaterFromStorage(TakingWaterFromStorage)
                .rubbishDisposal(RubbishDisposal)
                .householdDefecationMethod(HouseholdDefecationMethod)
                .wasteDisposalYoungestChild(WasteDisposalYoungestChild)
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
                .completed(completed)
                .lat(lat)
                .lng(lng)
                .date(date)
                .id(theInitialSurvey.getId())
                .build();
        return initialSurvey;
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

    private void saveIntialSurvey(InitialSurvey initialSurveyToSave){
        Amplify.DataStore.save(initialSurveyToSave,
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

    private void doSyncWaitAndShowSavedSuccessfulAlert(){
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
                new AlertDialog.Builder(UpdateInitialSurveyActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Initial Survey Save Failed Please try again\n" )
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                        .setCanceledOnTouchOutside(false);
            }
        });
    }

    private void showSavedSuccessfulAlert(){
        new AlertDialog.Builder(UpdateInitialSurveyActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Initial Survey Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all the user answers
                        for(Interchange interchange: UpdateInitialSurveyActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        UpdateInitialSurveyActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

}
