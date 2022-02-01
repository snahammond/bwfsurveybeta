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

import com.amplifyframework.datastore.generated.model.SWEMonthlySummary;
import com.amplifyframework.datastore.generated.model.SWEMonthlyTotalSummary;
import com.bwfsurvey.bwfsurveybeta.activities.select.SWEMonthlyTotalSummaryCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.update.UpdateSWEMonthlySummaryActivity;
import com.bwfsurvey.bwfsurveybeta.activities.view.ViewSWEMonthlySummaryActivity;
import com.bwfsurvey.bwfsurveybeta.activities.view.ViewSWEMonthlyTotalSummaryActivity;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SWEMonthlyTotalSummaryCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<SWEMonthlyTotalSummary> listOfSWEMonthlyTotalSummarys;
    private String namebwe;
    private String countrybwe;
    private String surveyType;
    private String operation;
    private String lat;
    private String lng;
    private Context context;

    public SWEMonthlyTotalSummaryCardAdapter(Context context,ArrayList<SWEMonthlyTotalSummary> listOfSWEMonthlyTotalSummarys, String namebwe, String countrybwe, String surveyType, String operation, String lat, String lng ) {
        this.listOfSWEMonthlyTotalSummarys = listOfSWEMonthlyTotalSummarys;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.monthly_total_summary_card, parent, false);
        return new SWEMonthlyTotalSummaryCardAdapter.SWEMonthlyTotalSummaryCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SWEMonthlyTotalSummaryCardAdapter.SWEMonthlyTotalSummaryCardViewHolder) holder).setMonthlySummaryDetails(listOfSWEMonthlyTotalSummarys.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfSWEMonthlyTotalSummarys.size();
    }

    private class SWEMonthlyTotalSummaryCardViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNoOfWaterSamplesTaken;
        private TextView txtNoOfSurveysCompleted;
        private TextView txtDate;
        private String uuidSWEMonthlyTotalSummary;

        public SWEMonthlyTotalSummaryCardViewHolder(View view) {
            super(view);
            txtNoOfWaterSamplesTaken = (TextView) itemView.findViewById(R.id.txtNoOfWaterSamplesTaken);
            txtNoOfSurveysCompleted = (TextView) itemView.findViewById(R.id.txtNoOfSurveysCompleted);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            CardView monthlyTotalSummaryCard = (CardView) itemView.findViewById(R.id.monthlyTotalSummaryCard); // creating a CardView and assigning a value.

            monthlyTotalSummaryCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Selected txtDate: " + txtDate.getText());
                    if(operation.contentEquals("CREATE")){

                    }else if(operation.contentEquals("VIEW")){
                        startViewSWEMonthlyTotalSummaryActivity(uuidSWEMonthlyTotalSummary);
                    }else if(operation.contentEquals("UPDATE")){
                        //startUpdateSWEMonthlyTotalSummaryActivity(uuidSWEMonthlyTotalSummary);
                    }

                }
            });
        }

        void setMonthlySummaryDetails(SWEMonthlyTotalSummary sweMonthlyTotalSummary,int position) {
            txtNoOfWaterSamplesTaken.setText(sweMonthlyTotalSummary.getNoWaterSampleTaken().toString());
            txtNoOfSurveysCompleted.setText(sweMonthlyTotalSummary.getNoSurveysCompleted().toString());
            String dateStr = sweMonthlyTotalSummary.getDate().toString();
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

            uuidSWEMonthlyTotalSummary = sweMonthlyTotalSummary.getId();
        }
    }

    private void startViewSWEMonthlyTotalSummaryActivity(String uuidSWEMonthlyTotalSummary) {
        Intent i = new Intent(context, ViewSWEMonthlyTotalSummaryActivity.class);
        i.putExtra("UUID", uuidSWEMonthlyTotalSummary);
        context.startActivity(i);
        ((Activity)context).finish();
    }
    /*
    private void startUpdateSWEMonthlyTotalSummaryActivity(String uuidSWEMonthlyTotalSummary) {
        Intent i = new Intent(context, UpdateSWEMonthlySummaryActivity.class);
        i.putExtra("UUID", uuidSWEMonthlyTotalSummary);
        context.startActivity(i);
        ((Activity)context).finish();
    }*/
}
