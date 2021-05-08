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
import com.amplifyframework.datastore.generated.model.VolunteerMonthlySummary;
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

public class VolunteerMonthlySummaryActivity extends AppCompatActivity {
    private String namebwe = null;
    private String positionbwe = null;
    private String volunteerName = null;

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
        if(getIntent().getStringExtra("POSITION_BWE")!=null)
            positionbwe = getIntent().getStringExtra("POSITION_BWE");
        if(getIntent().getStringExtra("NAME_VOL")!=null)
            volunteerName = getIntent().getStringExtra("NAME_VOL");

        if(getIntent().getStringExtra("LAT")!=null)
            lat = getIntent().getStringExtra("LAT");
        if(getIntent().getStringExtra("LNG")!=null)
            lng = getIntent().getStringExtra("LNG");

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);

        createMonthlySummaryQuestionaire();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InterchangeCardAdapter(VolunteerMonthlySummaryActivity.this, VolunteerMonthlySummaryActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }

    private void createMonthlySummaryQuestionaire() {
        try{
            ArrayList<Interchange> returnedInterchanges = BwfSurveyAmplifyApplication.getInterchanges("VOLUNTEERSUMMARY");
            if(returnedInterchanges!=null){
                VolunteerMonthlySummaryActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    interchange.setPositionOnRecyler(positionOnRecyler);
                    VolunteerMonthlySummaryActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
                //sort the interchanges
                Collections.sort(VolunteerMonthlySummaryActivity.interchanges);
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
                InterchangeUtils.showInvalidSurveyAlert(invalideInterchanges,VolunteerMonthlySummaryActivity.this);
            }else{
                String lat_ = "";
                String lng_ = "";
                if(lat!=null&&lng!=null){
                    lat_= lat;
                    lng_ = lng;
                }else{
                    //try and get it again
                    PhoneLocation phoneLocation = new PhoneLocation(VolunteerMonthlySummaryActivity.this);
                    String[] arraylatlng = phoneLocation.getLocation();
                    if(arraylatlng!=null){
                        lat_ = arraylatlng[0];
                        lng_ = arraylatlng[1];
                    }
                }
                //make an InitialSurvey object
                VolunteerMonthlySummary volunteerMonthlySummary = makeVolunteerMonthlySummaryObject(interchangesWithUserAns,1,lat_,lng_);
                //save the initialSurvey object
                saveVolunteerMonthlySummary(volunteerMonthlySummary);

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
                PhoneLocation phoneLocation = new PhoneLocation(VolunteerMonthlySummaryActivity.this);
                String[] arraylatlng = phoneLocation.getLocation();
                if(arraylatlng!=null){
                    lat_ = arraylatlng[0];
                    lng_ = arraylatlng[1];
                }
            }
            //make an InitialSurvey object
            VolunteerMonthlySummary volunteerMonthlySummary = makeVolunteerMonthlySummaryObject(interchangesWithUserAns,0,lat_,lng_);
            //save the initialSurvey object
            saveVolunteerMonthlySummary(volunteerMonthlySummary);


        }
        return super.onOptionsItemSelected(item);
    }

    private void saveVolunteerMonthlySummary(VolunteerMonthlySummary volunteerMonthlySummary) {
        Amplify.DataStore.save(volunteerMonthlySummary,
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

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(VolunteerMonthlySummaryActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Monthly Summary Save Failed! Please try again\n" )
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                        .setCanceledOnTouchOutside(false);
            }
        });
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
        new AlertDialog.Builder(VolunteerMonthlySummaryActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Monthly Summary Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all the user answers
                        for(Interchange interchange: VolunteerMonthlySummaryActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        VolunteerMonthlySummaryActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private VolunteerMonthlySummary makeVolunteerMonthlySummaryObject(ArrayList<Interchange> interchangesWithUserAns,int completed, String lat, String lng) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = namebwe;
        String SwePosition = positionbwe;
        String NameVolunteer = volunteerName;
        Integer NoWaterSampleTaken = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoWaterSampleTaken",interchangesWithUserAns),0);
        Integer NoLsn1TaughtAsPrimaryInstr = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn1TaughtAsPrimaryInstr",interchangesWithUserAns),0);
        Integer NoLsn1TaughtAssistingSWE = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn1TaughtAssistingSWE",interchangesWithUserAns),0);
        Integer NoPersonsTaughtLesson1ByVol = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaughtLesson1ByVol",interchangesWithUserAns),0);
        Integer NoLsn2TaughtAsPrimaryInstr = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn2TaughtAsPrimaryInstr",interchangesWithUserAns),0);
        Integer NoLsn2TaughtAssistingSWE = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn2TaughtAssistingSWE",interchangesWithUserAns),0);
        Integer NoPersonsTaughtLesson2ByVol = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaughtLesson2ByVol",interchangesWithUserAns),0);
        Integer NoLsn3TaughtAsPrimaryInstr = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn3TaughtAsPrimaryInstr",interchangesWithUserAns),0);
        Integer NoLsn3TaughtAssistingSWE = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn3TaughtAssistingSWE",interchangesWithUserAns),0);
        Integer NoPersonsTaughtLesson3ByVol = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaughtLesson3ByVol",interchangesWithUserAns),0);
        Integer NoLsn4TaughtAsPrimaryInstr = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn4TaughtAsPrimaryInstr",interchangesWithUserAns),0);
        Integer NoLsn4TaughtAssistingSWE = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn4TaughtAssistingSWE",interchangesWithUserAns),0);
        Integer NoPersonsTaughtLesson4ByVol = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaughtLesson4ByVol",interchangesWithUserAns),0);
        Integer NoPersonsTaughtByVol = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaughtByVol",interchangesWithUserAns),0);
        Temporal.Date date = new Temporal.Date(date_s);

        VolunteerMonthlySummary volunteerMonthlySummary = VolunteerMonthlySummary.builder()
                .namebwe(Namebwe)
                .namevol(NameVolunteer)
                .noWaterSampleTaken(NoWaterSampleTaken)
                .noLsn1TaughtAsPrimaryInstr(NoLsn1TaughtAsPrimaryInstr)
                .noLsn1TaughtAssistingSwe(NoLsn1TaughtAssistingSWE)
                .noPersonsTaughtLesson1ByVol(NoPersonsTaughtLesson1ByVol)
                .noLsn2TaughtAsPrimaryInstr(NoLsn2TaughtAsPrimaryInstr)
                .noLsn2TaughtAssistingSwe(NoLsn2TaughtAssistingSWE)
                .noPersonsTaughtLesson2ByVol(NoPersonsTaughtLesson2ByVol)
                .noLsn3TaughtAsPrimaryInstr(NoLsn3TaughtAsPrimaryInstr)
                .noLsn3TaughtAssistingSwe(NoLsn3TaughtAssistingSWE)
                .noPersonsTaughtLesson3ByVol(NoPersonsTaughtLesson3ByVol)
                .noLsn4TaughtAsPrimaryInstr(NoLsn4TaughtAsPrimaryInstr)
                .noLsn4TaughtAssistingSwe(NoLsn4TaughtAssistingSWE)
                .noPersonsTaughtLesson4ByVol(NoPersonsTaughtLesson4ByVol)
                .noPersonsTaughtByVol(NoPersonsTaughtByVol)
                .completed(completed)
                .lat(lat)
                .lng(lng)
                .date(date)
                .build();
        return volunteerMonthlySummary;
    }

}
