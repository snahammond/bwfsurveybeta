package com.bwfsurvey.bwfsurveybeta.activities.select;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.InitialSurvey;
import com.bwfsurvey.bwfsurveybeta.adapters.CommunityCardAdapter;
import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.dialogs.SelectCountryDialogFragment;
import com.bwfsurvey.bwfsurveybeta.types.Community;
import com.bwfsurvey.bwfsurveybeta.utils.ListUtils;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class CommunityCardSelectActivity extends AppCompatActivity {
    private static ArrayList<Community> listOfCommunities;
    private RecyclerView recyclerView;
    private CommunityCardAdapter adapter;

    private String namebwe = null;
    private String countrybwe = null;
    private String positionbwe = null;
    private String surveyType = null;
    private String lat = null;
    private String lng = null;
    private int surveyIdForInitialSurvey = 0;

    private LinearLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("NAME_BWE")!=null)
            namebwe = getIntent().getStringExtra("NAME_BWE");
        if(getIntent().getStringExtra("COUNTRY_BWE")!=null)
            countrybwe = getIntent().getStringExtra("COUNTRY_BWE");
        if(getIntent().getStringExtra("POSITION_BWE")!=null)
            positionbwe = getIntent().getStringExtra("POSITION_BWE");
        Log.i("Tutorials", "namebwe: " + namebwe);
        if(getIntent().getStringExtra("SURVEY_TYPE")!=null)
            surveyType = getIntent().getStringExtra("SURVEY_TYPE");
        if(getIntent().getStringExtra("LAT")!=null)
            lat = getIntent().getStringExtra("LAT");
        if(getIntent().getStringExtra("LNG")!=null)
            lng = getIntent().getStringExtra("LNG");

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        //if surveytype is initial survey we need to get the surveyId, so lets query for it before we start anything
        if(surveyType.contentEquals("INITIALSURVEY")){
            Amplify.DataStore.query(
                    InitialSurvey.class,
                    allInitialSurveyFamilys -> {
                        Log.i("Tutorials", "DataStore is queried.");
                        int numberOfInitialSurvey = 0;
                        while (allInitialSurveyFamilys.hasNext()) {
                            allInitialSurveyFamilys.next();
                            numberOfInitialSurvey++;
                            Log.i("Tutorials", "numberOfInitialSurvey: " + numberOfInitialSurvey);
                        }
                        numberOfInitialSurvey++;
                        Log.i("Tutorials", "numberOfInitialSurvey actual: " + numberOfInitialSurvey);
                        surveyIdForInitialSurvey = numberOfInitialSurvey;
                        runOnUiThread(new Runnable() {
                            public void run() {
                                //save the initialSurvey object
                                progressBar = (LinearLayout) findViewById(R.id.llProgressBar);
                                TextView progressBarText = (TextView) findViewById(R.id.pbText);
                                progressBarText.setText("Please wait... Setting Up!");
                                progressBar.setVisibility(View.VISIBLE);
                                CountDownTimer countDownTimer = new CountDownTimer(BwfSurveyAmplifyApplication.manualTimer,1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                    }

                                    @Override
                                    public void onFinish() {
                                        progressBar.setVisibility(View.GONE);
                                        createCommunityCardSelectList();
                                    }
                                };
                                countDownTimer.start();
                            }
                        });
                    },
                    failure ->{
                        Log.e("Tutorials", "numberOfInitialSurvey Query failed.", failure);
                        surveyIdForInitialSurvey = 0;
                        runOnUiThread(new Runnable() {
                            public void run() {
                                //save the initialSurvey object
                                progressBar = (LinearLayout) findViewById(R.id.llProgressBar);
                                TextView progressBarText = (TextView) findViewById(R.id.pbText);
                                progressBarText.setText("Please wait... Setting Up!");
                                progressBar.setVisibility(View.VISIBLE);
                                CountDownTimer countDownTimer = new CountDownTimer(BwfSurveyAmplifyApplication.manualTimer,1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                    }

                                    @Override
                                    public void onFinish() {
                                        progressBar.setVisibility(View.GONE);
                                        createCommunityCardSelectList();
                                    }
                                };
                                countDownTimer.start();
                            }
                        });
                    }
            );
        }else{
            createCommunityCardSelectList();
        }

    }

    private void createCommunityCardSelectList() {
        listOfCommunities = new ArrayList<>();
        getCommunityListAndShowOnRecyclerView();
    }

    private void getCommunityListAndShowOnRecyclerView() {
        if(countrybwe!=null && countrybwe!=""){
            listOfCommunities = BwfSurveyAmplifyApplication.getCommunities(countrybwe);
            showListOfCommunities();
        }
        else{
            //ask the use to select the country name, because we do not have it
            ArrayList<String> listOfCountries = BwfSurveyAmplifyApplication.getCountries();
            DialogFragment dialog = new SelectCountryDialogFragment(listOfCountries, new SelectCountryDialogFragment.SelectCountryDialogFragmentListener() {
                @Override
                public void onSelectedCountry(String countryName) {
                    countrybwe = countryName;
                    listOfCommunities = BwfSurveyAmplifyApplication.getCommunities(countrybwe);
                    showListOfCommunities();
                }
            });
            dialog.show(getSupportFragmentManager(), "countries");
        }


    }

    private void showListOfCommunities() {
        if(listOfCommunities!=null &&listOfCommunities.size()>0){
            initViewElements();
        }else{
            ListUtils.showZeroListAlert("No community can be found for the current country; " + countrybwe +". Please change the current country on the main menu page.",CommunityCardSelectActivity.this);
        }
    }

    private void initViewElements() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommunityCardAdapter(CommunityCardSelectActivity.this, CommunityCardSelectActivity.listOfCommunities,namebwe,positionbwe,surveyType,surveyIdForInitialSurvey,lat,lng);
        recyclerView.setAdapter(adapter);
    }


}
