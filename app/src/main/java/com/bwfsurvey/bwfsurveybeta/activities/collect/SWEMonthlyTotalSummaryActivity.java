package com.bwfsurvey.bwfsurveybeta.activities.collect;

import android.content.DialogInterface;
import android.os.Bundle;
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
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.generated.model.SWEMonthlySummary;
import com.amplifyframework.datastore.generated.model.SWEMonthlyTotalSummary;
import com.amplifyframework.datastore.syncengine.OutboxMutationEvent;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.SubscriptionToken;
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

public class SWEMonthlyTotalSummaryActivity extends AppCompatActivity {
    private String namebwe = null;
    private String positionbwe = null;

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
        adapter = new InterchangeCardAdapter(SWEMonthlyTotalSummaryActivity.this, SWEMonthlyTotalSummaryActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }

    private void createMonthlySummaryQuestionaire() {
        try{
            ArrayList<Interchange> returnedInterchanges = BwfSurveyAmplifyApplication.getInterchanges("SWETOTALSUMMARY");
            if(returnedInterchanges!=null){
                SWEMonthlyTotalSummaryActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    interchange.setPositionOnRecyler(positionOnRecyler);
                    SWEMonthlyTotalSummaryActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
                //sort the interchanges
                Collections.sort(SWEMonthlyTotalSummaryActivity.interchanges);
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
                InterchangeUtils.showInvalidSurveyAlert(invalideInterchanges,SWEMonthlyTotalSummaryActivity.this);
            }else{
                String lat_ = "";
                String lng_ = "";
                if(lat!=null&&lng!=null){
                    lat_= lat;
                    lng_ = lng;
                }else{
                    //try and get it again
                    PhoneLocation phoneLocation = new PhoneLocation(SWEMonthlyTotalSummaryActivity.this);
                    String[] arraylatlng = phoneLocation.getLocation();
                    if(arraylatlng!=null){
                        lat_ = arraylatlng[0];
                        lng_ = arraylatlng[1];
                    }
                }
                //make an InitialSurvey object
                SWEMonthlyTotalSummary sweMonthlyTotalSummary = makeSWEMonthlyTotalSummaryObject(interchangesWithUserAns,1,lat_,lng_);
                //save the initialSurvey object
                saveSWEMonthlyTotalSummary(sweMonthlyTotalSummary);

            }
        }

        if (id == R.id.suspend) {
            runOnUiThread(new Runnable() {
                public void run() {
                    new AlertDialog.Builder(SWEMonthlyTotalSummaryActivity.this)
                            .setTitle("Info")
                            .setMessage("Suspend not enabled \n" )
                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .show()
                            .setCanceledOnTouchOutside(false);
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    SubscriptionToken checkToken = null;
    private void saveSWEMonthlyTotalSummary(SWEMonthlyTotalSummary sweMonthlyTotalSummary) {
        checkToken = Amplify.Hub.subscribe(
                HubChannel.DATASTORE,
                hubEvent -> DataStoreChannelEventName.OUTBOX_MUTATION_ENQUEUED.toString().equals(hubEvent.getName()),
                hubEvent -> {
                    OutboxMutationEvent event = (OutboxMutationEvent) hubEvent.getData();
                    if(event!=null && event.getModelName().contentEquals("SWEMonthlyTotalSummary")){
                        if(event.getElement().getModel().equals(sweMonthlyTotalSummary)){
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    progressBar.setVisibility(View.GONE);
                                    showSavedSuccessfulAlert();
                                }
                            });
                        }else{
                            progressBar.setVisibility(View.GONE);
                        }
                    }else{
                        progressBar.setVisibility(View.GONE);
                    }
                }
        );
        Amplify.DataStore.save(sweMonthlyTotalSummary,
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
                new AlertDialog.Builder(SWEMonthlyTotalSummaryActivity.this)
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
    }

    private void showSavedSuccessfulAlert(){
        if(checkToken!=null)
            Amplify.Hub.unsubscribe(checkToken);
        new AlertDialog.Builder(SWEMonthlyTotalSummaryActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Monthly Summary Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all the user answers
                        for(Interchange interchange: SWEMonthlyTotalSummaryActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        SWEMonthlyTotalSummaryActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }
    private SWEMonthlyTotalSummary makeSWEMonthlyTotalSummaryObject(ArrayList<Interchange> interchangesWithUserAns,int completed, String lat, String lng) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = namebwe;
        String SwePosition = positionbwe;
        Integer NoWaterSampleTaken = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoWaterSampleTaken",interchangesWithUserAns),0);
        Integer NoSurveysCompleted = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoSurveysCompleted",interchangesWithUserAns),0);
        Integer NoHealthCheck = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoHealthCheck",interchangesWithUserAns),0);
        Integer NoPublicServiceMessagesAired = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPublicServiceMessagesAired",interchangesWithUserAns),0);
        Temporal.Date date = new Temporal.Date(date_s);

        SWEMonthlyTotalSummary sweMonthlyTotalSummary = SWEMonthlyTotalSummary.builder()
                .namebwe(Namebwe)
                .swePosition(SwePosition)
                .noWaterSampleTaken(NoWaterSampleTaken)
                .noSurveysCompleted(NoSurveysCompleted)
                .noHealthCheck(NoHealthCheck)
                .noPublicServiceMessagesAired(NoPublicServiceMessagesAired)
                .completed(completed)
                .lat(lat)
                .lng(lng)
                .date(date)
                .build();
        return sweMonthlyTotalSummary;
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
}