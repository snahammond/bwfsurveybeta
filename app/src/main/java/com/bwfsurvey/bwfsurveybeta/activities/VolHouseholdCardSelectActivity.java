package com.bwfsurvey.bwfsurveybeta.activities;

import android.content.DialogInterface;
import android.os.Build;
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
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.ConfigDefinitions;
import com.amplifyframework.datastore.generated.model.InitialSurvey;
import com.amplifyframework.datastore.generated.model.VolunteerHousehold;
import com.amplifyframework.datastore.generated.model.VolunteerHouseholdWaterTest;
import com.bwfsurvey.bwfsurveybeta.MyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.HouseholdCardAdapter;
import com.bwfsurvey.bwfsurveybeta.adapters.VolunteerHouseholdCardAdapter;
import com.bwfsurvey.bwfsurveybeta.dialogs.ConfirmSignUp;
import com.bwfsurvey.bwfsurveybeta.dialogs.CreateNewVolunteerHousehold;
import com.bwfsurvey.bwfsurveybeta.dialogs.SelectCountryDialogFragment;
import com.bwfsurvey.bwfsurveybeta.types.Community;
import com.bwfsurvey.bwfsurveybeta.types.Config;
import com.bwfsurvey.bwfsurveybeta.types.Interchange;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;
import java.util.Comparator;

public class VolHouseholdCardSelectActivity extends AppCompatActivity implements CreateNewVolunteerHousehold.CreateNewVolunteerHouseholdListener{
    private static ArrayList<VolunteerHousehold> listOfVolHouseholds;
    private RecyclerView recyclerView;
    private VolunteerHouseholdCardAdapter adapter;
    private String namebwe = null;
    private String countrybwe = null;
    private String surveyType = null;
    private String operation = null;

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
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createVolHouseholdCardSelectList();
    }

    private void createVolHouseholdCardSelectList() {
        downloadVolunteerHouseholdListAndShowOnRecyclerView();
    }

    private void downloadVolunteerHouseholdListAndShowOnRecyclerView() {
        Log.i("Tutorials", "going to downloadVolunteerHouseholdListAndShowOnRecyclerView");
        try{

            listOfVolHouseholds = new ArrayList<>();
            Amplify.DataStore.query(
                    VolunteerHousehold.class,
                    allVolunteerHouseholds -> {
                        Log.i("Tutorials", "DataStore is queried.");
                        while (allVolunteerHouseholds.hasNext()) {
                            VolunteerHousehold aVolHousehold = allVolunteerHouseholds.next();
                            listOfVolHouseholds.add(aVolHousehold);
                            Log.i("Tutorials", "Title: " + aVolHousehold.getHeadHouseholdName());
                        }
                        Log.i("Tutorials", "Queried");
                        runOnUiThread(new Runnable() {
                            public void run() {
                                showListOfVolHouseholds();
                            }
                        });
                    },
                    failure ->{
                        Log.e("Tutorials", "Query failed.", failure);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                //showErrorListOfFamilys();
                            }
                        });
                    }
            );
        }catch (Exception x){
            //show less menu
            Log.e("Tutorials", "No data to show", x );
            runOnUiThread(new Runnable() {
                public void run() {
                    //showErrorListOfFamilys();
                }
            });
        }
    }

    private void showListOfVolHouseholds() {
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
        adapter = new VolunteerHouseholdCardAdapter(VolHouseholdCardSelectActivity.this, VolHouseholdCardSelectActivity.listOfVolHouseholds,namebwe,countrybwe,surveyType,operation);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.menu_volunteer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.newHousehold) {
            Log.i("Tutorials", "going to get communities" );

            ArrayList<Community> listOfCommunities = MyAmplifyApplication.getCommunities(countrybwe);
            showCreateNewVolunteerHousehold(listOfCommunities);

            /*
            DialogFragment dialog = new SelectCountryDialogFragment(listOfCountries, new SelectCountryDialogFragment.SelectCountryDialogFragmentListener() {
                @Override
                public void onSelectedCountry(String countryName) {
                    countrybwe = countryName;
                    Log.i("Tutorials", "country selected is " + countrybwe );
                }
            });
            dialog.show(getSupportFragmentManager(), "countries");
             */
        }

        return super.onOptionsItemSelected(item);
    }

    private DialogFragment createNewVolunteerHousehold;
    public void showCreateNewVolunteerHousehold(ArrayList<Community> communities) {
        createNewVolunteerHousehold = new CreateNewVolunteerHousehold(communities,countrybwe,namebwe);
        createNewVolunteerHousehold.show(getSupportFragmentManager(), "createNewVolunteerHousehold");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, VolunteerHousehold newVolunteerHousehold) {
        Log.i("Tutorials", "newVolunteerHousehold " + newVolunteerHousehold.getCommunity() + " " +newVolunteerHousehold.getHeadHouseholdName() + " " + newVolunteerHousehold.getHouseholdLocation() );

        if (newVolunteerHousehold.getHeadHouseholdName()!=null&&newVolunteerHousehold.getHeadHouseholdName()!=""){

            Amplify.DataStore.save(newVolunteerHousehold,
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
        }else{
            Log.i("Tutorials", "newVolunteerHousehold data not valid" );
        }

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
        new AlertDialog.Builder(VolHouseholdCardSelectActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Volunteer household created Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reload the Cards again
                        downloadVolunteerHouseholdListAndShowOnRecyclerView();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(VolHouseholdCardSelectActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Volunteer household creation Failed Please try again\n" )
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                        .setCanceledOnTouchOutside(false);
            }
        });
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
