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

import com.bwfsurvey.bwfsurveybeta.activities.collect.SWEMonthlyClinicSummaryActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.ClinicCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.types.Clinic;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class ClinicCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Clinic> listOfClinics;
    private String namebwe;
    private String countrybwe;
    private String community;
    private String positionbwe = null;
    private String surveyType;
    private String lat = null;
    private String lng = null;
    private Context context;

    public ClinicCardAdapter(ClinicCardSelectActivity clinicCardSelectActivity, ArrayList<Clinic> listOfClinics, String namebwe, String countrybwe, String community, String positionbwe, String surveyType, String lat, String lng) {
        this.listOfClinics = listOfClinics;
        this.context = clinicCardSelectActivity;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_card, parent, false);
        return new ClinicCardViewHolder(view);
    }

    class ClinicCardViewHolder extends RecyclerView.ViewHolder{

        private TextView txtCommunity;
        private TextView txtClinic;

        public ClinicCardViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCommunity = (TextView) itemView.findViewById(R.id.txtCommunity);
            txtClinic = (TextView) itemView.findViewById(R.id.txtClinic);


            CardView schoolCard = (CardView) itemView.findViewById(R.id.clinicCard); // creating a CardView and assigning a value.

            schoolCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Selected school: " + txtClinic.getText());

                    if(surveyType.contentEquals("CLINICACTIVITYSUMMARY")){
                        startClinicSummaryActivity(txtClinic.getText().toString());
                    }
                }
            });
        }


        void setClinicCardDetails(Clinic clinic,int position) {
            txtCommunity.setText(clinic.getCommunityName());
            txtClinic.setText(clinic.getName());
        }
    }

    private void startClinicSummaryActivity(String clinic){
        Intent i = new Intent(this.context, SWEMonthlyClinicSummaryActivity.class);
        i.putExtra("NAME_BWE", namebwe);
        i.putExtra("POSITION_BWE", positionbwe);
        i.putExtra("SURVEY_TYPE",surveyType);
        i.putExtra("COUNTRY_BWE",countrybwe);
        i.putExtra("COMMUNITY",community);
        i.putExtra("CLINIC",clinic);
        i.putExtra("LAT",lat);
        i.putExtra("LNG",lng);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ClinicCardAdapter.ClinicCardViewHolder) holder).setClinicCardDetails(listOfClinics.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfClinics.size();
    }
}
