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
import com.amplifyframework.datastore.generated.model.VolunteerMonthlyTotalSummary;
import com.bwfsurvey.bwfsurveybeta.activities.select.VolunteerMonthlyTotalSummaryCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.update.UpdateVolunteerMonthlySummaryActivity;
import com.bwfsurvey.bwfsurveybeta.activities.view.ViewVolunteerMonthlySummaryActivity;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VolunteerMonthlyTotalSummaryCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<VolunteerMonthlyTotalSummary> listOfVolunteerMonthlyTotalSummarys;
    private String namebwe;
    private String countrybwe;
    private String surveyType;
    private String operation;
    private String lat;
    private String lng;
    private Context context;

    public VolunteerMonthlyTotalSummaryCardAdapter(VolunteerMonthlyTotalSummaryCardSelectActivity context, ArrayList<VolunteerMonthlyTotalSummary> listOfVolunteerMonthlyTotalSummarys, String namebwe, String countrybwe, String surveyType, String operation, String lat, String lng) {
        this.listOfVolunteerMonthlyTotalSummarys = listOfVolunteerMonthlyTotalSummarys;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.monthly_total_summary_card_vol, parent, false);
        return new VolunteerMonthlyTotalSummaryCardAdapter.VolunteerMonthlyTotalSummaryCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((VolunteerMonthlyTotalSummaryCardAdapter.VolunteerMonthlyTotalSummaryCardViewHolder) holder).setVolunteerMonthlyTotalSummaryDetails(listOfVolunteerMonthlyTotalSummarys.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfVolunteerMonthlyTotalSummarys.size();
    }

    private class VolunteerMonthlyTotalSummaryCardViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNoOfWaterSamplesTaken;
        private TextView txtDate;
        private String uuidVolunteerMonthlyTotalSummary;

        public VolunteerMonthlyTotalSummaryCardViewHolder(View view) {
            super(view);
            txtNoOfWaterSamplesTaken = (TextView) itemView.findViewById(R.id.txtNoOfWaterSamplesTaken);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            CardView monthlyTotalSummaryCard = (CardView) itemView.findViewById(R.id.monthlyTotalSummaryCard); // creating a CardView and assigning a value.

            monthlyTotalSummaryCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        void setVolunteerMonthlyTotalSummaryDetails(VolunteerMonthlyTotalSummary volunteerMonthlyTotalSummary,int position) {
            txtNoOfWaterSamplesTaken.setText(volunteerMonthlyTotalSummary.getNoWaterSampleTaken().toString());
            String dateStr = volunteerMonthlyTotalSummary.getDate().toString();
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

            uuidVolunteerMonthlyTotalSummary = volunteerMonthlyTotalSummary.getId();
        }
    }


}
