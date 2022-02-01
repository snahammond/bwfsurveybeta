package com.bwfsurvey.bwfsurveybeta.activities.select;

import android.app.Activity;
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

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.generated.model.HouseholdAttendingMeeting;
import com.amplifyframework.datastore.syncengine.OutboxMutationEvent;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.SubscriptionToken;
import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.HouseholdAttendingMeetingCardAdapter;
import com.bwfsurvey.bwfsurveybeta.dialogs.CreateNewHouseholdAttendingMeeting;
import com.bwfsurvey.bwfsurveybeta.utils.ListUtils;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class HouseholdAttendingMeetingCardSelectActivity extends AppCompatActivity implements CreateNewHouseholdAttendingMeeting.CreateNewHouseholdAttendingMeetingListener{
    private static ArrayList<HouseholdAttendingMeeting> listOfHouseholdAttendingMeeting;
    private RecyclerView recyclerView;
    private HouseholdAttendingMeetingCardAdapter adapter;
    private String operation = null;
    private String uuidMeeting = null;
    private String nameBWE = null;

    private LinearLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().getStringExtra("OPERATION")!=null)
            operation = getIntent().getStringExtra("OPERATION");
        Log.i("Tutorials", "operation: " + operation);
        if(getIntent().getStringExtra("UUID")!=null)
            uuidMeeting = getIntent().getStringExtra("UUID");
        if(getIntent().getStringExtra("NAME_BWE")!=null)
            nameBWE = getIntent().getStringExtra("NAME_BWE");
        Log.i("Tutorials", "nameBWE: " + nameBWE);

        if(operation.contentEquals("CREATE"))
            setTitle("Households that attended meeting");

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createHouseholdAttendingMeetingCardSelectList();
    }

    private void createHouseholdAttendingMeetingCardSelectList() {
        downloadHouseholdAttendingMeetingListAndShowOnRecyclerView();
    }

    private void downloadHouseholdAttendingMeetingListAndShowOnRecyclerView() {
        Log.i("Tutorials", "going to downloadHouseholdAttendingMeetingListAndShowOnRecyclerView");
        try{

            listOfHouseholdAttendingMeeting = new ArrayList<>();
            Amplify.DataStore.query(
                    HouseholdAttendingMeeting.class,
                    Where.matches(HouseholdAttendingMeeting.MEETING_ID.eq(uuidMeeting)),
                    allHouseholdsAttendingMeeting -> {
                        Log.i("Tutorials", "DataStore is queried.");
                        while (allHouseholdsAttendingMeeting.hasNext()) {
                            HouseholdAttendingMeeting aHouseholdAttendingMeeting = allHouseholdsAttendingMeeting.next();
                            listOfHouseholdAttendingMeeting.add(aHouseholdAttendingMeeting);
                            Log.i("Tutorials", "Title: " + aHouseholdAttendingMeeting.getHeadHouseholdName());
                            //try to send all the Meeting by forcefully pushing
                            Amplify.API.mutate(
                                    ModelMutation.create(aHouseholdAttendingMeeting),
                                    response -> {
                                    },
                                    error -> {
                                    }
                            );
                        }
                        Log.i("Tutorials", "Queried");
                        runOnUiThread(new Runnable() {
                            public void run() {
                                showListOfHouseholdsAttendingMeeting();
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

    private void showListOfHouseholdsAttendingMeeting() {
        if(listOfHouseholdAttendingMeeting.size()>0){
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
            String msg = "Please add new household";
            ListUtils.showZeroListAlert(msg,HouseholdAttendingMeetingCardSelectActivity.this);
        }
    }

    private void initViewElements() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HouseholdAttendingMeetingCardAdapter(HouseholdAttendingMeetingCardSelectActivity.this, HouseholdAttendingMeetingCardSelectActivity.listOfHouseholdAttendingMeeting,operation);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        if(operation.contentEquals("CREATE"))
            getMenuInflater().inflate(R.menu.menu_household_attending_meeting, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.newHouseholdAttendingMeeting) {
            showCreateNewHouseholdAttendingMeeting(HouseholdAttendingMeetingCardSelectActivity.this,uuidMeeting,nameBWE);
        }

        return super.onOptionsItemSelected(item);
    }

    private DialogFragment createNewHouseholdAttendingMeeting;
    public void showCreateNewHouseholdAttendingMeeting(Activity activity, String uuidMeeting, String nameSWE) {
        createNewHouseholdAttendingMeeting = new CreateNewHouseholdAttendingMeeting(activity,uuidMeeting,nameSWE);
        createNewHouseholdAttendingMeeting.show(getSupportFragmentManager(), "createNewHouseholdAttendingMeeting");
        createNewHouseholdAttendingMeeting.setCancelable(false);
    }

    SubscriptionToken checkToken = null;
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, HouseholdAttendingMeeting newHouseholdAttendingMeeting) {
        Log.i("Tutorials", "newHouseholdAttendingMeeting " + newHouseholdAttendingMeeting.toString() );

        checkToken = Amplify.Hub.subscribe(
                HubChannel.DATASTORE,
                hubEvent -> DataStoreChannelEventName.OUTBOX_MUTATION_ENQUEUED.toString().equals(hubEvent.getName()),
                hubEvent -> {
                    OutboxMutationEvent event = (OutboxMutationEvent) hubEvent.getData();
                    //Log.i("bwfSurveyAmplify", " HouseholdAttendingMeeting "+event.getModelName());
                    if(event!=null && event.getModelName().contentEquals("HouseholdAttendingMeeting")){
                        if(event.getElement().getModel().equals(newHouseholdAttendingMeeting)){
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
        Amplify.DataStore.save(newHouseholdAttendingMeeting,
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
        new AlertDialog.Builder(HouseholdAttendingMeetingCardSelectActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Household created Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reload the Cards again
                        downloadHouseholdAttendingMeetingListAndShowOnRecyclerView();
                        //instead of reloading cards open HouseholdAttendingMeetingCardSelect and pass uuid
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(HouseholdAttendingMeetingCardSelectActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Household creation Failed Please try again\n" )
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
