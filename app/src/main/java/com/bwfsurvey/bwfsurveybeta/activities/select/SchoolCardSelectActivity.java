package com.bwfsurvey.bwfsurveybeta.activities.select;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bwfsurvey.bwfsurveybeta.BwfSurveyAmplifyApplication;
import com.bwfsurvey.bwfsurveybeta.adapters.SchoolCardAdapter;
import com.bwfsurvey.bwfsurveybeta.types.School;
import com.bwfsurvey.bwfsurveybeta.utils.ListUtils;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class SchoolCardSelectActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SchoolCardAdapter adapter;

    private static ArrayList<School> listOfSchools;

    private String namebwe = null;
    private String countrybwe = null;
    private String positionbwe = null;
    private String community = null;
    private String surveyType = null;

    private String lat = null;
    private String lng = null;

    @Override
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
        createSchoolCardSelectList();
    }

    private void createSchoolCardSelectList(){
        listOfSchools = new ArrayList<>();
        getSchoolListAndShowOnRecyclerView();
    }

    private void getSchoolListAndShowOnRecyclerView(){
        if(community!=null && !community.equals("")){
            listOfSchools = BwfSurveyAmplifyApplication.getSchools(community);
            showListOfSchools();
        }
        else{
            //do something
        }
    }

    private void showListOfSchools() {
        if(listOfSchools!=null && listOfSchools.size()>0){
            initViewElements();
        }else{
            ListUtils.showZeroListAlert("No school can be found for the current community; " + community +". ",SchoolCardSelectActivity.this);
        }
    }

    private void initViewElements() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SchoolCardAdapter(SchoolCardSelectActivity.this, SchoolCardSelectActivity.listOfSchools,namebwe,countrybwe,community,positionbwe,surveyType,lat,lng);
        recyclerView.setAdapter(adapter);
    }
}
