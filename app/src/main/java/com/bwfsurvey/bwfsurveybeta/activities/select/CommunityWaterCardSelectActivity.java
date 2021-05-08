package com.bwfsurvey.bwfsurveybeta.activities.select;

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
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.CommunityWater;
import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.CommunityWaterCardAdapter;
import com.bwfsurvey.bwfsurveybeta.dialogs.CreateNewCommunityWaterSource;
import com.bwfsurvey.bwfsurveybeta.types.Community;
import com.bwfsurvey.bwfsurveybeta.utils.ListUtils;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class CommunityWaterCardSelectActivity extends AppCompatActivity implements CreateNewCommunityWaterSource.CreateNewCommunityWaterSourceListener {
    private static ArrayList<CommunityWater> listOfCommunityWater;
    private RecyclerView recyclerView;
    private CommunityWaterCardAdapter adapter;
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
        createCommunityWaterCardSelectList();
    }

    private void createCommunityWaterCardSelectList() {
        downloadCommunityWaterListAndShowOnRecyclerView();
    }

    private void downloadCommunityWaterListAndShowOnRecyclerView() {
        Log.i("Tutorials", "going to downloadCommunityWaterListAndShowOnRecyclerView");
        try{
            listOfCommunityWater = new ArrayList<>();
            Amplify.DataStore.query(
                    CommunityWater.class,
                    allCommunityWater -> {
                        Log.i("Tutorials", "DataStore is queried.");
                        while (allCommunityWater.hasNext()) {
                            CommunityWater aCommunityWater = allCommunityWater.next();
                            listOfCommunityWater.add(aCommunityWater);
                            Log.i("Tutorials", "Title: " + aCommunityWater.getCommunityWaterLocation());
                        }
                        Log.i("Tutorials", "Queried");
                        runOnUiThread(new Runnable() {
                            public void run() {
                                showListOfCommunityWater();
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

    private void showListOfCommunityWater() {
        if(listOfCommunityWater.size()>0){
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
            String msg = "No community water source found, please create new community water source.";
            ListUtils.showZeroListAlert(msg,CommunityWaterCardSelectActivity.this);
        }

    }

    private void initViewElements() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommunityWaterCardAdapter(CommunityWaterCardSelectActivity.this, CommunityWaterCardSelectActivity.listOfCommunityWater,namebwe,countrybwe,surveyType,operation);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.menu_community_water, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.newCommunityWaterSource) {
            Log.i("Tutorials", "going to get communities" );

            ArrayList<Community> listOfCommunities = BwfSurveyAmplifyApplication.getCommunities(countrybwe);
            showCreateNewCommunityWaterSource(listOfCommunities);

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

    private DialogFragment createNewCommunityWaterSource;
    public void showCreateNewCommunityWaterSource(ArrayList<Community> communities) {
        createNewCommunityWaterSource = new CreateNewCommunityWaterSource(communities,countrybwe,namebwe);
        createNewCommunityWaterSource.show(getSupportFragmentManager(), "createNewCommunityWaterSource");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, CommunityWater newCommunityWaterSource) {
        Log.i("Tutorials", "newCommunityWaterSource " + newCommunityWaterSource.getCommunity() + " " +newCommunityWaterSource.getCommunityWaterLocation()  );
        if (newCommunityWaterSource.getCommunityWaterLocation()!=null&&newCommunityWaterSource.getCommunityWaterLocation()!=""){

            Amplify.DataStore.save(newCommunityWaterSource,
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
            Log.i("Tutorials", "newCommunityWaterSource data not valid" );
        }
    }

    private void doSyncWaitAndShowSavedSuccessfulAlert() {
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

    private void showSavedSuccessfulAlert() {
        new AlertDialog.Builder(CommunityWaterCardSelectActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Community water source created Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reload the Cards again
                        downloadCommunityWaterListAndShowOnRecyclerView();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(CommunityWaterCardSelectActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Community Water source creation Failed Please try again\n" )
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                        .setCanceledOnTouchOutside(false);
            }
        });
    }
}
