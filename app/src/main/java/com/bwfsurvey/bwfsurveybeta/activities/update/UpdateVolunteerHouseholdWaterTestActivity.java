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
import com.amplifyframework.datastore.generated.model.VolunteerHouseholdWaterTest;
import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.InterchangeCardAdapter;
import com.bwfsurvey.bwfsurveybeta.types.Interchange;
import com.bwfsurvey.bwfsurveybeta.utils.PhoneLocation;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class UpdateVolunteerHouseholdWaterTestActivity extends AppCompatActivity {

    VolunteerHouseholdWaterTest theVolunteerHouseholdWaterTest;
    private String uuidVolunteerHouseholdWaterTest;
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
            uuidVolunteerHouseholdWaterTest = getIntent().getStringExtra("UUID");

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createVolunteerHouseholdWaterTestUpdateInterchanges();
    }

    private void createVolunteerHouseholdWaterTestUpdateInterchanges() {
        interchanges = new ArrayList<>();
        createInterchangesAndShowOnRecyclerView();
    }

    private void createInterchangesAndShowOnRecyclerView() {
        Amplify.DataStore.query(
                VolunteerHouseholdWaterTest.class,
                Where.matches(VolunteerHouseholdWaterTest.ID.eq(uuidVolunteerHouseholdWaterTest)),
                volunteerHouseholdWaterTest -> {
                    Log.i("Tutorials", "DataStore is queried.");
                    while (volunteerHouseholdWaterTest.hasNext()) {
                        theVolunteerHouseholdWaterTest = volunteerHouseholdWaterTest.next();
                        Log.i("Tutorials", "DataStore is queried. theVolunteerHouseholdWaterTest " +theVolunteerHouseholdWaterTest.getId());
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
        if(theVolunteerHouseholdWaterTest!=null){
            ArrayList<Interchange> returnedInterchanges = BwfSurveyAmplifyApplication.getInterchanges("WATERSURVEYHOUSEHOLD");
            if(returnedInterchanges!=null){
                UpdateVolunteerHouseholdWaterTestActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    //set answers
                    String answer = "";
                    String nameOfAns = interchange.getAnswer().getAnswerDef().getName();
                    String methodName = "get"+nameOfAns;
                    java.lang.reflect.Method method;
                    try {
                        method = theVolunteerHouseholdWaterTest.getClass().getMethod(methodName);
                        Object ansObject = method.invoke(theVolunteerHouseholdWaterTest);
                        answer = ansObject.toString();
                    } catch (Exception e) {
                        Log.e("Tutorials", "Could not get answer " + nameOfAns);
                    }
                    interchange.getAnswer().setAns(answer);

                    interchange.setPositionOnRecyler(positionOnRecyler);
                    UpdateVolunteerHouseholdWaterTestActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
                //sort the interchanges
                Collections.sort(UpdateVolunteerHouseholdWaterTestActivity.interchanges);
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
        adapter = new InterchangeCardAdapter(UpdateVolunteerHouseholdWaterTestActivity.this, UpdateVolunteerHouseholdWaterTestActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for(Interchange interchange: UpdateVolunteerHouseholdWaterTestActivity.interchanges){
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
                    PhoneLocation phoneLocation = new PhoneLocation(UpdateVolunteerHouseholdWaterTestActivity.this);
                    String[] arraylatlng = phoneLocation.getLocation();
                    if(arraylatlng!=null){
                        lat_ = arraylatlng[0];
                        lng_ = arraylatlng[1];
                    }
                }
                //make an CommunityWaterTest object
                VolunteerHouseholdWaterTest volHouseholdWaterTestToSave = makeVolunteerHouseholdWaterTestObjectWithId(interchangesWithUserAns,1,lat_,lng_);

                //save the CommunityWaterTest object
                saveVolunteerHouseholdWaterTestSurvey(volHouseholdWaterTestToSave);
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
                PhoneLocation phoneLocation = new PhoneLocation(UpdateVolunteerHouseholdWaterTestActivity.this);
                String[] arraylatlng = phoneLocation.getLocation();
                if(arraylatlng!=null){
                    lat_ = arraylatlng[0];
                    lng_ = arraylatlng[1];
                }
            }
            //make an CommunityWaterTest object
            VolunteerHouseholdWaterTest volunteerHouseholdWaterTestToSave = makeVolunteerHouseholdWaterTestObjectWithId(interchangesWithUserAns,0,lat_,lng_);

            //save the CommunityWaterTest object
            saveVolunteerHouseholdWaterTestSurvey(volunteerHouseholdWaterTestToSave);

        }
        return super.onOptionsItemSelected(item);
    }

    private void saveVolunteerHouseholdWaterTestSurvey(VolunteerHouseholdWaterTest volunteerHouseholdWaterTestToSave) {
        Amplify.DataStore.save(volunteerHouseholdWaterTestToSave,
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
        new AlertDialog.Builder(UpdateVolunteerHouseholdWaterTestActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Volunteer Household Water Test Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all the user answers
                        for(Interchange interchange: UpdateVolunteerHouseholdWaterTestActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        UpdateVolunteerHouseholdWaterTestActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(UpdateVolunteerHouseholdWaterTestActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Household Water Test Save Failed! Please try again\n" )
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                        .setCanceledOnTouchOutside(false);
            }
        });
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
        new AlertDialog.Builder(UpdateVolunteerHouseholdWaterTestActivity.this)
                .setTitle("Invalid Questions")
                .setMessage("Some questions have not been correctly answered \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private VolunteerHouseholdWaterTest makeVolunteerHouseholdWaterTestObjectWithId(ArrayList<Interchange> interchangesWithUserAns,int completed, String lat, String lng) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = theVolunteerHouseholdWaterTest.getNamebwe();
        String Country = (String) theVolunteerHouseholdWaterTest.getCountry();
        String Community = (String) theVolunteerHouseholdWaterTest.getCommunity();
        String HeadHouseholdName = (String) theVolunteerHouseholdWaterTest.getHeadHouseholdName();
        String NameVol = (String) theVolunteerHouseholdWaterTest.getNamevol();
        String HouseholdLoc = (String) theVolunteerHouseholdWaterTest.getHouseholdLocation();
        Temporal.Date  ColilertDateTested = parseDateWithDefault(getInterchangeAns("ColilertDateTested",interchangesWithUserAns));
        Temporal.Date ColilertDateRead = parseDateWithDefault(getInterchangeAns("ColilertDateRead",interchangesWithUserAns));
        String ColilertTestResult = (String) getInterchangeAns("ColilertTestResult",interchangesWithUserAns);
        Temporal.Date PetrifilmDateTested = parseDateWithDefault(getInterchangeAns("PetrifilmDateTested",interchangesWithUserAns));
        Temporal.Date PetrifilmDateRead = parseDateWithDefault(getInterchangeAns("PetrifilmDateRead",interchangesWithUserAns));
        String PetrifilmTestResult = (String) getInterchangeAns("PetrifilmTestResult",interchangesWithUserAns);
        Temporal.Date date = new Temporal.Date(date_s);

        VolunteerHouseholdWaterTest volunteerHouseholdWaterTest = VolunteerHouseholdWaterTest.builder()
                .namebwe(Namebwe)
                .namevol(NameVol)
                .country(Country)
                .community(Community)
                .headHouseholdName(HeadHouseholdName)
                .householdLocation(HouseholdLoc)
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
                .id(theVolunteerHouseholdWaterTest.getId())
                .build();
        return volunteerHouseholdWaterTest;
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

    public static Temporal.Date parseDateWithDefault(Object s){
        Temporal.Date dateValue = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try{
            dateValue = new Temporal.Date(dateFormat.format(s));
        }catch (Exception x){
            dateValue = new Temporal.Date("1900-01-01");
        }
        return dateValue;
    }
}
