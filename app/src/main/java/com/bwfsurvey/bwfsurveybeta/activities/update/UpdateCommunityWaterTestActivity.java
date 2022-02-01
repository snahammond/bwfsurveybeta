package com.bwfsurvey.bwfsurveybeta.activities.update;

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
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.CommunityWaterTest;
import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.InterchangeCardAdapter;
import com.bwfsurvey.bwfsurveybeta.types.Interchange;
import com.bwfsurvey.bwfsurveybeta.utils.PhoneLocation;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class UpdateCommunityWaterTestActivity extends AppCompatActivity {
    CommunityWaterTest theCommunityWaterTest;
    private String uuidCommunityWaterTest;
    private RecyclerView recyclerView;
    private InterchangeCardAdapter adapter;

    private static ArrayList<Interchange> interchanges;
    private LinearLayout progressBar;

    private String lat = null;
    private String lng = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().getStringExtra("UUID")!=null)
            uuidCommunityWaterTest = getIntent().getStringExtra("UUID");

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createCommunityWaterTestUpdateInterchanges();
    }

    private void createCommunityWaterTestUpdateInterchanges() {
        interchanges = new ArrayList<>();
        createInterchangesAndShowOnRecyclerView();
    }

    private void createInterchangesAndShowOnRecyclerView() {
        Amplify.DataStore.query(
                CommunityWaterTest.class,
                Where.matches(CommunityWaterTest.ID.eq(uuidCommunityWaterTest)),
                communityWaterTest -> {
                    Log.i("Tutorials", "DataStore is queried.");
                    while (communityWaterTest.hasNext()) {
                        theCommunityWaterTest = communityWaterTest.next();
                        Log.i("Tutorials", "DataStore is queried. theFollowUpSurvey " +theCommunityWaterTest.getId());
                    }

                    runOnUiThread(new Runnable() {
                        public void run() {
                            createInterchangesAndShow();
                        }
                    });
                },
                failure ->{
                    Log.e("Tutorials", "Query failed.", failure);
                }
        );
    }

    private void createInterchangesAndShow() {
        if(theCommunityWaterTest!=null){
            ArrayList<Interchange> returnedInterchanges = BwfSurveyAmplifyApplication.getInterchanges("WATERSURVEYCOMMUNITY");
            if(returnedInterchanges!=null){
                UpdateCommunityWaterTestActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    //set answers
                    String answer = "";
                    String nameOfAns = interchange.getAnswer().getAnswerDef().getName();
                    String methodName = "get"+nameOfAns;
                    java.lang.reflect.Method method;
                    try {
                        method = theCommunityWaterTest.getClass().getMethod(methodName);
                        Object ansObject = method.invoke(theCommunityWaterTest);
                        if(ansObject!=null)
                            answer = ansObject.toString();
                    } catch (Exception e) {
                        Log.e("Tutorials", "Could not get answer " + nameOfAns);
                    }
                    interchange.getAnswer().setAns(answer);

                    interchange.setPositionOnRecyler(positionOnRecyler);
                    UpdateCommunityWaterTestActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
                //sort the interchanges
                Collections.sort(UpdateCommunityWaterTestActivity.interchanges);
                showViewOnlyInterchanges();
            }
        }
    }

    private void showViewOnlyInterchanges() {
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
    }

    private void initViewElements() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InterchangeCardAdapter(UpdateCommunityWaterTestActivity.this, UpdateCommunityWaterTestActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for(Interchange interchange: UpdateCommunityWaterTestActivity.interchanges){
            interchange.getAnswer().setAns(null);
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
            ArrayList<Interchange> invalideInterchanges = validateUserAns(interchangesWithUserAns);
            Log.i("Tutorial", "how many invalid interfaces: " + invalideInterchanges.size());

            if(invalideInterchanges.size()>0){
                showInvalidSurveyAlert();
            }else{
                String lat_ = "";
                String lng_ = "";
                if(lat!=null&&lng!=null){
                    lat_= lat;
                    lng_ = lng;
                }else{
                    //try and get it again
                    PhoneLocation phoneLocation = new PhoneLocation(UpdateCommunityWaterTestActivity.this);
                    String[] arraylatlng = phoneLocation.getLocation();
                    if(arraylatlng!=null){
                        lat_ = arraylatlng[0];
                        lng_ = arraylatlng[1];
                    }
                }
                //make an CommunityWaterTest object
                CommunityWaterTest communityWaterTestToSave = makeCommunityWaterTestObjectWithId(interchangesWithUserAns,1,lat_,lng_);

                //save the CommunityWaterTest object
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
                PhoneLocation phoneLocation = new PhoneLocation(UpdateCommunityWaterTestActivity.this);
                String[] arraylatlng = phoneLocation.getLocation();
                if(arraylatlng!=null){
                    lat_ = arraylatlng[0];
                    lng_ = arraylatlng[1];
                }
            }
            //make an CommunityWaterTest object
            CommunityWaterTest communityWaterTestToSave = makeCommunityWaterTestObjectWithId(interchangesWithUserAns,0,lat_,lng_);

            //save the CommunityWaterTest object
            saveCommunityWaterTestSurvey(communityWaterTestToSave);

        }
        return super.onOptionsItemSelected(item);
    }

    private CommunityWaterTest makeCommunityWaterTestObjectWithId(ArrayList<Interchange> interchangesWithUserAns, int completed, String lat_, String lng_) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = theCommunityWaterTest.getNamebwe();
        String Country = (String) theCommunityWaterTest.getCountry();
        String Community = (String) theCommunityWaterTest.getCommunity();
        String CommunityWaterLoc = (String) theCommunityWaterTest.getCommunityWaterLocation();
        Temporal.Date  ColilertDateTested = parseDateWithDefault(getInterchangeAns("ColilertDateTested",interchangesWithUserAns));
        Temporal.Date ColilertDateRead = parseDateWithDefault(getInterchangeAns("ColilertDateRead",interchangesWithUserAns));
        String ColilertTestResult = (String) getInterchangeAns("ColilertTestResult",interchangesWithUserAns);
        Temporal.Date PetrifilmDateTested = parseDateWithDefault(getInterchangeAns("PetrifilmDateTested",interchangesWithUserAns));
        Temporal.Date PetrifilmDateRead = parseDateWithDefault(getInterchangeAns("PetrifilmDateRead",interchangesWithUserAns));
        String PetrifilmTestResult = (String) getInterchangeAns("PetrifilmTestResult",interchangesWithUserAns);
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
                .lat(lat_)
                .lng(lng_)
                .date(date)
                .id(theCommunityWaterTest.getId())
                .build();
        return communityWaterTest;
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
        new AlertDialog.Builder(UpdateCommunityWaterTestActivity.this)
                .setTitle("Invalid Questions")
                .setMessage("Some questions have not been correctly answered \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
                .setCanceledOnTouchOutside(false);
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
            if(foundInterchange!=null) {
                ans = foundInterchange.getValidation().getDefaultValue();
            }
        }
        return ans;
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
                new AlertDialog.Builder(UpdateCommunityWaterTestActivity.this)
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
        new AlertDialog.Builder(UpdateCommunityWaterTestActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Community Water Test Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all the user answers
                        for(Interchange interchange: UpdateCommunityWaterTestActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        UpdateCommunityWaterTestActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    public static Temporal.Date parseDateWithDefault(Object s){
        Temporal.Date dateValue;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try{
            dateValue = new Temporal.Date(dateFormat.format(s));
        }catch (Exception x){
            dateValue = new Temporal.Date("1900-01-01");
        }
        return dateValue;
    }
}
