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

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.generated.model.Volunteer;
import com.amplifyframework.datastore.syncengine.OutboxMutationEvent;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.SubscriptionToken;
import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.VolunteerCardAdapter;
import com.bwfsurvey.bwfsurveybeta.dialogs.CreateNewVolunteer;
import com.bwfsurvey.bwfsurveybeta.utils.ListUtils;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class VolunteerCardSelectActivity extends AppCompatActivity implements CreateNewVolunteer.CreateNewVolunteerListener{
    private static ArrayList<Volunteer> listOfVolunteers;
    private RecyclerView recyclerView;
    private VolunteerCardAdapter adapter;
    private String namebwe = null;
    private String countrybwe = null;
    private String community = null;
    private String householdName = null;
    private String householdLocation = null;
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
        Log.i("Tutorials", "operation: " + operation);
        if(getIntent().getStringExtra("COMMUNITY")!=null)
            community = getIntent().getStringExtra("COMMUNITY");
        if(getIntent().getStringExtra("HHNAME")!=null)
            householdName = getIntent().getStringExtra("HHNAME");
        if(getIntent().getStringExtra("HHLOC")!=null)
            householdLocation = getIntent().getStringExtra("HHLOC");

        if(operation.contentEquals("CREATE"))
            setTitle("Select or create new Volunteer");

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createVolunteerCardSelectList();
    }

    private void createVolunteerCardSelectList() {
        downloadVolunteerListAndShowOnRecyclerView();
    }

    private void downloadVolunteerListAndShowOnRecyclerView() {
        Log.i("Tutorials", "going to downloadVolunteerListAndShowOnRecyclerView");
        try{

            listOfVolunteers = new ArrayList<>();
            Amplify.DataStore.query(
                    Volunteer.class,
                    allVolunteers -> {
                        Log.i("Tutorials", "DataStore is queried.");
                        while (allVolunteers.hasNext()) {
                            Volunteer aVolunteer = allVolunteers.next();
                            listOfVolunteers.add(aVolunteer);
                            Log.i("Tutorials", "Title: " + aVolunteer.getNamevol());
                            //try to send all the InitialSurveys by forcefully pushing
                            Amplify.API.mutate(
                                    ModelMutation.create(aVolunteer),
                                    response -> {
                                    },
                                    error -> {
                                    }
                            );
                        }
                        Log.i("Tutorials", "Queried");
                        runOnUiThread(new Runnable() {
                            public void run() {
                                showListOfVolunteers();
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

    private void showListOfVolunteers() {
        if(listOfVolunteers.size()>0){
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
            String msg = "No volunteer found, please create new volunteers";
            ListUtils.showZeroListAlert(msg,VolunteerCardSelectActivity.this);
        }

    }

    private void initViewElements() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VolunteerCardAdapter(VolunteerCardSelectActivity.this, VolunteerCardSelectActivity.listOfVolunteers,namebwe,countrybwe,community,householdName, householdLocation,surveyType,operation);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        if(operation.contentEquals("CREATE"))
            getMenuInflater().inflate(R.menu.menu_volunteer, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.newVolunteer) {
            showCreateNewVolunteer();
        }

        return super.onOptionsItemSelected(item);
    }

    private DialogFragment createNewVolunteer;
    public void showCreateNewVolunteer() {
        createNewVolunteer = new CreateNewVolunteer(namebwe);
        createNewVolunteer.show(getSupportFragmentManager(), "createNewVolunteer");
    }

    SubscriptionToken checkToken = null;
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, Volunteer newVolunteer) {
        Log.i("Tutorials", "newVolunteer " + newVolunteer.getNamevol()  );

        if (newVolunteer.getNamevol()!=null&& !newVolunteer.getNamevol().equals("")){
            checkToken = Amplify.Hub.subscribe(
                    HubChannel.DATASTORE,
                    hubEvent -> DataStoreChannelEventName.OUTBOX_MUTATION_ENQUEUED.toString().equals(hubEvent.getName()),
                    hubEvent -> {
                        OutboxMutationEvent event = (OutboxMutationEvent) hubEvent.getData();
                        //Log.i("bwfSurveyAmplify", " VolunteerHousehold "+event.getModelName());
                        if(event!=null && event.getModelName().contentEquals("Volunteer")){
                            if(event.getElement().getModel().equals(newVolunteer)){
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
            Amplify.DataStore.save(newVolunteer,
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
            Log.i("Tutorials", "newVolunteer data not valid" );
        }
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
        new AlertDialog.Builder(VolunteerCardSelectActivity.this)
                .setTitle("Saved Succussfully")
                .setMessage("Volunteer created Succussfully \n" )
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //reload the Cards again
                        downloadVolunteerListAndShowOnRecyclerView();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private void showSaveFailedAlert(){
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(VolunteerCardSelectActivity.this)
                        .setTitle("Save Failed")
                        .setMessage("Volunteer creation Failed Please try again\n" )
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
