package com.bwfsurvey.bwfsurveybeta.activities.collect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bwfsurvey.bwfsurveybeta.activities.select.CommunityCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.MeetingCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.VolunteerCardSelectActivity;
import com.example.bwfsurveybeta.R;

public class SubMenuMonthlySummaryActivity extends AppCompatActivity {
    String namebwe = null;
    String countrybwe = null;
    String lat = null;
    String lng = null;

    private LinearLayout progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().getStringExtra("NAME_BWE")!=null)
            namebwe = getIntent().getStringExtra("NAME_BWE");
        if(getIntent().getStringExtra("COUNTRY_BWE")!=null)
            countrybwe = getIntent().getStringExtra("COUNTRY_BWE");

        if(getIntent().getStringExtra("LAT")!=null)
            lat = getIntent().getStringExtra("LAT");
        if(getIntent().getStringExtra("LNG")!=null)
            lng = getIntent().getStringExtra("LNG");
        setContentView(R.layout.activity_submenu_monthly_summary);
        initView();
    }

    private void initView() {

        Button sweMonthlyTotalSummary = (Button) findViewById(R.id.button_SWESubMonthlyTotalSummary);
        sweMonthlyTotalSummary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SWEMonthlyTotalSummaryActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("POSITION_BWE", "Educator");
                i.putExtra("SURVEY_TYPE","SWESUMMARY");
                i.putExtra("OPERATION","CREATE");
                i.putExtra("LAT",lat);
                i.putExtra("LNG",lng);
                startActivity(i);
            }
        });


        Button volMonthlySummary = (Button) findViewById(R.id.button_VolSubMonthlySummary);
        volMonthlySummary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VolunteerCardSelectActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("POSITION_BWE", "Volunteer");
                i.putExtra("SURVEY_TYPE","VOLUNTEERSUMMARY");
                i.putExtra("OPERATION","CREATE");
                i.putExtra("LAT",lat);
                i.putExtra("LNG",lng);
                startActivity(i);
            }
        });


        Button sweMonthlyEducationSummary = (Button) findViewById(R.id.button_SWESubMonthlyEducationSummary);
        sweMonthlyEducationSummary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MeetingCardSelectActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("COUNTRY_BWE", countrybwe);
                i.putExtra("POSITION_BWE", "Educator");
                i.putExtra("SURVEY_TYPE","SWESUMMARY");
                i.putExtra("OPERATION","CREATE");
                i.putExtra("LAT",lat);
                i.putExtra("LNG",lng);
                startActivity(i);
            }
        });

        Button sweSubMonthlyTotalSummary = (Button) findViewById(R.id.button_SWESubMonthlyTotalSummary);
        sweSubMonthlyTotalSummary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SWEMonthlyTotalSummaryActivity.class);
                i.putExtra("NAME_BWE", namebwe);
                i.putExtra("POSITION_BWE", "Educator");
                i.putExtra("SURVEY_TYPE","SWETOTALSUMMARY");
                i.putExtra("OPERATION","CREATE");
                i.putExtra("LAT",lat);
                i.putExtra("LNG",lng);
                startActivity(i);
            }
        });
    }
}
