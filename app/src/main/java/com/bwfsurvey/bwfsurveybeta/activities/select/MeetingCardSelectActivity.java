package com.bwfsurvey.bwfsurveybeta.activities.select;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.generated.model.Meeting;
import com.amplifyframework.datastore.generated.model.Volunteer;
import com.amplifyframework.datastore.syncengine.OutboxMutationEvent;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.SubscriptionToken;
import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.MeetingCardAdapter;
import com.bwfsurvey.bwfsurveybeta.dialogs.CreateNewHouseholdAttendingMeeting;
import com.bwfsurvey.bwfsurveybeta.dialogs.CreateNewMeeting;
import com.bwfsurvey.bwfsurveybeta.dialogs.SelectCountryDialogFragment;
import com.bwfsurvey.bwfsurveybeta.types.Community;
import com.bwfsurvey.bwfsurveybeta.utils.ListUtils;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class MeetingCardSelectActivity extends AppCompatActivity implements CreateNewMeeting.CreateNewMeetingListener,SelectCountryDialogFragment.SelectCountryDialogFragmentListener{
    private static ArrayList<Meeting> listOfMeetings;
    private RecyclerView recyclerView;
    private MeetingCardAdapter adapter;
    private String namebwe = null;
    private String countrybwe = null;
    private String surveyType = null;
    private String operation = null;

    private LinearLayout progressBar;

    //this is set when new meeting is created
    private static String uuidNewMeeting = null;
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
        Log.i("Tutorials", "operation: " + operation);


        if(operation.contentEquals("CREATE"))
            setTitle("Select or create new Meeting");

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        uuidNewMeeting = null;
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createMeetingCardSelectList();
    }

    private void createMeetingCardSelectList() {
        downloadMeetingListAndShowOnRecyclerView();
    }

    private void downloadMeetingListAndShowOnRecyclerView() {
        Log.i("Tutorials", "going to downloadMeetingListAndShowOnRecyclerView");
        try{

            listOfMeetings = new ArrayList<>();
            Amplify.DataStore.query(
                    Meeting.class,
                    allMeetings -> {
                        Log.i("Tutorials", "DataStore is queried.");
                        while (allMeetings.hasNext()) {
                            Meeting aMeeting = allMeetings.next();
                            listOfMeetings.add(aMeeting);
                            Log.i("Tutorials", "Title: " + aMeeting.getNamevol());
                            //try to send all the Meeting by forcefully pushing
                            Amplify.API.mutate(
                                    ModelMutation.create(aMeeting),
                                    response -> {
                                    },
                                    error -> {
                                    }
                            );
                        }
                        Log.i("Tutorials", "Queried");
                        runOnUiThread(new Runnable() {
                            public void run() {
                                showListOfMeetings();
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

    private void showListOfMeetings() {
        if(listOfMeetings.size()>0){
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
            String msg = "No meeting found, please create new meeting";
            ListUtils.showZeroListAlert(msg,MeetingCardSelectActivity.this);
        }
    }

    private void initViewElements() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MeetingCardAdapter(MeetingCardSelectActivity.this, MeetingCardSelectActivity.listOfMeetings,namebwe,countrybwe,surveyType,operation);
        recyclerView.setAdapter(adapter);
        if(uuidNewMeeting!=null&&!uuidNewMeeting.isEmpty()){
            startHouseholdAttendingActivity(uuidNewMeeting);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        if(operation.contentEquals("CREATE"))
            getMenuInflater().inflate(R.menu.menu_meeting, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.newMeeting) {
            //get list of all volunteers
            ArrayList<Volunteer> listOfVolunteers = new ArrayList<>();
            Amplify.DataStore.query(
                    Volunteer.class,
                    allVolunteers -> {
                        Log.i("Tutorials", "DataStore is queried.");
                        while (allVolunteers.hasNext()) {
                            Volunteer aVolunteer = allVolunteers.next();
                            listOfVolunteers.add(aVolunteer);
                            Log.i("Tutorials", "Title: " + aVolunteer.getNamevol());
                        }
                        if(countrybwe==null){
                            Log.i("bwfsurveybeta", "countrybwe is null" );
                            ArrayList<String> listOfCountries = BwfSurveyAmplifyApplication.getCountries();
                            /*DialogFragment dialog = new SelectCountryDialogFragment(listOfCountries, new SelectCountryDialogFragment.SelectCountryDialogFragmentListener() {
                                @Override
                                public void onSelectedCountry(String countryName) {
                                    countrybwe = countryName;
                                    Log.i("Tutorials", "country selected is " + countrybwe );
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            ArrayList<Community> listOfCommunities = BwfSurveyAmplifyApplication.getCommunities(countrybwe);
                                            showCreateNewMeeting(listOfCommunities,listOfVolunteers);
                                        }
                                    });
                                }
                            });
                            dialog.show(getSupportFragmentManager(), "countries");*/
                            showSelectCountry(listOfCountries);
                        }else {
                            ArrayList<Community> listOfCommunities = BwfSurveyAmplifyApplication.getCommunities(countrybwe);
                            showCreateNewMeeting(listOfCommunities,listOfVolunteers);
                        }
                    },
                    failure ->{
                        Log.e("Tutorials", "Query failed.", failure);
                    });


        }

        return super.onOptionsItemSelected(item);
    }

    private DialogFragment createNewMeeting;
    public void showCreateNewMeeting( ArrayList<Community> communities, ArrayList<Volunteer> volunteers) {

        ArrayList<String> communitiesStr = new ArrayList<>();
        for(Community community : communities) {
            communitiesStr.add(community.getName());
        }
        ArrayList<String> volunteersStr = new ArrayList<>();
        for(Volunteer volunteer : volunteers) {
            volunteersStr.add(volunteer.getNamevol());
        }
        createNewMeeting = CreateNewMeeting.newInstance(communitiesStr,volunteersStr,namebwe,countrybwe);
        createNewMeeting.show(getSupportFragmentManager(), "createNewMeeting");
        createNewMeeting.setCancelable(false);
    }

    SubscriptionToken checkToken = null;
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, Meeting newMeeting) {
        Log.i("Tutorials", "newMeeting " + newMeeting.toString() );

        checkToken = Amplify.Hub.subscribe(
                HubChannel.DATASTORE,
                hubEvent -> DataStoreChannelEventName.OUTBOX_MUTATION_ENQUEUED.toString().equals(hubEvent.getName()),
                hubEvent -> {
                    OutboxMutationEvent event = (OutboxMutationEvent) hubEvent.getData();
                    //Log.i("bwfSurveyAmplify", " Meeting "+event.getModelName());
                    if(event!=null && event.getModelName().contentEquals("Meeting")){
                        if(event.getElement().getModel().getId().equals(newMeeting.getId())){
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    progressBar.setVisibility(View.GONE);
                                    uuidNewMeeting = newMeeting.getId();
                                    showSavedSuccessfulAlert();
                                }
                            });
                        }else{
                            runOnUiThread(new Runnable() {
                              public void run() {
                                  progressBar.setVisibility(View.GONE);
                              }
                            });
                        }
                    }else{
                        runOnUiThread(new Runnable() {
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }
                }
        );
        Amplify.DataStore.save(newMeeting,
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
    }

    private void showSavedSuccessfulAlert(){
        new AlertDialog.Builder(MeetingCardSelectActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Meeting created Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reload the Cards again on show on the recycler is we find a new meeting uuid we will
                        //open householdAttending meeting Card select
                        downloadMeetingListAndShowOnRecyclerView();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private void startHouseholdAttendingActivity(String uuidMeeting) {
        Intent i = new Intent(MeetingCardSelectActivity.this, HouseholdAttendingMeetingCardSelectActivity.class);
        i.putExtra("UUID", uuidMeeting);
        i.putExtra("OPERATION", operation);
        i.putExtra("NAME_BWE", namebwe);
        MeetingCardSelectActivity.this.startActivity(i);
    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(MeetingCardSelectActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Meeting creation Failed Please try again\n" )
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

    private DialogFragment selectCountry;
    public void showSelectCountry(ArrayList<String> listOfCountries) {
        selectCountry = SelectCountryDialogFragment.newInstance(listOfCountries);
        selectCountry.show(getSupportFragmentManager(), "selectCountry");
        selectCountry.setCancelable(false);
    }

    @Override
    public void onSelectedCountry(String countryName) {
        countrybwe = countryName;
    }
}
