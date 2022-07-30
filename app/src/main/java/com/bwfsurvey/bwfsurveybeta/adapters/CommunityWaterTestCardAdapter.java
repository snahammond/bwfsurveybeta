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

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.CommunityWaterTest;
import com.amplifyframework.datastore.generated.model.FollowUpSurvey;
import com.bwfsurvey.bwfsurveybeta.activities.select.CommunityWaterTestCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.update.UpdateCommunityWaterTestActivity;
import com.bwfsurvey.bwfsurveybeta.activities.view.ViewCommunityWaterTestActivity;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class CommunityWaterTestCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<CommunityWaterTest> listOfCommunityWaterTest;
    private String namebwe;
    private String countrybwe;
    private String surveyType;
    private String operation;
    private String lat;
    private String lng;
    private Context context;

    public CommunityWaterTestCardAdapter(CommunityWaterTestCardSelectActivity updateCommunityWaterCardSelectActivity, ArrayList<CommunityWaterTest> listOfCommunityWaterTest, String namebwe, String countrybwe, String surveyType, String operation, String lat, String lng) {
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
        return new CommunityWaterTestCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CommunityWaterTestCardViewHolder) holder).setCommunityWaterCardDetails(listOfCommunityWaterTest.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfCommunityWaterTest.size();
    }

    private class CommunityWaterTestCardViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCountry;
        private TextView txtCommunity;
        private TextView txtCommunityWaterLoc;
        private TextView txtCommunityWaterTestOnlineStatus;
        private String uuidCommunityWaterTest;

        public CommunityWaterTestCardViewHolder(View view) {
            super(view);
            txtCountry = (TextView) view.findViewById(R.id.txtCountry);
            txtCommunity = (TextView) view.findViewById(R.id.txtCommunity);
            txtCommunityWaterLoc = (TextView) view.findViewById(R.id.txtCommunityWaterLoc);
            txtCommunityWaterTestOnlineStatus = (TextView) view.findViewById(R.id.txtCommunityWaterTestOnlineStatus);
            CardView communityWaterCard = (CardView) view.findViewById(R.id.volHouseholdCard); // creating a CardView and assigning a value.

            communityWaterCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Selected family: " + txtCommunityWaterLoc.getText());

                    if(operation.contentEquals("CREATE")){
                        //startCommunityWaterSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString(), txtCommunityWaterLoc.getText().toString());
                    }else if(operation.contentEquals("VIEW")){
                        startViewCommunityWaterTestActivity(uuidCommunityWaterTest);
                    }else if(operation.contentEquals("UPDATE")){
                        startUpdateCommunityWaterTestActivity(uuidCommunityWaterTest);
                    }


                }
            });
        }

        void setCommunityWaterCardDetails(CommunityWaterTest communityWaterTest, int position) {
            //txtFamilySurveyId.setText(Integer.toString(initialSurvey.getSurveyId()));
            txtCountry.setText(communityWaterTest.getCountry());
            txtCommunity.setText(communityWaterTest.getCommunity());
            txtCommunityWaterLoc.setText(communityWaterTest.getCommunityWaterLocation());
            uuidCommunityWaterTest = communityWaterTest.getId();
            txtCommunityWaterTestOnlineStatus.setVisibility(View.INVISIBLE);
            Amplify.API.query(
                    ModelQuery.get(CommunityWaterTest.class, uuidCommunityWaterTest),
                    response -> {
                        if(response.getData()!=null && (response.getData()).getId().contentEquals(uuidCommunityWaterTest)){
                            Activity activity = (Activity) context;
                            activity.runOnUiThread(new Runnable() {
                                public void run() {
                                    txtCommunityWaterTestOnlineStatus.setVisibility(View.VISIBLE);
                                }
                            });
                            Log.i("bwfsurveybeta", (response.getData()).getCommunityWaterLocation() +" is on the cloud");
                        }
                    },
                    error -> {
                        Log.e("bwfsurveybeta", error.toString(), error);
                    }
            );
        }
    }

    private void startUpdateCommunityWaterTestActivity(String uuidCommunityWaterTest){
        Intent i = new Intent(context, UpdateCommunityWaterTestActivity.class);
        i.putExtra("UUID", uuidCommunityWaterTest);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    private void startViewCommunityWaterTestActivity(String uuidCommunityWaterTest){
        Intent i = new Intent(context, ViewCommunityWaterTestActivity.class);
        i.putExtra("UUID", uuidCommunityWaterTest);
        context.startActivity(i);
        ((Activity)context).finish();
    }
}
