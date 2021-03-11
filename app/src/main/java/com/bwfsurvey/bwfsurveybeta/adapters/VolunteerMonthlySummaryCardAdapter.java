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

import com.amplifyframework.datastore.generated.model.VolunteerMonthlySummary;
import com.bwfsurvey.bwfsurveybeta.activities.update.UpdateVolunteerMonthlySummaryActivity;
import com.bwfsurvey.bwfsurveybeta.activities.view.ViewVolunteerMonthlySummaryActivity;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VolunteerMonthlySummaryCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<VolunteerMonthlySummary> listOfVolunteerMonthlySummarys;
    private String namebwe;
    private String countrybwe;
    private String surveyType;
    private String operation;
    private String lat;
    private String lng;
    private Context context;

    public VolunteerMonthlySummaryCardAdapter(Context context,ArrayList<VolunteerMonthlySummary> listOfVolunteerMonthlySummarys, String namebwe, String countrybwe, String surveyType, String operation, String lat, String lng ) {
        this.listOfVolunteerMonthlySummarys = listOfVolunteerMonthlySummarys;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.monthly_summary_card, parent, false);
        return new VolunteerMonthlySummaryCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((VolunteerMonthlySummaryCardViewHolder) holder).setVolunteerMonthlySummaryDetails(listOfVolunteerMonthlySummarys.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfVolunteerMonthlySummarys.size();
    }

    private class VolunteerMonthlySummaryCardViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNoOfPersonsTaught;
        private TextView txtNoOfSurveysCompleted;
        private TextView txtDate;
        private String uuidVolunteerMonthlySummary;

        public VolunteerMonthlySummaryCardViewHolder(View view) {
            super(view);
            txtNoOfPersonsTaught = (TextView) itemView.findViewById(R.id.txtNoOfPersonsTaught);
            txtNoOfSurveysCompleted = (TextView) itemView.findViewById(R.id.txtNoOfSurveysCompleted);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            CardView monthlySummaryCard = (CardView) itemView.findViewById(R.id.monthlySummaryCard); // creating a CardView and assigning a value.

            monthlySummaryCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Selected txtDate: " + txtDate.getText());
                    Log.i("Tutorials", "operation: " + operation);
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
                        startViewVolunteerMonthlySummaryActivity(uuidVolunteerMonthlySummary);
                    }else if(operation.contentEquals("UPDATE")){
                        startUpdateVolunteerMonthlySummaryActivity(uuidVolunteerMonthlySummary);
                    }

                }
            });
        }

        void setVolunteerMonthlySummaryDetails(VolunteerMonthlySummary volunteerMonthlySummary,int position) {
            txtNoOfPersonsTaught.setText(volunteerMonthlySummary.getNoPersonsTaught().toString());
            txtNoOfSurveysCompleted.setText(volunteerMonthlySummary.getNoSurveysCompleted().toString());
            String dateStr = volunteerMonthlySummary.getDate().toString();
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

            uuidVolunteerMonthlySummary = volunteerMonthlySummary.getId();
        }
    }

    private void startViewVolunteerMonthlySummaryActivity(String uuidVolunteerMonthlySummary) {
        Intent i = new Intent(context, ViewVolunteerMonthlySummaryActivity.class);
        i.putExtra("UUID", uuidVolunteerMonthlySummary);
        context.startActivity(i);
        ((Activity)context).finish();
    }


    private void startUpdateVolunteerMonthlySummaryActivity(String uuidVolunteerMonthlySummary) {
        Intent i = new Intent(context, UpdateVolunteerMonthlySummaryActivity.class);
        i.putExtra("UUID", uuidVolunteerMonthlySummary);
        context.startActivity(i);
        ((Activity)context).finish();
    }
}
