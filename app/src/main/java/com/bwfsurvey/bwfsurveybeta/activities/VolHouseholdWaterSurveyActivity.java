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
import com.amplifyframework.datastore.generated.model.HouseholdWaterTest;
import com.amplifyframework.datastore.generated.model.VolunteerHouseholdWaterTest;
import com.bwfsurvey.bwfsurveybeta.MyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.InterchangeCardAdapter;
import com.bwfsurvey.bwfsurveybeta.types.Interchange;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class VolHouseholdWaterSurveyActivity extends AppCompatActivity {
    private String namebwe = null;
    private String surveyType = null;
    private String country = null;
    private String community = null;
    private String householdName = null;
    private String householdLocation = null;
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
        if(getIntent().getStringExtra("HHLOC")!=null)
            householdLocation = getIntent().getStringExtra("HHLOC");

        if(getIntent().getStringExtra("SURVEY_ID")!=null){
            String surveyIdStr = getIntent().getStringExtra("SURVEY_ID");
            surveyId = Integer.parseInt(surveyIdStr);
        }

        Log.i("Tutorials", "Selected family water survey household class: " + householdName +" country: "+ country + " community: "+community + "surveyId: " + surveyId);
        setContentView(R.layout.activity_recycler);
        getSupportActionBar().setTitle((CharSequence) "Water Survey; "+householdName);
        initView();
    }

    private void initView() {
        createHouseholdWaterSurveyQuestionaire();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InterchangeCardAdapter(VolHouseholdWaterSurveyActivity.this, VolHouseholdWaterSurveyActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }

    private void createHouseholdWaterSurveyQuestionaire() {
        try{
            ArrayList<Interchange> returnedInterchanges = MyAmplifyApplication.getInterchanges(surveyType);
            if(returnedInterchanges!=null){
                VolHouseholdWaterSurveyActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    interchange.setPositionOnRecyler(positionOnRecyler);
                    VolHouseholdWaterSurveyActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
                Collections.sort(VolHouseholdWaterSurveyActivity.interchanges);
                Log.i("Tutorial", "VolHouseholdWaterSurveyActivity interchanges length " + VolHouseholdWaterSurveyActivity.interchanges.size());
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
                Log.i("Tutorial", "we are going to save!");

                //make an InitialSurvey object
                VolunteerHouseholdWaterTest volHouseholdWaterTestToSave = makeHouseholdWaterTestObject(interchangesWithUserAns,1,"","");

                //save the initialSurvey object
                saveHouseholdWaterTestSurvey(volHouseholdWaterTestToSave);

            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveHouseholdWaterTestSurvey(VolunteerHouseholdWaterTest volHouseholdWaterTestToSave) {
        Amplify.DataStore.save(volHouseholdWaterTestToSave,
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

    private void showSavedSuccessfulAlert(){
        new AlertDialog.Builder(VolHouseholdWaterSurveyActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Household Water Test Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all the user answers
                        for(Interchange interchange: VolHouseholdWaterSurveyActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        VolHouseholdWaterSurveyActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(VolHouseholdWaterSurveyActivity.this)
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

    private VolunteerHouseholdWaterTest makeHouseholdWaterTestObject(ArrayList<Interchange> interchangesWithUserAns,int completed, String lat, String lng) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = namebwe;
        String Country = (String) country;
        String Community = (String) community;
        String HeadHouseholdName = (String) householdName;
        String HouseholdLocation = (String) householdLocation;
        Temporal.Date  ColilertDateTested = new Temporal.Date(dateFormat.format(getInterchangeAns("ColilertDateTested",interchangesWithUserAns)));
        Temporal.Date ColilertDateRead = new Temporal.Date(dateFormat.format(getInterchangeAns("ColilertDateRead",interchangesWithUserAns)));
        String ColilertTestResult = (String) getInterchangeAns("ColilertTestResult",interchangesWithUserAns);
        Temporal.Date PetrifilmDateTested = new Temporal.Date(dateFormat.format(getInterchangeAns("PetrifilmDateTested",interchangesWithUserAns)));
        Temporal.Date PetrifilmDateRead = new Temporal.Date(dateFormat.format(getInterchangeAns("PetrifilmDateRead",interchangesWithUserAns)));
        String PetrifilmTestResult = (String) getInterchangeAns("PetrifilmTestResult",interchangesWithUserAns);
        Temporal.Date date = new Temporal.Date(date_s);

        VolunteerHouseholdWaterTest volHouseholdWaterTest = VolunteerHouseholdWaterTest.builder()
                .namebwe(Namebwe)
                .namevol("")
                .country(Country)
                .community(Community)
                .headHouseholdName(HeadHouseholdName)
                .householdLocation("")
                .colilertDateTested(ColilertDateTested)
                .colilertDateRead(ColilertDateRead)
                .colilertTestResult(ColilertTestResult)
                .petrifilmDateTested(PetrifilmDateTested)
                .petrifilmDateRead(PetrifilmDateRead)
                .petrifilmTestResult(PetrifilmTestResult)
                .completed(completed)
                .lat(lat)
                .lng(lng)
                .date(date)
                .build();
        return volHouseholdWaterTest;
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
        new AlertDialog.Builder(VolHouseholdWaterSurveyActivity.this)
                .setTitle("Invalid Questions")
                .setMessage("Some questions have not been correctly answered \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
                .setCanceledOnTouchOutside(false);
    }

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
