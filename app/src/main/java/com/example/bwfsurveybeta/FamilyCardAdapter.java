package com.example.bwfsurveybeta;

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

import java.util.ArrayList;

public class FamilyCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<InitialSurvey> listOfFamilys;
    private String namebwe;
    private String countrybwe;
    private String surveyType;
    private Context context;

    public FamilyCardAdapter(FamilyCardSelectActivity familyCardSelectActivity, ArrayList<InitialSurvey> listOfFamilys, String namebwe,String countrybwe, String surveyType) {
        this.listOfFamilys = listOfFamilys;
        this.context = familyCardSelectActivity;
        this.namebwe = namebwe;
        this.countrybwe = countrybwe;
        this.surveyType = surveyType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.family_card, parent, false);
        return new FamilyCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((FamilyCardAdapter.FamilyCardViewHolder) holder).setFamilyCardDetails(listOfFamilys.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfFamilys.size();
    }

    class FamilyCardViewHolder extends RecyclerView.ViewHolder{

        private TextView txtCountry;
        private TextView txtCommunity;
        private TextView txtHeadHousehold;

        public FamilyCardViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCountry = (TextView) itemView.findViewById(R.id.txtCountry);
            txtCommunity = (TextView) itemView.findViewById(R.id.txtCommunity);
            txtHeadHousehold = (TextView) itemView.findViewById(R.id.txtHeadHousehold);
            CardView familyCard = (CardView) itemView.findViewById(R.id.familyCard); // creating a CardView and assigning a value.

            familyCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Selected family: " + txtHeadHousehold.getText());

                    if (surveyType.contentEquals("FOLLOWUPSURVEY")) {
                        startFollowUpSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString(), txtHeadHousehold.getText().toString());
                    }else if(surveyType.contentEquals("HEALTHCHECKSURVEY")){
                        startHealthCheckSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString(), txtHeadHousehold.getText().toString());
                    }else if(surveyType.contentEquals("WATERSURVEYHOUSEHOLD")){
                        startHouseholdWaterSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString(), txtHeadHousehold.getText().toString());
                    }
                }
            });
        }


        void setFamilyCardDetails(InitialSurvey family,int position) {
            txtCountry.setText(family.getCountry());
            txtCommunity.setText(family.getCommunity());
            txtHeadHousehold.setText(family.getHeadHouseholdName());
        }
    }

    private void startHouseholdWaterSurveyActivity(String country, String community, String householdName) {
        Intent i = new Intent(this.context, HouseholdWaterSurveyActivity.class);
        i.putExtra("NAME_BWE", namebwe);
        i.putExtra("SURVEY_TYPE",surveyType);
        i.putExtra("COUNTRY",country);
        i.putExtra("COMMUNITY",community);
        i.putExtra("HHNAME",householdName);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    private void startFollowUpSurveyActivity(String country, String community, String householdName) {
        Intent i = new Intent(this.context, FollowUpSurveyActivity.class);
        i.putExtra("NAME_BWE", namebwe);
        i.putExtra("SURVEY_TYPE",surveyType);
        i.putExtra("COUNTRY",country);
        i.putExtra("COMMUNITY",community);
        i.putExtra("HHNAME",householdName);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    private void startHealthCheckSurveyActivity(String country, String community, String householdName) {
        Intent i = new Intent(this.context, HealthCheckSurveyActivity.class);
        i.putExtra("NAME_BWE", namebwe);
        i.putExtra("SURVEY_TYPE",surveyType);
        i.putExtra("COUNTRY",country);
        i.putExtra("COMMUNITY",community);
        i.putExtra("HHNAME",householdName);
        context.startActivity(i);
        ((Activity)context).finish();
    }
}
