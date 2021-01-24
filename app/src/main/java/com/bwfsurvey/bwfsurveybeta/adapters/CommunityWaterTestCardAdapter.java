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

import com.amplifyframework.datastore.generated.model.CommunityWater;
import com.amplifyframework.datastore.generated.model.CommunityWaterTest;
import com.amplifyframework.datastore.generated.model.FollowUpSurvey;
import com.bwfsurvey.bwfsurveybeta.activities.UpdateCommunityWaterCardSelectActivity;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class UpdateCommunityWaterTestCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<CommunityWaterTest> listOfCommunityWaterTest;
    private String namebwe;
    private String countrybwe;
    private String surveyType;
    private String operation;
    private String lat;
    private String lng;
    private Context context;

    public UpdateCommunityWaterTestCardAdapter(UpdateCommunityWaterCardSelectActivity updateCommunityWaterCardSelectActivity, ArrayList<CommunityWaterTest> listOfCommunityWaterTest, String namebwe, String countrybwe, String surveyType, String operation, String lat, String lng) {
        this.listOfCommunityWaterTest = listOfCommunityWaterTest;
        this.context = updateCommunityWaterCardSelectActivity;
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
        return new UpdateCommunityWaterTestCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((UpdateCommunityWaterTestCardAdapter.UpdateCommunityWaterTestCardViewHolder) holder).setCommunityWaterCardDetails(listOfCommunityWaterTest.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfCommunityWaterTest.size();
    }

    private class UpdateCommunityWaterTestCardViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCountry;
        private TextView txtCommunity;
        private TextView txtCommunityWaterLoc;

        public UpdateCommunityWaterTestCardViewHolder(View view) {
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
                        //startCommunityWaterSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString(), txtCommunityWaterLoc.getText().toString());
                    }else if(operation.contentEquals("VIEW")){
                        //startViewInitialSurveyActivity(uuidInitialSurvey);
                    }else if(operation.contentEquals("UPDATE")){
                        //startUpdateInitialSurveyActivity(uuidInitialSurvey);
                    }


                }
            });
        }

        void setCommunityWaterCardDetails(CommunityWaterTest communityWaterTest, int position) {
            //txtFamilySurveyId.setText(Integer.toString(initialSurvey.getSurveyId()));
            txtCountry.setText(communityWaterTest.getCountry());
            txtCommunity.setText(communityWaterTest.getCommunity());
            txtCommunityWaterLoc.setText(communityWaterTest.getCommunityWaterLocation());
        }
    }
}
