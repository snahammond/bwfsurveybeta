package com.bwfsurvey.bwfsurveybeta.activities.select;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.VolunteerHouseholdWaterTest;
import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.VolunteerHouseholdWaterTestCardAdapter;
import com.bwfsurvey.bwfsurveybeta.utils.ListUtils;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class VolunteerHouseholdWaterTestCardSelectActivity extends AppCompatActivity {
    private static ArrayList<VolunteerHouseholdWaterTest> listOfVolunteerHouseholdWaterTest;
    private RecyclerView recyclerView;
    private VolunteerHouseholdWaterTestCardAdapter adapter;
    private String namebwe = null;
    private String countrybwe = null;
    private String surveyType = null;
    private String operation = null;
    private String lat = null;
    private String lng = null;
    private LinearLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("NAME_BWE")!=null)
            namebwe = getIntent().getStringExtra("NAME_BWE");
        Log.i("Tutorials", "namebwe: " + namebwe);
        if(getIntent().getStringExtra("COUNTRY_BWE")!=null)
            countrybwe = getIntent().getStringExtra("COUNTRY_BWE");
        if(getIntent().getStringExtra("SURVEY_TYPE")!=null)
            surveyType = getIntent().getStringExtra("SURVEY_TYPE");
        if(getIntent().getStringExtra("OPERATION")!=null)
            operation = getIntent().getStringExtra("OPERATION");

        if(getIntent().getStringExtra("LAT")!=null)
            lat = getIntent().getStringExtra("LAT");
        if(getIntent().getStringExtra("LNG")!=null)
            lng = getIntent().getStringExtra("LNG");
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createUpdateVolunteerHouseholdWaterTestCardSelectList();
    }

    private void createUpdateVolunteerHouseholdWaterTestCardSelectList() {
        listOfVolunteerHouseholdWaterTest = new ArrayList<>();
        downloadVolunteerHouseholdWaterTestListAndShowOnRecyclerView();
    }

    private void downloadVolunteerHouseholdWaterTestListAndShowOnRecyclerView() {
        try{
            int completedL = 0;
            int completedR = 1;
            if(operation.contentEquals("CREATE")){
                completedL = 1;
                completedR = 1;
            }else if(operation.contentEquals("UPDATE")){
                completedL = 0;
                completedR = 0;
            }else if(operation.contentEquals("VIEW")){
                completedL = 0;
                completedR = 1;
            }
            Amplify.DataStore.query(
                    VolunteerHouseholdWaterTest.class,
                    Where.matches(VolunteerHouseholdWaterTest.COMPLETED.eq(completedL).or(VolunteerHouseholdWaterTest.COMPLETED.eq(completedR))),
                    allVolunteerHouseholdWaterTest -> {
                        Log.i("Tutorials", "DataStore is queried.");
                        while (allVolunteerHouseholdWaterTest.hasNext()) {
                            VolunteerHouseholdWaterTest aVolunteerHouseholdWaterTest = allVolunteerHouseholdWaterTest.next();
                            listOfVolunteerHouseholdWaterTest.add(aVolunteerHouseholdWaterTest);
                            Log.i("Tutorials", "Title: " + aVolunteerHouseholdWaterTest.getHeadHouseholdName());
                        }
                        runOnUiThread(new Runnable() {
                            public void run() {
                                showListOflistOfHouseholdWaterTests();
                            }
                        });
                    },
                    failure ->{
                        Log.e("Tutorials", "Query failed.", failure);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                //showErrorListOfFollowUpSurveys();
                            }
                        });
                    }
            );
        }catch (Exception x){
            //show less menu
            Log.i("Tutorials", "show less menu.");
            runOnUiThread(new Runnable() {
                public void run() {
                    //showErrorListOfFollowUpSurveys();
                }
            });
        }
    }

    private void showListOflistOfHouseholdWaterTests() {
        if(listOfVolunteerHouseholdWaterTest.size()>0){
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
        }else{
            String msg = "No data for Volunteer household water test found.";
            if(operation.contentEquals("UPDATE")){
                msg = "No suspended data for Volunteer household water test found.";
            }else if(operation.contentEquals("VIEW")){
                msg = "No data for Volunteer household water test found.";
            }
            ListUtils.showZeroListAlert(msg,VolunteerHouseholdWaterTestCardSelectActivity.this);
        }

    }

    private void initViewElements() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VolunteerHouseholdWaterTestCardAdapter(VolunteerHouseholdWaterTestCardSelectActivity.this, VolunteerHouseholdWaterTestCardSelectActivity.listOfVolunteerHouseholdWaterTest,namebwe,countrybwe,surveyType,operation,lat,lng);
        recyclerView.setAdapter(adapter);
    }
}
