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
import com.amplifyframework.datastore.generated.model.VolunteerMonthlySummary;
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

public class UpdateVolunteerMonthlySummaryActivity extends AppCompatActivity {
    VolunteerMonthlySummary theVolunteerMonthlySummary;
    private String uuidVolunteerMonthlySummary;
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
            uuidVolunteerMonthlySummary = getIntent().getStringExtra("UUID");

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createVolunteerMonthlySummaryUpdateInterchanges();
    }

    private void createVolunteerMonthlySummaryUpdateInterchanges() {
        interchanges = new ArrayList<>();
        createInterchangesAndShowOnRecyclerView();
    }

    private void createInterchangesAndShowOnRecyclerView() {
        Amplify.DataStore.query(
                VolunteerMonthlySummary.class,
                Where.matches(VolunteerMonthlySummary.ID.eq(uuidVolunteerMonthlySummary)),
                volunteerMonthlySummary -> {
                    Log.i("Tutorials", "DataStore is queried.");
                    while (volunteerMonthlySummary.hasNext()) {
                        theVolunteerMonthlySummary = volunteerMonthlySummary.next();
                        Log.i("Tutorials", "DataStore is queried. theVolunteerMonthlySummary " +theVolunteerMonthlySummary.getId());
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
        if(theVolunteerMonthlySummary!=null){
            ArrayList<Interchange> returnedInterchanges = BwfSurveyAmplifyApplication.getInterchanges("VOLUNTEERSUMMARY");
            if(returnedInterchanges!=null){
                UpdateVolunteerMonthlySummaryActivity.interchanges = new ArrayList<>();
                int positionOnRecyler = 0;
                for(Interchange interchange : returnedInterchanges){
                    //set answers
                    String answer = "";
                    String nameOfAns = interchange.getAnswer().getAnswerDef().getName();
                    String methodName = "get"+nameOfAns;
                    java.lang.reflect.Method method;
                    try {
                        method = theVolunteerMonthlySummary.getClass().getMethod(methodName);
                        Object ansObject = method.invoke(theVolunteerMonthlySummary);
                        answer = ansObject.toString();
                    } catch (Exception e) {
                        Log.e("Tutorials", "Could not get answer " + nameOfAns);
                    }
                    interchange.getAnswer().setAns(answer);

                    interchange.setPositionOnRecyler(positionOnRecyler);
                    UpdateVolunteerMonthlySummaryActivity.interchanges.add(interchange);
                    positionOnRecyler += 1;
                }
                //sort the interchanges
                Collections.sort(UpdateVolunteerMonthlySummaryActivity.interchanges);
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
        adapter = new InterchangeCardAdapter(UpdateVolunteerMonthlySummaryActivity.this, UpdateVolunteerMonthlySummaryActivity.interchanges);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for(Interchange interchange: UpdateVolunteerMonthlySummaryActivity.interchanges){
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
                    PhoneLocation phoneLocation = new PhoneLocation(UpdateVolunteerMonthlySummaryActivity.this);
                    String[] arraylatlng = phoneLocation.getLocation();
                    if(arraylatlng!=null){
                        lat_ = arraylatlng[0];
                        lng_ = arraylatlng[1];
                    }
                }
                //make an volunteerMonthlySummary object
                VolunteerMonthlySummary volunteerMonthlySummary = makeVolunteerMonthlySummaryObjectWithId(interchangesWithUserAns,1,lat_,lng_);

                //save the volunteerMonthlySummary object
                saveVolunteerMonthlySummary(volunteerMonthlySummary);
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
                PhoneLocation phoneLocation = new PhoneLocation(UpdateVolunteerMonthlySummaryActivity.this);
                String[] arraylatlng = phoneLocation.getLocation();
                if(arraylatlng!=null){
                    lat_ = arraylatlng[0];
                    lng_ = arraylatlng[1];
                }
            }
            //make an volunteerMonthlySummary object
            VolunteerMonthlySummary volunteerMonthlySummary = makeVolunteerMonthlySummaryObjectWithId(interchangesWithUserAns,0,lat_,lng_);

            //save the volunteerMonthlySummary object
            saveVolunteerMonthlySummary(volunteerMonthlySummary);

        }
        return super.onOptionsItemSelected(item);
    }

    private void saveVolunteerMonthlySummary(VolunteerMonthlySummary volunteerMonthlySummary) {
        Amplify.DataStore.save(volunteerMonthlySummary,
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
        CountDownTimer countDownTimer = new CountDownTimer(BwfSurveyAmplifyApplication.manualTimer,1000) {
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
        new AlertDialog.Builder(UpdateVolunteerMonthlySummaryActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Volunteer Monthly Summary Saved Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reset all the user answers
                        for(Interchange interchange: UpdateVolunteerMonthlySummaryActivity.interchanges){
                            interchange.getAnswer().setAns(null);
                        }
                        UpdateVolunteerMonthlySummaryActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(UpdateVolunteerMonthlySummaryActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Volunteer Monthly Summary Save Failed! Please try again\n" )
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
        new AlertDialog.Builder(UpdateVolunteerMonthlySummaryActivity.this)
                .setTitle("Invalid Questions")
                .setMessage("Some questions have not been correctly answered \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private VolunteerMonthlySummary makeVolunteerMonthlySummaryObjectWithId(ArrayList<Interchange> interchangesWithUserAns,int completed, String lat, String lng) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = dateFormat.format(calendar.getTime());

        String Namebwe = theVolunteerMonthlySummary.getNamebwe();
        String SwePosition = "Volunteer";
        String NameVolunteer = theVolunteerMonthlySummary.getNamevol();
        Integer NoWaterSampleTaken = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoWaterSampleTaken",interchangesWithUserAns),0);
        Integer NoLsn1TaughtAsPrimaryInstr = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn1TaughtAsPrimaryInstr",interchangesWithUserAns),0);
        Integer NoLsn1TaughtAssistingSWE = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn1TaughtAssistingSWE",interchangesWithUserAns),0);
        Integer NoPersonsTaughtLesson1ByVol = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaughtLesson1ByVol",interchangesWithUserAns),0);
        Integer NoLsn2TaughtAsPrimaryInstr = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn2TaughtAsPrimaryInstr",interchangesWithUserAns),0);
        Integer NoLsn2TaughtAssistingSWE = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn2TaughtAssistingSWE",interchangesWithUserAns),0);
        Integer NoPersonsTaughtLesson2ByVol = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaughtLesson2ByVol",interchangesWithUserAns),0);
        Integer NoLsn3TaughtAsPrimaryInstr = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn3TaughtAsPrimaryInstr",interchangesWithUserAns),0);
        Integer NoLsn3TaughtAssistingSWE = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn3TaughtAssistingSWE",interchangesWithUserAns),0);
        Integer NoPersonsTaughtLesson3ByVol = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaughtLesson3ByVol",interchangesWithUserAns),0);
        Integer NoLsn4TaughtAsPrimaryInstr = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn4TaughtAsPrimaryInstr",interchangesWithUserAns),0);
        Integer NoLsn4TaughtAssistingSWE = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoLsn4TaughtAssistingSWE",interchangesWithUserAns),0);
        Integer NoPersonsTaughtLesson4ByVol = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaughtLesson4ByVol",interchangesWithUserAns),0);
        Integer NoPersonsTaughtByVol = IntegerUtils.parseIntegerWithDefault( InterchangeUtils.getInterchangeAns("NoPersonsTaughtByVol",interchangesWithUserAns),0);
        Temporal.Date date = new Temporal.Date(date_s);

        VolunteerMonthlySummary volunteerMonthlySummary = VolunteerMonthlySummary.builder()
                .namebwe(Namebwe)
                .namevol(NameVolunteer)
                .noWaterSampleTaken(NoWaterSampleTaken)
                .noLsn1TaughtAsPrimaryInstr(NoLsn1TaughtAsPrimaryInstr)
                .noLsn1TaughtAssistingSwe(NoLsn1TaughtAssistingSWE)
                .noPersonsTaughtLesson1ByVol(NoPersonsTaughtLesson1ByVol)
                .noLsn2TaughtAsPrimaryInstr(NoLsn2TaughtAsPrimaryInstr)
                .noLsn2TaughtAssistingSwe(NoLsn2TaughtAssistingSWE)
                .noPersonsTaughtLesson2ByVol(NoPersonsTaughtLesson2ByVol)
                .noLsn3TaughtAsPrimaryInstr(NoLsn3TaughtAsPrimaryInstr)
                .noLsn3TaughtAssistingSwe(NoLsn3TaughtAssistingSWE)
                .noPersonsTaughtLesson3ByVol(NoPersonsTaughtLesson3ByVol)
                .noLsn4TaughtAsPrimaryInstr(NoLsn4TaughtAsPrimaryInstr)
                .noLsn4TaughtAssistingSwe(NoLsn4TaughtAssistingSWE)
                .noPersonsTaughtLesson4ByVol(NoPersonsTaughtLesson4ByVol)
                .noPersonsTaughtByVol(NoPersonsTaughtByVol)
                .completed(completed)
                .lat(lat)
                .lng(lng)
                .date(date)
                .id(theVolunteerMonthlySummary.getId())
                .build();
        return volunteerMonthlySummary;

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
