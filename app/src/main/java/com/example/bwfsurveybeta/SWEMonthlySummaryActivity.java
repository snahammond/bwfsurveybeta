package com.example.bwfsurveybeta;

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
import com.amplifyframework.datastore.generated.model.HealthCheckSurvey;
import com.amplifyframework.datastore.generated.model.InitialSurvey;
import com.amplifyframework.datastore.generated.model.SWEMonthlySummary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SWEMonthlySummaryActivity extends AppCompatActivity {
    private String namebwe = null;
    private String positionbwe = null;
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

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);

        createMonthlySummaryQuestionaire();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InterchangeCardAdapter(SWEMonthlySummaryActivity.this, SWEMonthlySummaryActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }

    private void createMonthlySummaryQuestionaire() {
        try{
            ArrayList<Interchange> returnedInterchanges = MyAmplifyApplication.getInterchanges("SWESUMMARY");
            if(returnedInterchanges!=null){
                SWEMonthlySummaryActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    interchange.setPositionOnRecyler(positionOnRecyler);
                    SWEMonthlySummaryActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
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

        if (id == R.id.menu_save) {
            ArrayList<Interchange> interchangesWithUserAns = adapter.retrieveData();

            //we have to validate now
            ArrayList<Interchange> invalideInterchanges = validateUserAns(interchangesWithUserAns);
            Log.i("Tutorial", "how many invalid interfaces: " + invalideInterchanges.size());

            if(invalideInterchanges.size()>0){
                showInvalidSurveyAlert();
            }else{

                //make an InitialSurvey object
                SWEMonthlySummary sweMonthlySummary = makeSWEMonthlySummaryObject(interchangesWithUserAns);
                //save the initialSurvey object
                saveSWEMonthlySummary(sweMonthlySummary);

            }
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

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(SWEMonthlySummaryActivity.this)
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
        new AlertDialog.Builder(SWEMonthlySummaryActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Monthly Summary Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all the user answers
                        for(Interchange interchange: SWEMonthlySummaryActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        SWEMonthlySummaryActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }
    private SWEMonthlySummary makeSWEMonthlySummaryObject(ArrayList<Interchange> interchangesWithUserAns) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = namebwe;
        String SwePosition = positionbwe;
        Integer NoWaterSampleTaken = parseIntegerWithDefault( getInterchangeAns("NoWaterSampleTaken",interchangesWithUserAns),0);
        Integer NoSurveysCompleted = parseIntegerWithDefault( getInterchangeAns("NoSurveysCompleted",interchangesWithUserAns),0);
        Integer NoLsn1Taught = parseIntegerWithDefault( getInterchangeAns("NoLsn1Taught",interchangesWithUserAns),0);
        Integer NoLsn2Taught = parseIntegerWithDefault( getInterchangeAns("NoLsn2Taught",interchangesWithUserAns),0);
        Integer NoLsn3Taught = parseIntegerWithDefault( getInterchangeAns("NoLsn3Taught",interchangesWithUserAns),0);
        Integer NoLsn4Taught = parseIntegerWithDefault( getInterchangeAns("NoLsn4Taught",interchangesWithUserAns),0);
        Integer NoPersonsTaught = parseIntegerWithDefault( getInterchangeAns("NoPersonsTaught",interchangesWithUserAns),0);
        Integer NoChlorineLiquidTabsDistributed = parseIntegerWithDefault( getInterchangeAns("NoChlorineLiquid_TabsDistributed",interchangesWithUserAns),0);
        Temporal.Date date = new Temporal.Date(date_s);

        SWEMonthlySummary sweMonthlySummary = SWEMonthlySummary.builder()
                .namebwe(Namebwe)
                .swePosition(SwePosition)
                .noWaterSampleTaken(NoWaterSampleTaken)
                .noSurveysCompleted(NoSurveysCompleted)
                .noLsn1Taught(NoLsn1Taught)
                .noLsn2Taught(NoLsn2Taught)
                .noLsn3Taught(NoLsn3Taught)
                .noLsn4Taught(NoLsn4Taught)
                .noPersonsTaught(NoPersonsTaught)
                .noChlorineLiquidTabsDistributed(NoChlorineLiquidTabsDistributed)
                .date(date)
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
            ans = foundInterchange.getValidation().getDefaultValue();
        }
        return ans;
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
        new AlertDialog.Builder(SWEMonthlySummaryActivity.this)
                .setTitle("Invalid Questions")
                .setMessage("Some questions have not been correctly answered \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    public static int parseIntegerWithDefault(Object s, int defaultVal) {
        if (s instanceof Integer) {
            return (Integer) s;
        }else if (s instanceof String){
            String str = (String) s;
            return str.matches("-?\\d+") ? Integer.parseInt(str): defaultVal;
        }else
            return defaultVal;

    }

}
