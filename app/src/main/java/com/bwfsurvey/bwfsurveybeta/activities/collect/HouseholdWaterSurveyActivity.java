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
import com.amplifyframework.datastore.generated.model.HouseholdWaterTest;
import com.amplifyframework.datastore.syncengine.OutboxMutationEvent;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.SubscriptionToken;
import com.bwfsurvey.bwfsurveybeta.types.Interchange;
import com.bwfsurvey.bwfsurveybeta.adapters.InterchangeCardAdapter;
import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.utils.DateUtils;
import com.bwfsurvey.bwfsurveybeta.utils.InterchangeUtils;
import com.bwfsurvey.bwfsurveybeta.utils.PhoneLocation;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Objects;

public class HouseholdWaterSurveyActivity extends AppCompatActivity {
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

        Log.i("Tutorials", "Selected family water survey household class: " + householdName +" country: "+ country + " community: "+community + "surveyId: " + surveyId);
        setContentView(R.layout.activity_recycler);
        Objects.requireNonNull(getSupportActionBar()).setTitle((CharSequence) "Water Survey; "+householdName);
        initView();
    }

    private void initView() {
        createHouseholdWaterSurveyQuestionaire();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InterchangeCardAdapter(HouseholdWaterSurveyActivity.this, HouseholdWaterSurveyActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }

    private void createHouseholdWaterSurveyQuestionaire() {
        try{
            ArrayList<Interchange> returnedInterchanges = BwfSurveyAmplifyApplication.getInterchanges(surveyType);
            if(returnedInterchanges!=null){
                HouseholdWaterSurveyActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    interchange.setPositionOnRecyler(positionOnRecyler);
                    HouseholdWaterSurveyActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
                Collections.sort(HouseholdWaterSurveyActivity.interchanges);
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
                InterchangeUtils.showInvalidSurveyAlert(invalideInterchanges,HouseholdWaterSurveyActivity.this);
            }else{
                Log.i("Tutorial", "we are going to save!");
                String lat_ = "";
                String lng_ = "";
                if(lat!=null&&lng!=null){
                    lat_= lat;
                    lng_ = lng;
                }else{
                    //try and get it again
                    PhoneLocation phoneLocation = new PhoneLocation(HouseholdWaterSurveyActivity.this);
                    String[] arraylatlng = phoneLocation.getLocation();
                    if(arraylatlng!=null){
                        lat_ = arraylatlng[0];
                        lng_ = arraylatlng[1];
                    }
                }

                //make an HouseholdWaterTest object
                HouseholdWaterTest householdWaterTestToSave = makeHouseholdWaterTestObject(interchangesWithUserAns,1,lat_,lng_);

                //save the HouseholdWaterTest object
                saveHouseholdWaterTestSurvey(householdWaterTestToSave);

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
                PhoneLocation phoneLocation = new PhoneLocation(HouseholdWaterSurveyActivity.this);
                String[] arraylatlng = phoneLocation.getLocation();
                if(arraylatlng!=null){
                    lat_ = arraylatlng[0];
                    lng_ = arraylatlng[1];
                }
            }
            //make an HouseholdWaterTest object
            HouseholdWaterTest householdWaterTestToSave = makeHouseholdWaterTestObject(interchangesWithUserAns,0,lat_,lng_);

            //save the HouseholdWaterTest object
            saveHouseholdWaterTestSurvey(householdWaterTestToSave);

        }
        return super.onOptionsItemSelected(item);
    }

    SubscriptionToken checkToken = null;
    private void saveHouseholdWaterTestSurvey(HouseholdWaterTest householdWaterTestToSave) {
        checkToken = Amplify.Hub.subscribe(
                HubChannel.DATASTORE,
                hubEvent -> DataStoreChannelEventName.OUTBOX_MUTATION_ENQUEUED.toString().equals(hubEvent.getName()),
                hubEvent -> {
                    OutboxMutationEvent event = (OutboxMutationEvent) hubEvent.getData();
                    if(event!=null && event.getModelName().contentEquals("HouseholdWaterTest")){
                        if(event.getElement().getModel().getId().contentEquals(householdWaterTestToSave.getId())){
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
                                    for(Interchange interchange: HouseholdWaterSurveyActivity.interchanges){
                                        interchange.getAnswer().setAns(null);
                                    }
                                    HouseholdWaterSurveyActivity.this.finish();
                                }
                            });
                        }
                    }else{
                        runOnUiThread(new Runnable() {
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                //reset all the user answers
                                for(Interchange interchange: HouseholdWaterSurveyActivity.interchanges){
                                    interchange.getAnswer().setAns(null);
                                }
                                HouseholdWaterSurveyActivity.this.finish();
                            }
                        });
                    }
                }
        );
        Amplify.DataStore.save(householdWaterTestToSave,
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

    }

    private void showSavedSuccessfulAlert(){
        if(checkToken!=null)
            Amplify.Hub.unsubscribe(checkToken);
        new AlertDialog.Builder(HouseholdWaterSurveyActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Household Water Test Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all the user answers
                        for(Interchange interchange: HouseholdWaterSurveyActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        HouseholdWaterSurveyActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(HouseholdWaterSurveyActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Household Water Test Save Failed! Please try again\n" )
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                        .setCanceledOnTouchOutside(false);
            }
        });
    }

    private HouseholdWaterTest makeHouseholdWaterTestObject(ArrayList<Interchange> interchangesWithUserAns,int completed, String lat, String lng) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = namebwe;
        String Country = (String) country;
        String Community = (String) community;
        String HeadHouseholdName = (String) householdName;
        Temporal.Date  ColilertDateTested = DateUtils.parseDateWithDefault(InterchangeUtils.getInterchangeAns("ColilertDateTested",interchangesWithUserAns));
        Temporal.Date ColilertDateRead = DateUtils.parseDateWithDefault(InterchangeUtils.getInterchangeAns("ColilertDateRead",interchangesWithUserAns));
        String ColilertTestResult = (String) InterchangeUtils.getInterchangeAns("ColilertTestResult",interchangesWithUserAns);
        Temporal.Date PetrifilmDateTested = DateUtils.parseDateWithDefault(InterchangeUtils.getInterchangeAns("PetrifilmDateTested",interchangesWithUserAns));
        Temporal.Date PetrifilmDateRead = DateUtils.parseDateWithDefault(InterchangeUtils.getInterchangeAns("PetrifilmDateRead",interchangesWithUserAns));
        String PetrifilmTestResult = (String) InterchangeUtils.getInterchangeAns("PetrifilmTestResult",interchangesWithUserAns);
        Temporal.Date ChlorineDateTested = DateUtils.parseDateWithDefault(InterchangeUtils.getInterchangeAns("ChlorineDateTested",interchangesWithUserAns));
        String ChlorineTestResult = (String) InterchangeUtils.getInterchangeAns("ChlorineTestResult",interchangesWithUserAns);
        Temporal.Date date = new Temporal.Date(date_s);

        HouseholdWaterTest householdWaterTest = HouseholdWaterTest.builder()
                .namebwe(Namebwe)
                .country(Country)
                .community(Community)
                .headHouseholdName(HeadHouseholdName)
                .colilertDateTested(ColilertDateTested)
                .colilertDateRead(ColilertDateRead)
                .colilertTestResult(ColilertTestResult)
                .petrifilmDateTested(PetrifilmDateTested)
                .petrifilmDateRead(PetrifilmDateRead)
                .petrifilmTestResult(PetrifilmTestResult)
                .chlorineDateTested(ChlorineDateTested)
                .chlorineTestResult(ChlorineTestResult)
                .completed(completed)
                .lat(lat)
                .lng(lng)
                .date(date)
                .build();
        return householdWaterTest;
    }

}
