package com.bwfsurvey.bwfsurveybeta.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bwfsurvey.bwfsurveybeta.activities.collect.CommunityWaterSurveyActivity;
import com.bwfsurvey.bwfsurveybeta.activities.collect.SWEMonthlySchoolSummaryActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.SchoolCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.types.Community;
import com.bwfsurvey.bwfsurveybeta.types.School;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class SchoolCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<School> listOfSchools;
    private String namebwe;
    private String countrybwe;
    private String community;
    private String positionbwe = null;
    private String surveyType;
    private String lat = null;
    private String lng = null;
    private Context context;

    public SchoolCardAdapter(SchoolCardSelectActivity schoolCardSelectActivity, ArrayList<School> listOfSchools, String namebwe, String countrybwe, String community, String positionbwe, String surveyType, String lat, String lng) {
        this.listOfSchools = listOfSchools;
        this.context = schoolCardSelectActivity;
        this.namebwe = namebwe;
        this.countrybwe = countrybwe;
        this.community = community;
        this.positionbwe = positionbwe;
        this.surveyType = surveyType;
        this.lat = lat;
        this.lng = lng;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.school_card, parent, false);
        return new SchoolCardViewHolder(view);
    }

    class SchoolCardViewHolder extends RecyclerView.ViewHolder{

        private TextView txtCommunity;
        private TextView txtSchool;

        public SchoolCardViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCommunity = (TextView) itemView.findViewById(R.id.txtCommunity);
            txtSchool = (TextView) itemView.findViewById(R.id.txtSchool);


            CardView schoolCard = (CardView) itemView.findViewById(R.id.schoolCard); // creating a CardView and assigning a value.

            schoolCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Selected school: " + txtSchool.getText());

                    if(surveyType.contentEquals("SCHACTIVITYSUMMARY")){
                        startSchoolSummaryActivity(txtSchool.getText().toString());
                    }
                }
            });
        }


        void setSchoolCardDetails(School school,int position) {
            txtCommunity.setText(school.getCommunityName());
            txtSchool.setText(school.getName());
        }
    }

    private void startSchoolSummaryActivity(String school){
        Intent i = new Intent(this.context, SWEMonthlySchoolSummaryActivity.class);
        i.putExtra("NAME_BWE", namebwe);
        i.putExtra("POSITION_BWE", positionbwe);
        i.putExtra("SURVEY_TYPE",surveyType);
        i.putExtra("COUNTRY_BWE",countrybwe);
        i.putExtra("COMMUNITY",community);
        i.putExtra("SCHOOL",school);
        i.putExtra("LAT",lat);
        i.putExtra("LNG",lng);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SchoolCardAdapter.SchoolCardViewHolder) holder).setSchoolCardDetails(listOfSchools.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfSchools.size();
    }
}
