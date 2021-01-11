package com.bwfsurvey.bwfsurveybeta;

import android.os.Build;
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
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;
import java.util.Comparator;

public class ViewInitialSurveySelectActivity extends AppCompatActivity {
    private static ArrayList<InitialSurvey> listOfInitialSurveys;
    private RecyclerView recyclerView;
    private ViewInitialSurveySelectCardAdapter adapter;

    private LinearLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createInitialSurveyCardSelectList();
    }

    private void createInitialSurveyCardSelectList() {
        listOfInitialSurveys = new ArrayList<>();
        downloadInitialSurveyListAndShowOnRecyclerView();
    }

    private void downloadInitialSurveyListAndShowOnRecyclerView() {
        try{
            Amplify.DataStore.query(
                    InitialSurvey.class,
                    allInitialSurveyFamilys -> {
                        Log.i("Tutorials", "DataStore is queried.");
                        while (allInitialSurveyFamilys.hasNext()) {
                            InitialSurvey aFamily = allInitialSurveyFamilys.next();
                            listOfInitialSurveys.add(aFamily);
                            Log.i("Tutorials", "Title: " + aFamily.getHeadHouseholdName());
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            listOfInitialSurveys.sort(new Comparator<InitialSurvey>() {
                                @Override
                                public int compare(InitialSurvey o1, InitialSurvey o2) {
                                    return o1.getSurveyId() - o2.getSurveyId();
                                }
                            });
                        }
                        runOnUiThread(new Runnable() {
                            public void run() {
                                showListOfInitialSurveys();
                            }
                        });
                    },
                    failure ->{
                        Log.e("Tutorials", "Query failed.", failure);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                showErrorListOfInitialSurveys();
                            }
                        });
                    }
            );
        }catch (Exception x){
            //show less menu
            Log.i("Tutorials", "show less menu.");
            runOnUiThread(new Runnable() {
                public void run() {
                    showErrorListOfInitialSurveys();
                }
            });
        }
    }

    private void showListOfInitialSurveys() {
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
        adapter = new ViewInitialSurveySelectCardAdapter(ViewInitialSurveySelectActivity.this, ViewInitialSurveySelectActivity.listOfInitialSurveys);
        recyclerView.setAdapter(adapter);
    }

    private void showErrorListOfInitialSurveys() {
        if(progressBar!=null)
            progressBar.setVisibility(View.GONE);

        new AlertDialog.Builder(ViewInitialSurveySelectActivity.this)
                .setTitle("Loading List Failed")
                .setMessage("Loading List Failed, Please press the back button and try again\n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
