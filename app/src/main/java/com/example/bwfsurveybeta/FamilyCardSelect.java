package com.example.bwfsurveybeta;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.InitialSurvey;

import java.util.ArrayList;

public class FamilyCardSelect extends AppCompatActivity {
    private static ArrayList<InitialSurvey> listOfFamilys;
    private RecyclerView recyclerView;
    private FamilyCardAdapter adapter;
    private String namebwe = null;
    private String surveyType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        if(getIntent().getStringExtra("NAME_BWE")!=null)
            namebwe = getIntent().getStringExtra("NAME_BWE");
        Log.i("Tutorials", "namebwe: " + namebwe);
        if(getIntent().getStringExtra("SURVEY_TYPE")!=null)
            surveyType = getIntent().getStringExtra("SURVEY_TYPE");
        initView();
    }

    private void initView() {
        createFollowUpSelectList();
        /*
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InterchangeCardAdapter(InitialSurveyActivity.this, InitialSurveyActivity.interchanges);
        recyclerView.setAdapter(adapter);
         */
    }

    private void createFollowUpSelectList(){
        listOfFamilys = new ArrayList<>();
        downloadInitialSurveyListAndShowOnRecyclerView();
    }

    public void downloadInitialSurveyListAndShowOnRecyclerView(){
        try{
            Amplify.DataStore.query(
                    InitialSurvey.class,
                    allInitialSurveyFamilys -> {
                        Log.i("Tutorials", "DataStore is queried.");
                        while (allInitialSurveyFamilys.hasNext()) {
                            InitialSurvey aFamily = allInitialSurveyFamilys.next();
                            listOfFamilys.add(aFamily);
                            Log.i("Tutorials", "Title: " + aFamily.getHeadHouseholdName());
                        }

                        runOnUiThread(new Runnable() {
                            public void run() {
                                showListOfFamilys();
                            }
                        });
                    },
                    failure ->{
                        Log.e("Tutorials", "Query failed.", failure);
                        showErrorListOfFamilys();
                    }
            );
        }catch (Exception x){
            //show less menu
            Log.i("Tutorials", "show less menu.");
            showErrorListOfFamilys();
        }

    }

    private void showListOfFamilys() {
        setContentView(R.layout.activity_recycler);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FamilyCardAdapter(FamilyCardSelect.this, FamilyCardSelect.listOfFamilys,namebwe,surveyType);
        recyclerView.setAdapter(adapter);
    }

    private void showErrorListOfFamilys() {
        setContentView(R.layout.activity_no_family);
    }
}
