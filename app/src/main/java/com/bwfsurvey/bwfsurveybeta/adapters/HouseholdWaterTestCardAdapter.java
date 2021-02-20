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

import com.amplifyframework.datastore.generated.model.HouseholdWaterTest;
import com.bwfsurvey.bwfsurveybeta.activities.select.HouseholdWaterTestCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.update.UpdateHouseholdWaterTestActivity;
import com.bwfsurvey.bwfsurveybeta.activities.view.ViewCommunityWaterTestActivity;
import com.bwfsurvey.bwfsurveybeta.activities.view.ViewHouseholdWaterTestActivity;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class HouseholdWaterTestCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<HouseholdWaterTest> listOfHouseholdWaterTest;
    private String namebwe;
    private String countrybwe;
    private String surveyType;
    private String operation;
    private String lat;
    private String lng;
    private Context context;

    public HouseholdWaterTestCardAdapter(HouseholdWaterTestCardSelectActivity updateHouseholdWaterCardSelectActivity, ArrayList<HouseholdWaterTest> listOfHouseholdWaterTest, String namebwe, String countrybwe, String surveyType, String operation, String lat, String lng) {
        this.listOfHouseholdWaterTest = listOfHouseholdWaterTest;
        this.context = updateHouseholdWaterCardSelectActivity;
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
        return new HouseholdWaterTestCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HouseholdWaterTestCardViewHolder) holder).setHouseholdWaterCardDetails(listOfHouseholdWaterTest.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfHouseholdWaterTest.size();
    }

    private class HouseholdWaterTestCardViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCountry;
        private TextView txtCommunity;
        private TextView txtHeadHousehold;
        private String uuidHouseholdWaterTest;

        public HouseholdWaterTestCardViewHolder(View view) {
            super(view);
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
                        //
                    }else if(operation.contentEquals("VIEW")){
                        startViewHouseholdWaterTestActivity(uuidHouseholdWaterTest);
                    }else if(operation.contentEquals("UPDATE")){
                        startUpdateHouseholdWaterTestActivity(uuidHouseholdWaterTest);
                    }
                }
            });
        }

        void setHouseholdWaterCardDetails(HouseholdWaterTest householdWaterTest, int position) {
            //txtFamilySurveyId.setText(Integer.toString(initialSurvey.getSurveyId()));
            txtCountry.setText(householdWaterTest.getCountry());
            txtCommunity.setText(householdWaterTest.getCommunity());
            txtHeadHousehold.setText(householdWaterTest.getHeadHouseholdName());
            uuidHouseholdWaterTest = householdWaterTest.getId();
        }
    }

    private void startUpdateHouseholdWaterTestActivity(String uuidHouseholdWaterTest){
        Intent i = new Intent(context, UpdateHouseholdWaterTestActivity.class);
        i.putExtra("UUID", uuidHouseholdWaterTest);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    private void startViewHouseholdWaterTestActivity(String uuidHouseholdWaterTest){
        Intent i = new Intent(context, ViewHouseholdWaterTestActivity.class);
        i.putExtra("UUID", uuidHouseholdWaterTest);
        context.startActivity(i);
        ((Activity)context).finish();
    }
}
