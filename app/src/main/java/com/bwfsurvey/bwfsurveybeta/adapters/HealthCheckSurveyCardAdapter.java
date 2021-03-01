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

import com.amplifyframework.datastore.generated.model.FollowUpSurvey;
import com.amplifyframework.datastore.generated.model.HealthCheckSurvey;
import com.bwfsurvey.bwfsurveybeta.activities.update.UpdateHealthCheckSurveyActivity;
import com.bwfsurvey.bwfsurveybeta.activities.view.ViewFollowUpSurveyActivity;
import com.bwfsurvey.bwfsurveybeta.activities.view.ViewHealthCheckSurveyActivity;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HealthCheckSurveyCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<HealthCheckSurvey> listOfHealthCheckSurveys;
    private String namebwe;
    private String countrybwe;
    private String surveyType;
    private String operation;
    private String lat;
    private String lng;
    private Context context;

    public HealthCheckSurveyCardAdapter(Context context,ArrayList<HealthCheckSurvey> listOfHealthCheckSurveys, String namebwe, String countrybwe, String surveyType, String operation, String lat, String lng ) {
        this.listOfHealthCheckSurveys = listOfHealthCheckSurveys;
        this.namebwe = namebwe;
        this.countrybwe = countrybwe;
        this.surveyType = surveyType;
        this.operation = operation;
        this.lat = lat;
        this.lng = lng;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.followup_survey_card, parent, false);
        return new HealthCheckSurveyCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HealthCheckSurveyCardViewHolder) holder).setFamilyCardDetails(listOfHealthCheckSurveys.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfHealthCheckSurveys.size();
    }

    private class HealthCheckSurveyCardViewHolder extends RecyclerView.ViewHolder {
        private TextView txtFamilySurveyId;
        private TextView txtCountry;
        private TextView txtCommunity;
        private TextView txtHeadHousehold;
        private TextView txtDate;
        private String uuidHealthCheckSurvey;

        public HealthCheckSurveyCardViewHolder(View view) {
            super(view);
            txtFamilySurveyId = (TextView) itemView.findViewById(R.id.txtFamilySurveyId);
            txtCountry = (TextView) itemView.findViewById(R.id.txtCountry);
            txtCommunity = (TextView) itemView.findViewById(R.id.txtCommunity);
            txtHeadHousehold = (TextView) itemView.findViewById(R.id.txtHeadHousehold);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            CardView familyCard = (CardView) itemView.findViewById(R.id.familyCard); // creating a CardView and assigning a value.

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
                        startViewHealthCheckSurveyActivity(uuidHealthCheckSurvey);
                    }else if(operation.contentEquals("UPDATE")){
                        startUpdateHealthCheckSurveyActivity(uuidHealthCheckSurvey);
                    }

                }
            });
        }

        void setFamilyCardDetails(HealthCheckSurvey healthCheckSurvey,int position) {
            //txtFamilySurveyId.setText(Integer.toString(initialSurvey.getSurveyId()));
            txtCountry.setText(healthCheckSurvey.getCountry());
            txtCommunity.setText(healthCheckSurvey.getCommunity());
            txtHeadHousehold.setText(healthCheckSurvey.getHeadHouseholdName());

            String dateStr = healthCheckSurvey.getDate().toString();
            if(dateStr.indexOf("{")>0&&dateStr.indexOf("}")>0){
                dateStr = dateStr.substring(dateStr.indexOf("{") + 1);
                dateStr = dateStr.substring(0, dateStr.indexOf("}"));
                dateStr = dateStr.split(",")[0].split("=")[1];
                dateStr = dateStr.substring( 1, dateStr.length() - 1 );
                Log.i("Tutorial", "index dateStr: "+dateStr);

                Date date_ = null;
                try {
                    date_ = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                    String dateToShow = new SimpleDateFormat("dd/MM/yyyy").format(date_);

                    if(!dateToShow.contentEquals("01/01/1900"))
                        txtDate.setText(dateToShow);
                    else
                        txtDate.setText("");
                } catch (Exception e) {
                    txtDate.setText("");
                }

            }else{
                Log.i("Tutorial", "out index dateStr: "+dateStr);
                txtDate.setText("");
            }

            uuidHealthCheckSurvey = healthCheckSurvey.getId();
        }

    }

    private void startViewHealthCheckSurveyActivity(String uuidHealthCheckSurvey) {
        Intent i = new Intent(context, ViewHealthCheckSurveyActivity.class);
        i.putExtra("UUID", uuidHealthCheckSurvey);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    private void startUpdateHealthCheckSurveyActivity(String uuidHealthCheckSurvey) {
        Intent i = new Intent(context, UpdateHealthCheckSurveyActivity.class);
        i.putExtra("UUID", uuidHealthCheckSurvey);
        context.startActivity(i);
        ((Activity)context).finish();
    }
}
