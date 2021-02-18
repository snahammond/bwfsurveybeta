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

import com.amplifyframework.datastore.generated.model.CommunityWater;
import com.bwfsurvey.bwfsurveybeta.activities.select.CommunityWaterCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.collect.CommunityWaterSurveyActivity;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class CommunityWaterCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<CommunityWater> listOfCommunityWater;
    private String namebwe;
    private String countrybwe;
    private String positionbwe = null;
    private String surveyType;
    private String operation;
    private Context context;
    private String lat = null;
    private String lng = null;


    public CommunityWaterCardAdapter(CommunityWaterCardSelectActivity communityWaterCardSelectActivity, ArrayList<CommunityWater> listOfCommunityWater, String namebwe, String countrybwe, String surveyType, String operation) {
        this.listOfCommunityWater = listOfCommunityWater;
        this.context = communityWaterCardSelectActivity;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_water_card, parent, false);
        return new CommunityWaterCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CommunityWaterCardViewHolder) holder).setCommunityWaterCardDetails(listOfCommunityWater.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfCommunityWater.size();
    }

    private class CommunityWaterCardViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCountry;
        private TextView txtCommunity;
        private TextView txtCommunityWaterLoc;

        public CommunityWaterCardViewHolder(View view) {
            super(view);
            txtCountry = (TextView) itemView.findViewById(R.id.txtCountry);
            txtCommunity = (TextView) itemView.findViewById(R.id.txtCommunity);
            txtCommunityWaterLoc = (TextView) itemView.findViewById(R.id.txtCommunityWaterLoc);
            CardView communityWaterCard = (CardView) itemView.findViewById(R.id.volHouseholdCard); // creating a CardView and assigning a value.

            communityWaterCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Selected family: " + txtCommunityWaterLoc.getText());

                    if(operation.contentEquals("CREATE")){
                        startCommunityWaterSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString(), txtCommunityWaterLoc.getText().toString());
                    }else if(operation.contentEquals("VIEW")){
                        //startViewInitialSurveyActivity(uuidInitialSurvey);
                    }else if(operation.contentEquals("UPDATE")){
                        //startUpdateInitialSurveyActivity(uuidInitialSurvey);
                    }


                }
            });
        }

        void setCommunityWaterCardDetails(CommunityWater communityWater,int position) {
            //txtFamilySurveyId.setText(Integer.toString(initialSurvey.getSurveyId()));
            txtCountry.setText(communityWater.getCountry());
            txtCommunity.setText(communityWater.getCommunity());
            txtCommunityWaterLoc.setText(communityWater.getCommunityWaterLocation());
        }
    }

    private void startCommunityWaterSurveyActivity(String country, String community, String communityWaterLoc) {
        Intent i = new Intent(this.context, CommunityWaterSurveyActivity.class);
        i.putExtra("NAME_BWE", namebwe);
        i.putExtra("POSITION_BWE", positionbwe);
        i.putExtra("SURVEY_TYPE",surveyType);
        i.putExtra("COUNTRY_BWE",country);
        i.putExtra("COMMUNITY",community);
        i.putExtra("COMMUNITY_WATER_LOC",communityWaterLoc);
        i.putExtra("LAT",lat);
        i.putExtra("LNG",lng);
        context.startActivity(i);
        ((Activity)context).finish();
    }
}
