package com.bwfsurvey.bwfsurveybeta.activities.collect;

import android.content.DialogInterface;
import android.os.Bundle;
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
import com.amplifyframework.datastore.generated.model.InitialSurvey;
import com.amplifyframework.datastore.syncengine.OutboxMutationEvent;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.SubscriptionToken;
import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.InterchangeCardAdapter;
import com.bwfsurvey.bwfsurveybeta.types.Interchange;
import com.bwfsurvey.bwfsurveybeta.utils.IntegerUtils;
import com.bwfsurvey.bwfsurveybeta.utils.InterchangeUtils;
import com.bwfsurvey.bwfsurveybeta.utils.PhoneLocation;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class InitialSurveyActivity extends AppCompatActivity /*implements SaveSurveyDialog.SaveQuestionaireDialogListener*/ {
    private RecyclerView recyclerView;
    private InterchangeCardAdapter adapter;
    private String namebwe;
    String countrybwe = null;
    String community = null;
    String positionbwe = null;
    private String lat = null;
    private String lng = null;
    int surveyId = 0;

    private static ArrayList<Interchange> interchanges;

    private LinearLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().getStringExtra("NAME_BWE")!=null)
            namebwe = getIntent().getStringExtra("NAME_BWE");
        if(getIntent().getStringExtra("COUNTRY_BWE")!=null)
            countrybwe = getIntent().getStringExtra("COUNTRY_BWE");
        if(getIntent().getStringExtra("COMMUNITY")!=null)
            community = getIntent().getStringExtra("COMMUNITY");
        if(getIntent().getStringExtra("POSITION_BWE")!=null)
            positionbwe = getIntent().getStringExtra("POSITION_BWE");

        surveyId = getIntent().getIntExtra("SURVEY_ID",0);
        if(getIntent().getStringExtra("LAT")!=null)
            lat = getIntent().getStringExtra("LAT");
        if(getIntent().getStringExtra("LNG")!=null)
            lng = getIntent().getStringExtra("LNG");

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);

        createInitialSurveyQuestionaire();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InterchangeCardAdapter(InitialSurveyActivity.this, InitialSurveyActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }

    private void createInitialSurveyQuestionaire(){
        try{
            ArrayList<Interchange> returnedInterchanges = BwfSurveyAmplifyApplication.getInterchanges("INITAILSURVEY");
            if(returnedInterchanges!=null){
                InitialSurveyActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    interchange.setPositionOnRecyler(positionOnRecyler);
                    InitialSurveyActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
                //sort the interchanges
                Collections.sort(InitialSurveyActivity.interchanges);


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
                InterchangeUtils.showInvalidSurveyAlert(invalideInterchanges,InitialSurveyActivity.this);
            }else{
                String lat_ = "";
                String lng_ = "";
                if(lat!=null&&lng!=null){
                    lat_= lat;
                    lng_ = lng;
                }else{
                    //try and get it again
                    PhoneLocation phoneLocation = new PhoneLocation(InitialSurveyActivity.this);
                    String[] arraylatlng = phoneLocation.getLocation();
                    if(arraylatlng!=null){
                        lat_ = arraylatlng[0];
                        lng_ = arraylatlng[1];
                    }
                }

                //make an InitialSurvey object
                InitialSurvey initialSurveyToSave = makeInitialSurveyObject(interchangesWithUserAns,1,lat_,lng_);
                saveIntialSurvey(initialSurveyToSave);
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
                PhoneLocation phoneLocation = new PhoneLocation(InitialSurveyActivity.this);
                String[] arraylatlng = phoneLocation.getLocation();
                if(arraylatlng!=null){
                    lat_ = arraylatlng[0];
                    lng_ = arraylatlng[1];
                }
            }
            //make an InitialSurvey object to save
            InitialSurvey initialSurveyToSave = makeInitialSurveyObject(interchangesWithUserAns,0,lat_,lng_);
            saveIntialSurvey(initialSurveyToSave);

        }
        return super.onOptionsItemSelected(item);
    }


    SubscriptionToken checkToken = null;
    private void saveIntialSurvey(InitialSurvey initialSurveyToSave){
        checkToken = Amplify.Hub.subscribe(
                HubChannel.DATASTORE,
                hubEvent -> DataStoreChannelEventName.OUTBOX_MUTATION_ENQUEUED.toString().equals(hubEvent.getName()),
                hubEvent -> {
                    OutboxMutationEvent event = (OutboxMutationEvent) hubEvent.getData();
                    //Log.i("bwfSurveyAmplify", " InitialSurvey "+event.getModelName());
                    if(event!=null && event.getModelName().contentEquals("InitialSurvey")){
                        if(event.getElement().getModel().getId().contentEquals(initialSurveyToSave.getId())){
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    progressBar.setVisibility(View.GONE);
                                    showSavedSuccessfulAlert();
                                }
                            });
                        }else{
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    progressBar.setVisibility(View.GONE);
                                    //reset all the user answers
                                    for(Interchange interchange: InitialSurveyActivity.interchanges){
                                        interchange.getAnswer().setAns(null);
                                    }
                                    InitialSurveyActivity.this.finish();
                                }
                            });
                        }
                    }else{
                        runOnUiThread(new Runnable() {
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                //reset all the user answers
                                for(Interchange interchange: InitialSurveyActivity.interchanges){
                                    interchange.getAnswer().setAns(null);
                                }
                                InitialSurveyActivity.this.finish();
                            }
                        });
                    }
                }
        );
        Amplify.DataStore.save(initialSurveyToSave,
                update -> {
                    //Log.i("Tutorial", "Saved Successfully ");
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

    }

    private void showSavedSuccessfulAlert(){
        if(checkToken!=null)
            Amplify.Hub.unsubscribe(checkToken);
        new AlertDialog.Builder(InitialSurveyActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Initial Survey Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all the user answers
                        for(Interchange interchange: InitialSurveyActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        InitialSurveyActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(InitialSurveyActivity.this)
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

    private InitialSurvey makeInitialSurveyObject(ArrayList<Interchange> validatedInterchangesWithAns,int completed, String lat, String lng){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = namebwe;
        String Country = countrybwe;
        String Community = community;
        Integer SurveyId = surveyId;
        Temporal.Date date = new Temporal.Date(date_s);
        String HeadHouseholdName = (String) InterchangeUtils.getInterchangeAns("HeadHouseholdName",validatedInterchangesWithAns);
        String HeadHouseholdPhoneNumber = (String) InterchangeUtils.getInterchangeAns("HeadHouseholdPhoneNumber",validatedInterchangesWithAns);
        String HeadHouseholdSex = (String) InterchangeUtils.getInterchangeAns("HeadHouseholdSex",validatedInterchangesWithAns);
        String HeadHouseholdMaritalStatus = (String) InterchangeUtils.getInterchangeAns("HeadHouseholdMaritalStatus",validatedInterchangesWithAns);
        Integer HeadHouseholdAge = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("HeadHouseholdAge",validatedInterchangesWithAns),0);
        String HeadHouseholdOccupation = (String) InterchangeUtils.getInterchangeAns("HeadHouseholdOccupation",validatedInterchangesWithAns);
        String HeadHouseholdEducation = (String) InterchangeUtils.getInterchangeAns("HeadHouseholdEducation",validatedInterchangesWithAns);
        String PersonBeingInterviewed = (String) InterchangeUtils.getInterchangeAns("PersonBeingInterviewed",validatedInterchangesWithAns);
        Integer TotalNoPeopleHousehold = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("TotalNoPeopleHousehold",validatedInterchangesWithAns),0);
        Integer NoHouseholdMale0_1Year = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoHouseholdMale0_1Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdFemale0_1Year = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoHouseholdFemale0_1Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdMale1_5Year = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoHouseholdMale1_5Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdFemale1_5Year = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoHouseholdFemale1_5Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdMale5_12Year = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoHouseholdMale5_12Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdFemale5_12Year = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoHouseholdFemale5_12Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdMale13_17Year = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoHouseholdMale13_17Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdFemale13_17Year = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoHouseholdFemale13_17Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdMale18_Year = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoHouseholdMale18_Year",validatedInterchangesWithAns),0);
        Integer NoHouseholdFemale18_Year = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("NoHouseholdFemale18_Year",validatedInterchangesWithAns),0);
        String ReasonNoSchoolChildren5_17Year = (String) InterchangeUtils.getInterchangeAns("ReasonNoSchoolChildren5_17Year",validatedInterchangesWithAns);
        String MainSourceDrinkingWater = (String) InterchangeUtils.getInterchangeAns("MainSourceDrinkingWater",validatedInterchangesWithAns);
        String MainSourceOtherPurposeWater = (String) InterchangeUtils.getInterchangeAns("MainSourceOtherPurposeWater",validatedInterchangesWithAns);
        Integer TimeToWaterSourceGetReturn = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("TimeToWaterSourceGetReturn",validatedInterchangesWithAns),0);
        String HouseholdFrequencyAtWaterSource = (String) InterchangeUtils.getInterchangeAns("HouseholdFrequencyAtWaterSource",validatedInterchangesWithAns);
        String UsualHouseholdWaterFetcher = (String) InterchangeUtils.getInterchangeAns("UsualHouseholdWaterFetcher",validatedInterchangesWithAns);
        String ContainerCarryWater = (String) InterchangeUtils.getInterchangeAns("ContainerCarryWater",validatedInterchangesWithAns);
        String WaterTreatmentBeforeDrinking = (String) InterchangeUtils.getInterchangeAns("WaterTreatmentBeforeDrinking",validatedInterchangesWithAns);
        String MainReasonNoWaterTreatmentBeforeDrinking = (String) InterchangeUtils.getInterchangeAns("MainReasonNoWaterTreatmentBeforeDrinking",validatedInterchangesWithAns);
        String WaterTreatmentMethod = (String) InterchangeUtils.getInterchangeAns("WaterTreatmentMethod",validatedInterchangesWithAns);
        String FrequencyWaterTreatment = (String) InterchangeUtils.getInterchangeAns("FrequencyWaterTreatment",validatedInterchangesWithAns);
        String LastTimeTreatedHouseholdWaterWithChlorine = (String) InterchangeUtils.getInterchangeAns("LastTimeTreatedHouseholdWaterWithChlorine",validatedInterchangesWithAns);
        String WhereDidYouGetChlorineToTreatHouseholdWater = (String) InterchangeUtils.getInterchangeAns("WhereDidYouGetChlorineToTreatHouseholdWater",validatedInterchangesWithAns);
        Integer AmountSpendPerWeekForChlorineToTreatWater = IntegerUtils.parseIntegerWithDefault(InterchangeUtils.getInterchangeAns("AmountSpendPerWeekForChlorineToTreatWater", validatedInterchangesWithAns),0);
        String HowDifficultToObtainChlorine = (String) InterchangeUtils.getInterchangeAns("HowDifficultToObtainChlorine",validatedInterchangesWithAns);
        String TakingWaterFromStorage = (String) InterchangeUtils.getInterchangeAns("TakingWaterFromStorage",validatedInterchangesWithAns);
        String RubbishDisposal = (String) InterchangeUtils.getInterchangeAns("RubbishDisposal",validatedInterchangesWithAns);
        String HouseholdDefecationMethod = (String) InterchangeUtils.getInterchangeAns("HouseholdDefecationMethod",validatedInterchangesWithAns);
        String WasteDisposalYoungestChild = (String) InterchangeUtils.getInterchangeAns("WasteDisposalYoungestChild",validatedInterchangesWithAns);
        String WashedHandsIn24Hours = (String) InterchangeUtils.getInterchangeAns("WashedHandsIn24Hours",validatedInterchangesWithAns);
        String WhenWashedHandsIn24Hours = (String) InterchangeUtils.getInterchangeAns("WhenWashedHandsIn24Hours",validatedInterchangesWithAns);
        String WhatUsedToWashYourHands = (String) InterchangeUtils.getInterchangeAns("WhatUsedToWashYourHands",validatedInterchangesWithAns);
        String CommonIllnessAffectingAllChildrenInHousehold = (String) InterchangeUtils.getInterchangeAns("CommonIllnessAffectingAllChildrenInHousehold",validatedInterchangesWithAns);
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
        String HealthChangeInAYear = (String) InterchangeUtils.getInterchangeAns("HealthChangeInAYear",validatedInterchangesWithAns);
        String HealthChangeFamilyInAYear = (String) InterchangeUtils.getInterchangeAns("HealthChangeFamilyInAYear",validatedInterchangesWithAns);
        String ChildrenDiedAfterBeingBornAlive = (String) InterchangeUtils.getInterchangeAns("ChildrenDiedAfterBeingBornAlive",validatedInterchangesWithAns);
        String CausesOfChildrenDeath = (String) InterchangeUtils.getInterchangeAns("CausesOfChildrenDeath", validatedInterchangesWithAns);

        InitialSurvey initialSurvey = InitialSurvey.builder()
                .namebwe(Namebwe)
                .country(Country)
                .community(Community)
                .surveyId(SurveyId)
                .headHouseholdName(HeadHouseholdName)
                .headHouseholdPhoneNumber(HeadHouseholdPhoneNumber)
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
                .frequencyWaterTreatment(FrequencyWaterTreatment)
                .lastTimeTreatedHouseholdWaterWithChlorine(LastTimeTreatedHouseholdWaterWithChlorine)
                .whereDidYouGetChlorineToTreatHouseholdWater(WhereDidYouGetChlorineToTreatHouseholdWater)
                .amountSpendPerWeekForChlorineToTreatWater(AmountSpendPerWeekForChlorineToTreatWater)
                .howDifficultToObtainChlorine(HowDifficultToObtainChlorine)
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
                .childrenDiedAfterBeingBornAlive(ChildrenDiedAfterBeingBornAlive)
                .causesOfChildrenDeath(CausesOfChildrenDeath)
                .date(date)
                .build();
        return initialSurvey;

    }

}
