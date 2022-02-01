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
import com.amplifyframework.datastore.generated.model.SWEMonthlySummary;
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

public class UpdateSWEMonthlySummaryActivity extends AppCompatActivity {
    SWEMonthlySummary theSWEMonthlySummary;
    private String uuidSWEMonthlySummary;
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
            uuidSWEMonthlySummary = getIntent().getStringExtra("UUID");

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createSWEMonthlySummaryUpdateInterchanges();
    }

    private void createSWEMonthlySummaryUpdateInterchanges() {
        interchanges = new ArrayList<>();
        createInterchangesAndShowOnRecyclerView();
    }

    private void createInterchangesAndShowOnRecyclerView() {
        Amplify.DataStore.query(
                SWEMonthlySummary.class,
                Where.matches(SWEMonthlySummary.ID.eq(uuidSWEMonthlySummary)),
                sweMonthlySummary -> {
                    Log.i("Tutorials", "DataStore is queried.");
                    while (sweMonthlySummary.hasNext()) {
                        theSWEMonthlySummary = sweMonthlySummary.next();
                        Log.i("Tutorials", "DataStore is queried. theSWEMonthlySummary " +theSWEMonthlySummary.getId());
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
        if(theSWEMonthlySummary!=null){
            ArrayList<Interchange> returnedInterchanges = BwfSurveyAmplifyApplication.getInterchanges("SWESUMMARY");
            if(returnedInterchanges!=null){
                UpdateSWEMonthlySummaryActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    //set answers
                    String answer = "";
                    String nameOfAns = interchange.getAnswer().getAnswerDef().getName();
                    String methodName = "get"+nameOfAns;
                    java.lang.reflect.Method method;
                    try {
                        method = theSWEMonthlySummary.getClass().getMethod(methodName);
                        Object ansObject = method.invoke(theSWEMonthlySummary);
                        if(ansObject!=null)
                            answer = ansObject.toString();
                    } catch (Exception e) {
                        Log.e("Tutorials", "Could not get answer " + nameOfAns);
                    }
                    interchange.getAnswer().setAns(answer);

                    interchange.setPositionOnRecyler(positionOnRecyler);
                    UpdateSWEMonthlySummaryActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
                //sort the interchanges
                Collections.sort(UpdateSWEMonthlySummaryActivity.interchanges);
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
        adapter = new InterchangeCardAdapter(UpdateSWEMonthlySummaryActivity.this, UpdateSWEMonthlySummaryActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for(Interchange interchange: UpdateSWEMonthlySummaryActivity.interchanges){
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
                    PhoneLocation phoneLocation = new PhoneLocation(UpdateSWEMonthlySummaryActivity.this);
                    String[] arraylatlng = phoneLocation.getLocation();
                    if(arraylatlng!=null){
                        lat_ = arraylatlng[0];
                        lng_ = arraylatlng[1];
                    }
                }
                //make an sweMonthlySummary object
                SWEMonthlySummary sweMonthlySummary = makeSWEMonthlySummaryObjectWithId(interchangesWithUserAns,1,lat_,lng_);

                //save the sweMonthlySummary object
                saveSWEMonthlySummary(sweMonthlySummary);
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
                PhoneLocation phoneLocation = new PhoneLocation(UpdateSWEMonthlySummaryActivity.this);
                String[] arraylatlng = phoneLocation.getLocation();
                if(arraylatlng!=null){
                    lat_ = arraylatlng[0];
                    lng_ = arraylatlng[1];
                }
            }
            //make an sweMonthlySummary object
            SWEMonthlySummary sweMonthlySummary = makeSWEMonthlySummaryObjectWithId(interchangesWithUserAns,0,lat_,lng_);

            //save the sweMonthlySummary object
            saveSWEMonthlySummary(sweMonthlySummary);

        }
        return super.onOptionsItemSelected(item);
    }

    private void saveSWEMonthlySummary(SWEMonthlySummary sweMonthlySummary) {
        Amplify.DataStore.save(sweMonthlySummary,
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
        new AlertDialog.Builder(UpdateSWEMonthlySummaryActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("SWE Monthly Summary Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all the user answers
                        for(Interchange interchange: UpdateSWEMonthlySummaryActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        UpdateSWEMonthlySummaryActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(UpdateSWEMonthlySummaryActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("SWE Monthly Summary Save Failed! Please try again\n" )
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                        .setCanceledOnTouchOutside(false);
            }
        });
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

    private void showInvalidSurveyAlert(){
        new AlertDialog.Builder(UpdateSWEMonthlySummaryActivity.this)
                .setTitle("Invalid Questions")
                .setMessage("Some questions have not been correctly answered \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private SWEMonthlySummary makeSWEMonthlySummaryObjectWithId(ArrayList<Interchange> interchangesWithUserAns,int completed, String lat, String lng) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = theSWEMonthlySummary.getNamebwe();
        String SwePosition = theSWEMonthlySummary.getSwePosition();
        Integer NoWaterSampleTaken = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoWaterSampleTaken",interchangesWithUserAns),0);
        Integer NoSurveysCompleted = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoSurveysCompleted",interchangesWithUserAns),0);
        Integer NoHealthCheck = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoHealthCheck",interchangesWithUserAns),0);
        Integer NoLsn1Taught = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn1Taught",interchangesWithUserAns),0);
        Integer NoPersonsTaughtLesson1 = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaughtLesson1",interchangesWithUserAns),0);
        Integer NoLsn2Taught = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn2Taught",interchangesWithUserAns),0);
        Integer NoPersonsTaughtLesson2 = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaughtLesson2",interchangesWithUserAns),0);
        Integer NoLsn3Taught = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn3Taught",interchangesWithUserAns),0);
        Integer NoPersonsTaughtLesson3 = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaughtLesson3",interchangesWithUserAns),0);
        Integer NoLsn4Taught = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn4Taught",interchangesWithUserAns),0);
        Integer NoPersonsTaughtLesson4 = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaughtLesson4",interchangesWithUserAns),0);
        Integer NoPersonsTaught = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaught",interchangesWithUserAns),0);
        Integer NoHouseholdReceivingChlorineSupplies = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoHouseholdReceivingChlorineSupplies",interchangesWithUserAns),0);
        Integer NoLiquidChlorineDistributed = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLiquidChlorineDistributed",interchangesWithUserAns),0);
        Integer NoChlorineTabletsDistributed = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoChlorineTabletsDistributed",interchangesWithUserAns),0);
        Integer NoWaterStorageContainersDistributed = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoWaterStorageContainersDistributed",interchangesWithUserAns),0);
        Integer NoSchoolVisits = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoSchoolVisits",interchangesWithUserAns),0);
        Integer NoPublicServiceMessagesAired = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPublicServiceMessagesAired",interchangesWithUserAns),0);
        Temporal.Date date = new Temporal.Date(date_s);

        SWEMonthlySummary sweMonthlySummary = SWEMonthlySummary.builder()
                .namebwe(Namebwe)
                .swePosition(SwePosition)
                .noWaterSampleTaken(NoWaterSampleTaken)
                .noSurveysCompleted(NoSurveysCompleted)
                .noHealthCheck(NoHealthCheck)
                .noLsn1Taught(NoLsn1Taught)
                .noPersonsTaughtLesson1(NoPersonsTaughtLesson1)
                .noLsn2Taught(NoLsn2Taught)
                .noPersonsTaughtLesson2(NoPersonsTaughtLesson2)
                .noLsn3Taught(NoLsn3Taught)
                .noPersonsTaughtLesson3(NoPersonsTaughtLesson3)
                .noLsn4Taught(NoLsn4Taught)
                .noPersonsTaughtLesson4(NoPersonsTaughtLesson4)
                .noPersonsTaught(NoPersonsTaught)
                .noHouseholdReceivingChlorineSupplies(NoHouseholdReceivingChlorineSupplies)
                .noLiquidChlorineDistributed(NoLiquidChlorineDistributed)
                .noChlorineTabletsDistributed(NoChlorineTabletsDistributed)
                .noWaterStorageContainersDistributed(NoWaterStorageContainersDistributed)
                .noSchoolVisits(NoSchoolVisits)
                .noPublicServiceMessagesAired(NoPublicServiceMessagesAired)
                .completed(completed)
                .lat(lat)
                .lng(lng)
                .date(date)
                .id(theSWEMonthlySummary.getId())
                .build();
        return sweMonthlySummary;

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
            if(foundInterchange!=null)
                ans = foundInterchange.getValidation().getDefaultValue();
        }
        return ans;
    }

    public static Temporal.Date parseDateWithDefault(Object s){
        Temporal.Date dateValue = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try{
            dateValue = new Temporal.Date(dateFormat.format(s));
        }catch (Exception x){
            dateValue = new Temporal.Date("1900-01-01");
        }
        return dateValue;
    }
}
