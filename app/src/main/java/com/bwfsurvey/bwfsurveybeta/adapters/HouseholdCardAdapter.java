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

import com.amplifyframework.datastore.generated.model.InitialSurvey;
import com.bwfsurvey.bwfsurveybeta.activities.select.HouseholdCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.collect.FollowUpSurveyActivity;
import com.bwfsurvey.bwfsurveybeta.activities.collect.HealthCheckSurveyActivity;
import com.bwfsurvey.bwfsurveybeta.activities.collect.HouseholdWaterSurveyActivity;
import com.bwfsurvey.bwfsurveybeta.activities.update.UpdateInitialSurveyActivity;
import com.bwfsurvey.bwfsurveybeta.activities.view.ViewInitialSurveyActivity;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class HouseholdCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<InitialSurvey> listOfFamilys;
    private String namebwe;
    private String countrybwe;
    private String surveyType;
    private String operation;
    private String lat;
    private String lng;
    private Context context;

    public HouseholdCardAdapter(HouseholdCardSelectActivity familyCardSelectActivity, ArrayList<InitialSurvey> listOfFamilys, String namebwe, String countrybwe, String surveyType, String operation, String lat, String lng) {
        this.listOfFamilys = listOfFamilys;
        this.context = familyCardSelectActivity;
        this.namebwe = namebwe;
        this.countrybwe = countrybwe;
        this.surveyType = surveyType;
        this.operation = operation;
        this.lat = lat;
        this.lng = lng;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.family_card, parent, false);
        return new HouseholdCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HouseholdCardAdapter.HouseholdCardViewHolder) holder).setFamilyCardDetails(listOfFamilys.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfFamilys.size();
    }

    class HouseholdCardViewHolder extends RecyclerView.ViewHolder{

        private TextView txtFamilySurveyId;
        private TextView txtCountry;
        private TextView txtCommunity;
        private TextView txtHeadHousehold;
        private String uuidInitialSurvey;

        public HouseholdCardViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFamilySurveyId = (TextView) itemView.findViewById(R.id.txtFamilySurveyId);
            txtCountry = (TextView) itemView.findViewById(R.id.txtCountry);
            txtCommunity = (TextView) itemView.findViewById(R.id.txtCommunity);
            txtHeadHousehold = (TextView) itemView.findViewById(R.id.txtHeadHousehold);
            CardView familyCard = (CardView) itemView.findViewById(R.id.familyCard); // creating a CardView and assigning a value.

            familyCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Selected family: " + txtHeadHousehold.getText());
                    if(operation.contentEquals("CREATE")){
                        if (surveyType.contentEquals("FOLLOWUPSURVEY")) {
                            startFollowUpSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString(), txtHeadHousehold.getText().toString(), txtFamilySurveyId.getText().toString());
                        }else if(surveyType.contentEquals("HEALTHCHECKSURVEY")){
                            startHealthCheckSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString(), txtHeadHousehold.getText().toString(), txtFamilySurveyId.getText().toString());
                        }else if(surveyType.contentEquals("WATERSURVEYHOUSEHOLD")){
                            startHouseholdWaterSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString(), txtHeadHousehold.getText().toString(), txtFamilySurveyId.getText().toString());
                        }
                    }else if(operation.contentEquals("VIEW")){
                        startViewInitialSurveyActivity(uuidInitialSurvey);
                    }else if(operation.contentEquals("UPDATE")){
                        startUpdateInitialSurveyActivity(uuidInitialSurvey);
                    }

                }
            });
        }


        void setFamilyCardDetails(InitialSurvey initialSurvey,int position) {
            txtFamilySurveyId.setText(Integer.toString(initialSurvey.getSurveyId()));
            txtCountry.setText(initialSurvey.getCountry());
            txtCommunity.setText(initialSurvey.getCommunity());
            txtHeadHousehold.setText(initialSurvey.getHeadHouseholdName());
            uuidInitialSurvey = initialSurvey.getId();
        }
    }

    private void startHouseholdWaterSurveyActivity(String country, String community, String householdName, String familySurveyId) {
        Intent i = new Intent(this.context, HouseholdWaterSurveyActivity.class);
        i.putExtra("NAME_BWE", namebwe);
        i.putExtra("SURVEY_TYPE",surveyType);
        i.putExtra("COUNTRY",country);
        i.putExtra("COMMUNITY",community);
        i.putExtra("HHNAME",householdName);
        i.putExtra("SURVEY_ID",familySurveyId);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    private void startFollowUpSurveyActivity(String country, String community, String householdName, String familySurveyId) {
        Intent i = new Intent(this.context, FollowUpSurveyActivity.class);
        i.putExtra("NAME_BWE", namebwe);
        i.putExtra("SURVEY_TYPE",surveyType);
        i.putExtra("COUNTRY",country);
        i.putExtra("COMMUNITY",community);
        i.putExtra("HHNAME",householdName);
        i.putExtra("SURVEY_ID",familySurveyId);
        i.putExtra("LAT",lat);
        i.putExtra("LNG",lng);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    private void startHealthCheckSurveyActivity(String country, String community, String householdName, String familySurveyId) {
        Intent i = new Intent(this.context, HealthCheckSurveyActivity.class);
        i.putExtra("NAME_BWE", namebwe);
        i.putExtra("SURVEY_TYPE",surveyType);
        i.putExtra("COUNTRY",country);
        i.putExtra("COMMUNITY",community);
        i.putExtra("HHNAME",householdName);
        i.putExtra("SURVEY_ID",familySurveyId);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    private void startViewInitialSurveyActivity(String uuidInitialSurvey){
        Intent i = new Intent(context, ViewInitialSurveyActivity.class);
        i.putExtra("UUID", uuidInitialSurvey);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    private void startUpdateInitialSurveyActivity(String uuidInitialSurvey){
        Intent i = new Intent(context, UpdateInitialSurveyActivity.class);
        i.putExtra("UUID", uuidInitialSurvey);
        context.startActivity(i);
        ((Activity)context).finish();
    }
}
