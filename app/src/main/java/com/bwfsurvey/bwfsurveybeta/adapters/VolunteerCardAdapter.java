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

import com.amplifyframework.datastore.generated.model.Volunteer;
import com.amplifyframework.datastore.generated.model.VolunteerHousehold;
import com.bwfsurvey.bwfsurveybeta.activities.SWEMonthlySummaryActivity;
import com.bwfsurvey.bwfsurveybeta.activities.VolunteerCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.VolunteerHouseholdWaterSurveyActivity;
import com.bwfsurvey.bwfsurveybeta.activities.VolunteerMonthlySummaryActivity;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class VolunteerCardAdapter extends RecyclerView.Adapter {
    private ArrayList<Volunteer> listOfVolunteers;
    private String namebwe;
    private String countrybwe;
    private String community;
    private String householdName;
    private String householdLocation;
    private String surveyType;
    private String operation;
    private Context context;

    public VolunteerCardAdapter(VolunteerCardSelectActivity volunteerCardSelectActivity, ArrayList<Volunteer> listOfVolunteers, String namebwe, String countrybwe, String community, String householdName, String householdLocation, String surveyType, String operation) {
        this.listOfVolunteers = listOfVolunteers;
        this.context = volunteerCardSelectActivity;
        this.namebwe = namebwe;
        this.countrybwe = countrybwe;
        this.community = community;
        this.householdName = householdName;
        this.householdLocation = householdLocation;
        this.surveyType = surveyType;
        this.operation = operation;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.volunteer_card, parent, false);
        return new VolunteerCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((VolunteerCardAdapter.VolunteerCardViewHolder) holder).setVolunteerCardDetails(listOfVolunteers.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfVolunteers.size();
    }

    private class VolunteerCardViewHolder extends RecyclerView.ViewHolder {

        private TextView txtVolunteerName;

        public VolunteerCardViewHolder(View view) {
            super(view);
            txtVolunteerName = (TextView) itemView.findViewById(R.id.txtVolunteerName);
            CardView volunteerCard = (CardView) itemView.findViewById(R.id.volunteerCard); // creating a CardView and assigning a value.

            volunteerCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Volunteer name: " + txtVolunteerName.getText());

                    if(operation.contentEquals("CREATE")){
                        if(surveyType.contentEquals("WATERSURVEYHOUSEHOLD"))
                            startVolHouseholdWaterSurveyActivity(txtVolunteerName.getText().toString());
                        else if(surveyType.contentEquals("SWESUMMARY"))
                            startVolMonthlySummaryActivity(txtVolunteerName.getText().toString());
                    }else if(operation.contentEquals("VIEW")){
                        //startViewInitialSurveyActivity(uuidInitialSurvey);
                    }else if(operation.contentEquals("UPDATE")){
                        //startUpdateInitialSurveyActivity(uuidInitialSurvey);
                    }


                }
            });
        }

        void setVolunteerCardDetails(Volunteer volunteer, int position) {
            txtVolunteerName.setText(volunteer.getNamevol());
        }

    }

    private void startVolMonthlySummaryActivity(String volunteerName) {
        Intent i = new Intent(this.context, VolunteerMonthlySummaryActivity.class);
        i.putExtra("NAME_BWE", namebwe);
        i.putExtra("POSITION_BWE", "Volunteer");
        i.putExtra("SURVEY_TYPE","SWESUMMARY");
        i.putExtra("OPERATION","CREATE");
        i.putExtra("NAME_VOL",volunteerName);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    private void startVolHouseholdWaterSurveyActivity(String volunteerName) {
        Intent i = new Intent(this.context, VolunteerHouseholdWaterSurveyActivity.class);
        i.putExtra("NAME_BWE", namebwe);
        i.putExtra("SURVEY_TYPE",surveyType);
        i.putExtra("COUNTRY",countrybwe);
        i.putExtra("COMMUNITY",community);
        i.putExtra("HHNAME",householdName);
        i.putExtra("HHLOC",householdLocation);
        i.putExtra("NAME_VOL",volunteerName);
        context.startActivity(i);
        ((Activity)context).finish();
    }
}
