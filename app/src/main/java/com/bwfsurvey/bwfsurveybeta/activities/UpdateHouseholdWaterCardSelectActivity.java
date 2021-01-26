package com.bwfsurvey.bwfsurveybeta.activities;

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
import com.amplifyframework.datastore.generated.model.CommunityWaterTest;
import com.amplifyframework.datastore.generated.model.HouseholdWaterTest;
import com.bwfsurvey.bwfsurveybeta.adapters.CommunityWaterTestCardAdapter;
import com.bwfsurvey.bwfsurveybeta.adapters.HouseholdWaterTestCardAdapter;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class UpdateHouseholdWaterCardSelectActivity extends AppCompatActivity {
    private static ArrayList<HouseholdWaterTest> listOfHouseholdWaterTest;
    private RecyclerView recyclerView;
    private HouseholdWaterTestCardAdapter adapter;
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
        createUpdateHouseholdWaterTestCardSelectList();
    }

    private void createUpdateHouseholdWaterTestCardSelectList() {
        listOfHouseholdWaterTest = new ArrayList<>();
        downloadHouseholdWaterTestListAndShowOnRecyclerView();
    }

    private void downloadHouseholdWaterTestListAndShowOnRecyclerView() {
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
                    HouseholdWaterTest.class,
                    Where.matches(HouseholdWaterTest.COMPLETED.eq(completedL).or(HouseholdWaterTest.COMPLETED.eq(completedR))),
                    allHouseholdWaterTest -> {
                        Log.i("Tutorials", "DataStore is queried.");
                        while (allHouseholdWaterTest.hasNext()) {
                            HouseholdWaterTest aHouseholdWaterTest = allHouseholdWaterTest.next();
                            listOfHouseholdWaterTest.add(aHouseholdWaterTest);
                            Log.i("Tutorials", "Title: " + aHouseholdWaterTest.getHeadHouseholdName());
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
        adapter = new HouseholdWaterTestCardAdapter(UpdateHouseholdWaterCardSelectActivity.this, UpdateHouseholdWaterCardSelectActivity.listOfHouseholdWaterTest,namebwe,countrybwe,surveyType,operation,lat,lng);
        recyclerView.setAdapter(adapter);
    }
}
