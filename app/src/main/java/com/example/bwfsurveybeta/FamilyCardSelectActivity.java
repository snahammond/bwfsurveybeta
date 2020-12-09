package com.example.bwfsurveybeta;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.InitialSurvey;

import java.util.ArrayList;

public class FamilyCardSelectActivity extends AppCompatActivity {
    private static ArrayList<InitialSurvey> listOfFamilys;
    private RecyclerView recyclerView;
    private FamilyCardAdapter adapter;
    private String namebwe = null;
    private String surveyType = null;

    private LinearLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("NAME_BWE")!=null)
            namebwe = getIntent().getStringExtra("NAME_BWE");
        Log.i("Tutorials", "namebwe: " + namebwe);
        if(getIntent().getStringExtra("SURVEY_TYPE")!=null)
            surveyType = getIntent().getStringExtra("SURVEY_TYPE");
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createFamilyCardSelectList();
    }

    private void createFamilyCardSelectList(){
        listOfFamilys = new ArrayList<>();
        downloadInitialSurveyListAndShowOnRecyclerView();
    }

    public void downloadInitialSurveyListAndShowOnRecyclerView(){
        try{
            Amplify.DataStore.query(
                    InitialSurvey.class,
                    allInitialSurveyFamilys -> {
                        Log.i("Tutorials", "DataStore is queried.");
                        while (allInitialSurveyFamilys.hasNext()) {
                            InitialSurvey aFamily = allInitialSurveyFamilys.next();
                            listOfFamilys.add(aFamily);
                            Log.i("Tutorials", "Title: " + aFamily.getHeadHouseholdName());
                        }

                        runOnUiThread(new Runnable() {
                            public void run() {
                                showListOfFamilys();
                            }
                        });
                    },
                    failure ->{
                        Log.e("Tutorials", "Query failed.", failure);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                showErrorListOfFamilys();
                            }
                        });
                    }
            );
        }catch (Exception x){
            //show less menu
            Log.i("Tutorials", "show less menu.");
            runOnUiThread(new Runnable() {
                public void run() {
                    showErrorListOfFamilys();
                }
            });
        }

    }

    private void showListOfFamilys() {
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
        adapter = new FamilyCardAdapter(FamilyCardSelectActivity.this, FamilyCardSelectActivity.listOfFamilys,namebwe,surveyType);
        recyclerView.setAdapter(adapter);
    }

    private void showErrorListOfFamilys() {
        if(progressBar!=null)
            progressBar.setVisibility(View.GONE);

        new AlertDialog.Builder(FamilyCardSelectActivity.this)
                .setTitle("Loading List Failed")
                .setMessage("Loading List Failed, Please press the back button and try again\n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
