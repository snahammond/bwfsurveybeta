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

import java.util.ArrayList;

public class CommunityCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Community> listOfCommunities;
    private String namebwe;
    private String positionbwe = null;
    private String surveyType;
    private Context context;

    public CommunityCardAdapter(CommunityCardSelectActivity communityCardSelectActivity, ArrayList<Community> listOfCommunities, String namebwe,String positionbwe, String surveyType) {
        this.listOfCommunities = listOfCommunities;
        this.context = communityCardSelectActivity;
        this.namebwe = namebwe;
        this.positionbwe = positionbwe;
        this.surveyType = surveyType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_card, parent, false);
        return new CommunityCardViewHolder(view);
    }

    class CommunityCardViewHolder extends RecyclerView.ViewHolder{

        private TextView txtCountry;
        private TextView txtCommunity;


        public CommunityCardViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCountry = (TextView) itemView.findViewById(R.id.txtCountry);
            txtCommunity = (TextView) itemView.findViewById(R.id.txtCommunity);

            CardView communityCard = (CardView) itemView.findViewById(R.id.communityCard); // creating a CardView and assigning a value.

            communityCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Selected community: " + txtCommunity.getText());

                    if (surveyType.contentEquals("INITIALSURVEY")) {
                        startInitialSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString());
                    }else if(surveyType.contentEquals("WATERSURVEYCOMMUNITY")) {
                        startCommunityWaterSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString());
                    }
                }
            });
        }


        void setFamilyCardDetails(Community community,int position) {
            txtCountry.setText(community.getCountryName());
            txtCommunity.setText(community.getName());
        }
    }

    private void startCommunityWaterSurveyActivity(String country, String community) {
        Intent i = new Intent(this.context, CommunityWaterSurveyActivity.class);
        i.putExtra("NAME_BWE", namebwe);
        i.putExtra("POSITION_BWE", positionbwe);
        i.putExtra("SURVEY_TYPE",surveyType);
        i.putExtra("COUNTRY_BWE",country);
        i.putExtra("COMMUNITY",community);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    private void startInitialSurveyActivity(String country, String community) {
        Intent i = new Intent(this.context, InitialSurveyActivity.class);
        i.putExtra("NAME_BWE", namebwe);
        i.putExtra("POSITION_BWE", positionbwe);
        i.putExtra("SURVEY_TYPE",surveyType);
        i.putExtra("COUNTRY_BWE",country);
        i.putExtra("COMMUNITY",community);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CommunityCardAdapter.CommunityCardViewHolder) holder).setFamilyCardDetails(listOfCommunities.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfCommunities.size();
    }
}
