package com.bwfsurvey.bwfsurveybeta.activities.collect;

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
import com.amplifyframework.datastore.generated.model.CommunityWaterTest;
import com.bwfsurvey.bwfsurveybeta.types.Interchange;
import com.bwfsurvey.bwfsurveybeta.adapters.InterchangeCardAdapter;
import com.bwfsurvey.bwfsurveybeta.MyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.utils.DateUtils;
import com.bwfsurvey.bwfsurveybeta.utils.InterchangeUtils;
import com.bwfsurvey.bwfsurveybeta.utils.PhoneLocation;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class CommunityWaterSurveyActivity extends AppCompatActivity {
    private String namebwe = null;
    private String surveyType = null;
    private String countrybwe = null;
    private String community = null;
    private String communityWaterLoc = null;
    private String householdName = null;

    private static ArrayList<Interchange> interchanges;
    private RecyclerView recyclerView;
    private InterchangeCardAdapter adapter;

    private LinearLayout progressBar;

    private String lat = null;
    private String lng = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("NAME_BWE")!=null)
            namebwe = getIntent().getStringExtra("NAME_BWE");
        if(getIntent().getStringExtra("SURVEY_TYPE")!=null)
            surveyType = getIntent().getStringExtra("SURVEY_TYPE");
        if(getIntent().getStringExtra("COUNTRY_BWE")!=null)
            countrybwe = getIntent().getStringExtra("COUNTRY_BWE");
        if(getIntent().getStringExtra("COMMUNITY")!=null)
            community = getIntent().getStringExtra("COMMUNITY");
        if(getIntent().getStringExtra("COMMUNITY_WATER_LOC")!=null)
            communityWaterLoc = getIntent().getStringExtra("COMMUNITY_WATER_LOC");

        if(getIntent().getStringExtra("LAT")!=null)
            lat = getIntent().getStringExtra("LAT");
        if(getIntent().getStringExtra("LNG")!=null)
            lng = getIntent().getStringExtra("LNG");

        setContentView(R.layout.activity_recycler);
        getSupportActionBar().setTitle((CharSequence) "Community Water Test; "+community);
        initView();
    }

    private void initView() {
        createCommunityWaterSurveyQuestionaire();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InterchangeCardAdapter(CommunityWaterSurveyActivity.this, CommunityWaterSurveyActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }

    private void createCommunityWaterSurveyQuestionaire() {
        try{
            ArrayList<Interchange> returnedInterchanges = MyAmplifyApplication.getInterchanges(surveyType);
            if(returnedInterchanges!=null){
                CommunityWaterSurveyActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    interchange.setPositionOnRecyler(positionOnRecyler);
                    CommunityWaterSurveyActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
                Collections.sort(CommunityWaterSurveyActivity.interchanges);
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
                InterchangeUtils.showInvalidSurveyAlert(invalideInterchanges,CommunityWaterSurveyActivity.this);
            }else{
                Log.i("Tutorial", "we are going to save!");

                String lat_ = "";
                String lng_ = "";
                if(lat!=null&&lng!=null){
                    lat_= lat;
                    lng_ = lng;
                }else{
                    //try and get it again
                    PhoneLocation phoneLocation = new PhoneLocation(CommunityWaterSurveyActivity.this);
                    String[] arraylatlng = phoneLocation.getLocation();
                    if(arraylatlng!=null){
                        lat_ = arraylatlng[0];
                        lng_ = arraylatlng[1];
                    }
                }

                //make an InitialSurvey object
                CommunityWaterTest communityWaterTestToSave = makeCommunityWaterTestObject(interchangesWithUserAns,1,lat_,lng_);

                //save the initialSurvey object
                saveCommunityWaterTestSurvey(communityWaterTestToSave);

            }

        }

        if(id== R.id.suspend){
            ArrayList<Interchange> interchangesWithUserAns = adapter.retrieveData();
            String lat_ = "";
            String lng_ = "";
            if(lat!=null&&lng!=null){
                lat_= lat;
                lng_ = lng;
            }else{
                //try and get it again
                PhoneLocation phoneLocation = new PhoneLocation(CommunityWaterSurveyActivity.this);
                String[] arraylatlng = phoneLocation.getLocation();
                if(arraylatlng!=null){
                    lat_ = arraylatlng[0];
                    lng_ = arraylatlng[1];
                }
            }
            //make an InitialSurvey object
            CommunityWaterTest communityWaterTestToSave = makeCommunityWaterTestObject(interchangesWithUserAns,0,lat_,lng_);

            //save the initialSurvey object
            saveCommunityWaterTestSurvey(communityWaterTestToSave);

        }
        return super.onOptionsItemSelected(item);
    }

    private void saveCommunityWaterTestSurvey(CommunityWaterTest communityWaterTestToSave) {
        Amplify.DataStore.save(communityWaterTestToSave,
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
                new AlertDialog.Builder(CommunityWaterSurveyActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Community Water Test Save Failed! Please try again\n" )
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
        new AlertDialog.Builder(CommunityWaterSurveyActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Community Water Test Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all the user answers
                        for(Interchange interchange: CommunityWaterSurveyActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        CommunityWaterSurveyActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private CommunityWaterTest makeCommunityWaterTestObject(ArrayList<Interchange> interchangesWithUserAns,int completed, String lat, String lng) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = namebwe;
        String Country = (String) countrybwe;
        String Community = (String) community;
        String CommunityWaterLoc = (String) communityWaterLoc;
        Temporal.Date  ColilertDateTested = DateUtils.parseDateWithDefault(InterchangeUtils.getInterchangeAns("ColilertDateTested",interchangesWithUserAns));
        Temporal.Date ColilertDateRead = DateUtils.parseDateWithDefault(InterchangeUtils.getInterchangeAns("ColilertDateRead",interchangesWithUserAns));
        String ColilertTestResult = (String) InterchangeUtils.getInterchangeAns("ColilertTestResult",interchangesWithUserAns);
        Temporal.Date PetrifilmDateTested = DateUtils.parseDateWithDefault(InterchangeUtils.getInterchangeAns("PetrifilmDateTested",interchangesWithUserAns));
        Temporal.Date PetrifilmDateRead = DateUtils.parseDateWithDefault(InterchangeUtils.getInterchangeAns("PetrifilmDateRead",interchangesWithUserAns));
        String PetrifilmTestResult = (String) InterchangeUtils.getInterchangeAns("PetrifilmTestResult",interchangesWithUserAns);
        Temporal.Date date = new Temporal.Date(date_s);

        CommunityWaterTest communityWaterTest = CommunityWaterTest.builder()
                .namebwe(Namebwe)
                .country(Country)
                .community(Community)
                .communityWaterLocation(CommunityWaterLoc)
                .colilertDateTested(ColilertDateTested)
                .colilertDateRead(ColilertDateRead)
                .colilertTestResult(ColilertTestResult)
                .petrifilmDateTested(PetrifilmDateTested)
                .petrifilmDateRead(PetrifilmDateRead)
                .petrifilmTestResult(PetrifilmTestResult)
                .completed(completed)
                .lat(lat)
                .lng(lng)
                .date(date)
                .build();
        return communityWaterTest;
    }




}
