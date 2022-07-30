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
import com.amplifyframework.datastore.generated.model.FollowUpSurvey;
import com.bwfsurvey.bwfsurveybeta.activities.select.FollowUpSurveyCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.update.UpdateFollowUpSurveyActivity;
import com.bwfsurvey.bwfsurveybeta.activities.view.ViewFollowUpSurveyActivity;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FollowUpSurveyCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<FollowUpSurvey> listOfFollowUpSurveys;
    private String namebwe;
    private String countrybwe;
    private String surveyType;
    private String operation;
    private String lat;
    private String lng;
    private Context context;

    public FollowUpSurveyCardAdapter(FollowUpSurveyCardSelectActivity updateFollowUpSurveyCardSelectActivity, ArrayList<FollowUpSurvey> listOfFollowUpSurveys, String namebwe, String countrybwe, String surveyType, String operation, String lat, String lng) {
        this.listOfFollowUpSurveys = listOfFollowUpSurveys;
        this.context = updateFollowUpSurveyCardSelectActivity;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.followup_survey_card, parent, false);
        return new FollowUpSurveyCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((FollowUpSurveyCardViewHolder) holder).setFamilyCardDetails(listOfFollowUpSurveys.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfFollowUpSurveys.size();
    }

    private class FollowUpSurveyCardViewHolder extends RecyclerView.ViewHolder {

        private TextView txtFamilySurveyId;
        private TextView txtCountry;
        private TextView txtCommunity;
        private TextView txtHeadHousehold;
        private TextView txtDate;
        private TextView txtFollowUpSurveyOnlineStatus;
        private String uuidFollowUpSurvey;

        public FollowUpSurveyCardViewHolder(View view) {
            super(view);
            txtFamilySurveyId = (TextView) view.findViewById(R.id.txtFamilySurveyId);
            txtCountry = (TextView) view.findViewById(R.id.txtCountry);
            txtCommunity = (TextView) view.findViewById(R.id.txtCommunity);
            txtHeadHousehold = (TextView) view.findViewById(R.id.txtHeadHousehold);
            txtDate = (TextView) view.findViewById(R.id.txtDate);
            txtFollowUpSurveyOnlineStatus = (TextView) view.findViewById(R.id.txtFollowUpSurveyOnlineStatus);
            CardView familyCard = (CardView) view.findViewById(R.id.familyCard); // creating a CardView and assigning a value.

            familyCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Selected family: " + txtHeadHousehold.getText());
                    if(operation.contentEquals("CREATE")){
                        /*
                        if (surveyType.contentEquals("FOLLOWUPSURVEY")) {
                            startFollowUpSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString(), txtHeadHousehold.getText().toString(), txtFamilySurveyId.getText().toString());
                        }else if(surveyType.contentEquals("HEALTHCHECKSURVEY")){
                            startHealthCheckSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString(), txtHeadHousehold.getText().toString(), txtFamilySurveyId.getText().toString());
                        }else if(surveyType.contentEquals("WATERSURVEYHOUSEHOLD")){
                            startHouseholdWaterSurveyActivity(txtCountry.getText().toString(), txtCommunity.getText().toString(), txtHeadHousehold.getText().toString(), txtFamilySurveyId.getText().toString());
                        }

                         */
                    }else if(operation.contentEquals("VIEW")){
                        startViewFollowUpSurveyActivity(uuidFollowUpSurvey);
                    }else if(operation.contentEquals("UPDATE")){
                        startUpdateFollowUpSurveyActivity(uuidFollowUpSurvey);
                    }

                }
            });
        }

        void setFamilyCardDetails(FollowUpSurvey followUpSurvey,int position) {
            //txtFamilySurveyId.setText(Integer.toString(initialSurvey.getSurveyId()));
            txtCountry.setText(followUpSurvey.getCountry());
            txtCommunity.setText(followUpSurvey.getCommunity());
            txtHeadHousehold.setText(followUpSurvey.getHeadHouseholdName());
            txtFollowUpSurveyOnlineStatus.setVisibility(View.INVISIBLE);

            String dateStr = followUpSurvey.getDate().toString();
            if(dateStr.indexOf("{")>0&&dateStr.indexOf("}")>0){
                dateStr = dateStr.substring(dateStr.indexOf("{") + 1);
                dateStr = dateStr.substring(0, dateStr.indexOf("}"));
                dateStr = dateStr.split(",")[0].split("=")[1];
                dateStr = dateStr.substring( 1, dateStr.length() - 1 );
                Log.i("Tutorial", "index dateStr: "+dateStr);

                Date date_ = null;
                try {
                    date_ = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                    if(date_!=null){
                        String dateToShow = new SimpleDateFormat("dd/MM/yyyy").format(date_);

                        if(!dateToShow.contentEquals("01/01/1900"))
                            txtDate.setText(dateToShow);
                        else
                            txtDate.setText("");
                    }else{
                        txtDate.setText("");
                    }

                } catch (Exception e) {
                    txtDate.setText("");
                }

            }else{
                Log.i("Tutorial", "out index dateStr: "+dateStr);
                txtDate.setText("");
            }

            uuidFollowUpSurvey = followUpSurvey.getId();
            Amplify.API.query(
                ModelQuery.get(FollowUpSurvey.class, uuidFollowUpSurvey),
                response -> {
                    if(response.getData()!=null && (response.getData()).getId().contentEquals(uuidFollowUpSurvey)){
                        Activity activity = (Activity) context;
                        activity.runOnUiThread(new Runnable() {
                            public void run() {
                                txtFollowUpSurveyOnlineStatus.setVisibility(View.VISIBLE);
                            }
                        });
                        Log.i("bwfsurveybeta", (response.getData()).getHeadHouseholdName() +" is on the cloud");
                    }
                },
                error -> {
                    Log.e("bwfsurveybeta", error.toString(), error);
                }
            );
        }
    }

    private void startUpdateFollowUpSurveyActivity(String uuidFollowUpSurvey){
        Intent i = new Intent(context, UpdateFollowUpSurveyActivity.class);
        i.putExtra("UUID", uuidFollowUpSurvey);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    private void startViewFollowUpSurveyActivity(String uuidInitialSurvey){
        Intent i = new Intent(context, ViewFollowUpSurveyActivity.class);
        i.putExtra("UUID", uuidInitialSurvey);
        context.startActivity(i);
        ((Activity)context).finish();
    }
}
