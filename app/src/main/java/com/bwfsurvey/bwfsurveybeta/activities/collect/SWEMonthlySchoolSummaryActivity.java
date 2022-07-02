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
import com.amplifyframework.datastore.generated.model.SWEMonthlySchoolSummary;
import com.amplifyframework.datastore.generated.model.SWEMonthlySummary;
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

public class SWEMonthlySchoolSummaryActivity extends AppCompatActivity {

    private static ArrayList<Interchange> interchanges;
    private RecyclerView recyclerView;
    private InterchangeCardAdapter adapter;

    private String namebwe;
    private String countrybwe;
    private String positionbwe = null;
    private String community;
    private String school;
    private String surveyType;
    private String lat = null;
    private String lng = null;

    private LinearLayout progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("NAME_BWE")!=null)
            namebwe = getIntent().getStringExtra("NAME_BWE");
        if(getIntent().getStringExtra("COUNTRY_BWE")!=null)
            countrybwe = getIntent().getStringExtra("COUNTRY_BWE");
        if(getIntent().getStringExtra("POSITION_BWE")!=null)
            positionbwe = getIntent().getStringExtra("POSITION_BWE");
        if(getIntent().getStringExtra("COMMUNITY")!=null)
            community = getIntent().getStringExtra("COMMUNITY");
        if(getIntent().getStringExtra("SCHOOL")!=null)
            school = getIntent().getStringExtra("SCHOOL");

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

        createMonthlySchoolSummaryQuestionaire();

        if(SWEMonthlySchoolSummaryActivity.interchanges!=null){
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new InterchangeCardAdapter(SWEMonthlySchoolSummaryActivity.this, SWEMonthlySchoolSummaryActivity.interchanges);
            recyclerView.setAdapter(adapter);
        }else{
            Log.i("Tutorial", "we cannot get list of interchanges " );
        }
    }

    private void createMonthlySchoolSummaryQuestionaire() {
        try{
            ArrayList<Interchange> returnedInterchanges = BwfSurveyAmplifyApplication.getInterchanges("SWESCHOOLSUMMARY");
            if(returnedInterchanges!=null){
                SWEMonthlySchoolSummaryActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    interchange.setPositionOnRecyler(positionOnRecyler);
                    SWEMonthlySchoolSummaryActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
                //sort the interchanges
                Collections.sort(SWEMonthlySchoolSummaryActivity.interchanges);
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
                InterchangeUtils.showInvalidSurveyAlert(invalideInterchanges,SWEMonthlySchoolSummaryActivity.this);
            }else{
                String lat_ = "";
                String lng_ = "";
                if(lat!=null&&lng!=null){
                    lat_= lat;
                    lng_ = lng;
                }else{
                    //try and get it again
                    PhoneLocation phoneLocation = new PhoneLocation(SWEMonthlySchoolSummaryActivity.this);
                    String[] arraylatlng = phoneLocation.getLocation();
                    if(arraylatlng!=null){
                        lat_ = arraylatlng[0];
                        lng_ = arraylatlng[1];
                    }
                }
                //make an InitialSurvey object
                SWEMonthlySchoolSummary sweMonthlySchoolSummary = makeSWEMonthlySchoolSummaryObject(interchangesWithUserAns,1,lat_,lng_);
                //save the initialSurvey object
                saveSWEMonthlySchoolSummary(sweMonthlySchoolSummary);

            }
        }

        return super.onOptionsItemSelected(item);
    }

    private SWEMonthlySchoolSummary makeSWEMonthlySchoolSummaryObject(ArrayList<Interchange> interchangesWithUserAns,int completed, String lat, String lng) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = namebwe;
        String SwePosition = positionbwe;

        Integer NoTabletUsedAtDrinkingStation1 = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoTabletUsedAtDrinkingStation1",interchangesWithUserAns),0);
        Integer NoTabletUsedAtDrinkingStation2 = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoTabletUsedAtDrinkingStation2",interchangesWithUserAns),0);
        Integer NoTabletUsedAtDrinkingStation3 = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoTabletUsedAtDrinkingStation3",interchangesWithUserAns),0);
        Integer NoStudentsTaughtSafeWaterPrinciples = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoStudentsTaughtSafeWaterPrinciples",interchangesWithUserAns),0);
        Integer NoEnrolledInSchoolHealthClub = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoEnrolledInSchoolHealthClub",interchangesWithUserAns),0);
        Integer NoHealthClubMeetingHeld = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoHealthClubMeetingHeld",interchangesWithUserAns),0);
        Integer NoHealthClubLessonsTaught = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoHealthClubLessonsTaught",interchangesWithUserAns),0);
        String DoHealthClubManageStations = (String) InterchangeUtils.getInterchangeAns("DoHealthClubManageStations",interchangesWithUserAns);
        Integer NoHealthClubCleanUpProject = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoHealthClubCleanUpProject",interchangesWithUserAns),0);
        Temporal.Date date = new Temporal.Date(date_s);

        SWEMonthlySchoolSummary sweMonthlySchoolSummary = SWEMonthlySchoolSummary.builder()
                .namebwe(Namebwe)
                .swePosition(SwePosition)
                .noTabletUsedAtDrinkingStation1(NoTabletUsedAtDrinkingStation1)
                .noTabletUsedAtDrinkingStation2(NoTabletUsedAtDrinkingStation2)
                .noTabletUsedAtDrinkingStation3(NoTabletUsedAtDrinkingStation3)
                .noStudentsTaughtSafeWaterPrinciples(NoStudentsTaughtSafeWaterPrinciples)
                .noEnrolledInSchoolHealthClub(NoEnrolledInSchoolHealthClub)
                .noHealthClubMeetingHeld(NoHealthClubMeetingHeld)
                .noHealthClubLessonsTaught(NoHealthClubLessonsTaught)
                .doHealthClubManageStations(DoHealthClubManageStations)
                .noHealthClubCleanUpProject(NoHealthClubCleanUpProject)
                .completed(completed)
                .lat(lat)
                .lng(lng)
                .date(date)
                .build();
        return sweMonthlySchoolSummary;
    }

    SubscriptionToken checkToken = null;
    private void saveSWEMonthlySchoolSummary(SWEMonthlySchoolSummary sweMonthlySchoolSummary) {
        checkToken = Amplify.Hub.subscribe(
                HubChannel.DATASTORE,
                hubEvent -> DataStoreChannelEventName.OUTBOX_MUTATION_ENQUEUED.toString().equals(hubEvent.getName()),
                hubEvent -> {
                    OutboxMutationEvent event = (OutboxMutationEvent) hubEvent.getData();
                    if(event!=null && event.getModelName().contentEquals("SWEMonthlySchoolSummary")){
                        if(event.getElement().getModel().getId().contentEquals(sweMonthlySchoolSummary.getId())){
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    progressBar.setVisibility(View.GONE);
                                    showSavedSuccessfulAlert();
                                }
                            });
                        }else{
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    progressBar.setVisibility(View.GONE);
                                    //reset all the user answers
                                    for(Interchange interchange: SWEMonthlySchoolSummaryActivity.interchanges){
                                        interchange.getAnswer().setAns(null);
                                    }
                                    SWEMonthlySchoolSummaryActivity.this.finish();
                                }
                            });
                        }
                    }else{
                        runOnUiThread(new Runnable() {
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                //reset all the user answers
                                for(Interchange interchange: SWEMonthlySchoolSummaryActivity.interchanges){
                                    interchange.getAnswer().setAns(null);
                                }
                                SWEMonthlySchoolSummaryActivity.this.finish();
                            }
                        });
                    }
                }
        );
        Amplify.DataStore.save(sweMonthlySchoolSummary,
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

    private void showSavedSuccessfulAlert(){
        if(checkToken!=null)
            Amplify.Hub.unsubscribe(checkToken);
        new AlertDialog.Builder(SWEMonthlySchoolSummaryActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Monthly School Summary Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all the user answers
                        for(Interchange interchange: SWEMonthlySchoolSummaryActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        SWEMonthlySchoolSummaryActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private void doSyncWaitAndShowSavedSuccessfulAlert(){
        //show progress bar so that if user is offline, the save will go into pending to be shot into cloud
        //this is for the progress bar
        progressBar = (LinearLayout) findViewById(R.id.llProgressBar);
        TextView progressBarText = (TextView) findViewById(R.id.pbText);
        progressBarText.setText("Please wait... Syncing Up!");
        progressBar.setVisibility(View.VISIBLE);
    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(SWEMonthlySchoolSummaryActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Monthly School Summary Save Failed! Please try again\n" )
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                        .setCanceledOnTouchOutside(false);
            }
        });
    }
}
