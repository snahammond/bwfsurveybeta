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

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.SWEMonthlyClinicSummary;
import com.amplifyframework.datastore.generated.model.SWEMonthlySchoolSummary;
import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.SWEMonthlyClinicSummaryCardAdapter;
import com.bwfsurvey.bwfsurveybeta.adapters.SWEMonthlySchoolSummaryCardAdapter;
import com.bwfsurvey.bwfsurveybeta.utils.ListUtils;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class SWEMonthlyClinicSummaryCardSelectActivity extends AppCompatActivity {
    private static ArrayList<SWEMonthlyClinicSummary> listOfSWEMonthlyClinicSummarys;
    private SWEMonthlyClinicSummaryCardAdapter adapter;

    private String namebwe = null;
    private String countrybwe = null;
    private String surveyType = null;
    private String operation = null;
    private String lat = null;
    private String lng = null;

    private LinearLayout progressBar;
    private RecyclerView recyclerView;

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
        createSWEMonthlyClinicSummaryCardSelectList();
    }

    private void createSWEMonthlyClinicSummaryCardSelectList() {
        listOfSWEMonthlyClinicSummarys = new ArrayList<>();
        downloadSWEMonthlyClinicSummaryListAndShowOnRecyclerView();
    }

    private void downloadSWEMonthlyClinicSummaryListAndShowOnRecyclerView() {
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
                    SWEMonthlyClinicSummary.class,
                    Where.matches(SWEMonthlyClinicSummary.COMPLETED.eq(completedL).or(SWEMonthlyClinicSummary.COMPLETED.eq(completedR))),
                    allSWEMonthlyClinicSummarys -> {
                        Log.i("Tutorials", "DataStore is queried.");
                        while (allSWEMonthlyClinicSummarys.hasNext()) {
                            SWEMonthlyClinicSummary aSWEMonthlyClinicSummary = allSWEMonthlyClinicSummarys.next();
                            listOfSWEMonthlyClinicSummarys.add(aSWEMonthlyClinicSummary);
                            Log.i("Tutorials", "Title: " + aSWEMonthlyClinicSummary.getNamebwe());
                            //try to send all the InitialSurveys by forcefully pushing
                            Amplify.API.mutate(
                                    ModelMutation.create(aSWEMonthlyClinicSummary),
                                    response -> {
                                    },
                                    error -> {
                                    }
                            );
                        }

                        runOnUiThread(() -> showListOfSWEMonthlyClinicSummarys());
                    },
                    failure ->{
                        Log.e("Tutorials", "Query failed.", failure);
                        runOnUiThread(() -> {
                            //showErrorListOfFollowUpSurveys();
                        });
                    }
            );
        }catch (Exception x){
            //show less menu
            Log.i("Tutorials", "show less menu.");
            runOnUiThread(() -> {
                //showErrorListOfFollowUpSurveys();
            });
        }
    }

    private void showListOfSWEMonthlyClinicSummarys() {
        if(listOfSWEMonthlyClinicSummarys.size()>0){
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
            String msg = "No data for SWE Monthly Clinic Summary found.";
            if(operation.contentEquals("UPDATE")){
                msg = "No suspended data for SWE Monthly Clinic Summary found.";
            }else if(operation.contentEquals("VIEW")){
                msg = "No data for SWE Monthly Clinic Summary found.";
            }
            ListUtils.showZeroListAlert(msg,SWEMonthlyClinicSummaryCardSelectActivity.this);
        }

    }

    private void initViewElements() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SWEMonthlyClinicSummaryCardAdapter(SWEMonthlyClinicSummaryCardSelectActivity.this, listOfSWEMonthlyClinicSummarys,namebwe,countrybwe,surveyType,operation,lat,lng);
        recyclerView.setAdapter(adapter);
    }
}
