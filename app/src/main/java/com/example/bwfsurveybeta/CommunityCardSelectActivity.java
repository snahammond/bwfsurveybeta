package com.example.bwfsurveybeta;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.InitialSurvey;

import java.util.ArrayList;

public class CommunityCardSelectActivity extends AppCompatActivity {
    private static ArrayList<Community> listOfCommunities;
    private RecyclerView recyclerView;
    private CommunityCardAdapter adapter;

    private String namebwe = null;
    private String countrybwe = null;
    private String positionbwe = null;
    private String surveyType = null;

    private LinearLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("NAME_BWE")!=null)
            namebwe = getIntent().getStringExtra("NAME_BWE");
        if(getIntent().getStringExtra("COUNTRY_BWE")!=null)
            countrybwe = getIntent().getStringExtra("COUNTRY_BWE");
        if(getIntent().getStringExtra("POSITION_BWE")!=null)
            positionbwe = getIntent().getStringExtra("POSITION_BWE");
        Log.i("Tutorials", "namebwe: " + namebwe);
        if(getIntent().getStringExtra("SURVEY_TYPE")!=null)
            surveyType = getIntent().getStringExtra("SURVEY_TYPE");
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler);
        createCommunityCardSelectList();
    }

    private void createCommunityCardSelectList() {
        listOfCommunities = new ArrayList<>();
        getCommunityListAndShowOnRecyclerView();
    }

    private void getCommunityListAndShowOnRecyclerView() {
        listOfCommunities = MyAmplifyApplication.getCommunities(countrybwe);
        showListOfCommunities();
    }

    private void showListOfCommunities() {
        initViewElements();
    }

    private void initViewElements() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommunityCardAdapter(CommunityCardSelectActivity.this, CommunityCardSelectActivity.listOfCommunities,namebwe,positionbwe,surveyType);
        recyclerView.setAdapter(adapter);
    }
}
