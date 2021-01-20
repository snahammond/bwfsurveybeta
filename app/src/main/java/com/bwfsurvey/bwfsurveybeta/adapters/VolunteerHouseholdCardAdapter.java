package com.bwfsurvey.bwfsurveybeta.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.InitialSurvey;
import com.amplifyframework.datastore.generated.model.VolunteerHouseholdWaterTest;
import com.bwfsurvey.bwfsurveybeta.activities.HouseholdCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.VolHouseholdCardSelectActivity;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class VolunteerHouseholdCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<VolunteerHouseholdWaterTest> listOfVolHouseholds;
    private String namebwe;
    private String countrybwe;
    private String surveyType;
    private String operation;
    private Context context;

    public VolunteerHouseholdCardAdapter(VolHouseholdCardSelectActivity volHouseholdCardSelectActivity, ArrayList<VolunteerHouseholdWaterTest> listOfVolHouseholds, String namebwe, String countrybwe, String surveyType, String operation) {
        this.listOfVolHouseholds = listOfVolHouseholds;
        this.context = volHouseholdCardSelectActivity;
        this.namebwe = namebwe;
        this.countrybwe = countrybwe;
        this.surveyType = surveyType;
        this.operation = operation;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vol_household_card, parent, false);
        return new VolHouseholdCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((VolunteerHouseholdCardAdapter.VolHouseholdCardViewHolder) holder).setVolHouseholCardDetails(listOfVolHouseholds.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfVolHouseholds.size();
    }

    private class VolHouseholdCardViewHolder extends RecyclerView.ViewHolder {
        private TextView txtFamilySurveyId;
        private TextView txtCountry;
        private TextView txtCommunity;
        private TextView txtHeadHousehold;
        private String uuidVolHousehold;

        public VolHouseholdCardViewHolder(View view) {
            super(view);
            txtFamilySurveyId = (TextView) itemView.findViewById(R.id.txtFamilySurveyId);
            txtCountry = (TextView) itemView.findViewById(R.id.txtCountry);
            txtCommunity = (TextView) itemView.findViewById(R.id.txtCommunity);
            txtHeadHousehold = (TextView) itemView.findViewById(R.id.txtHeadHousehold);
            CardView volHouseholdCard = (CardView) itemView.findViewById(R.id.volHouseholdCard); // creating a CardView and assigning a value.

            volHouseholdCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Selected family: " + txtHeadHousehold.getText());
                    if(operation.contentEquals("CREATE")){
                       // startFollowUpSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString(), txtHeadHousehold.getText().toString(), txtFamilySurveyId.getText().toString());
                    }else if(operation.contentEquals("VIEW")){
                        //startViewInitialSurveyActivity(uuidInitialSurvey);
                    }else if(operation.contentEquals("UPDATE")){
                        //startUpdateInitialSurveyActivity(uuidInitialSurvey);
                    }

                }
            });
        }

        void setVolHouseholCardDetails(VolunteerHouseholdWaterTest volunteerHouseholdWaterTest,int position) {
            //txtFamilySurveyId.setText(Integer.toString(initialSurvey.getSurveyId()));
            txtCountry.setText(volunteerHouseholdWaterTest.getCountry());
            txtCommunity.setText(volunteerHouseholdWaterTest.getCommunity());
            txtHeadHousehold.setText(volunteerHouseholdWaterTest.getHeadHouseholdName());
            uuidVolHousehold = volunteerHouseholdWaterTest.getId();
        }

    }
}
