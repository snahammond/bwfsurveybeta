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
import com.amplifyframework.datastore.generated.model.SWEMonthlySchoolSummary;
import com.bwfsurvey.bwfsurveybeta.activities.view.ViewSWEMonthlySchoolSummaryActivity;
import com.bwfsurvey.bwfsurveybeta.activities.view.ViewSWEMonthlyTotalSummaryActivity;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SWEMonthlySchoolSummaryCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<SWEMonthlySchoolSummary> listOfSWEMonthlySchoolSummarys;
    private String namebwe;
    private String countrybwe;
    private String surveyType;
    private String operation;
    private String lat;
    private String lng;
    private Context context;

    public SWEMonthlySchoolSummaryCardAdapter(Context context, ArrayList<SWEMonthlySchoolSummary> listOfSWEMonthlySchoolSummarys, String namebwe, String countrybwe, String surveyType, String operation, String lat, String lng ) {
        this.listOfSWEMonthlySchoolSummarys = listOfSWEMonthlySchoolSummarys;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.monthly_school_summary_card, parent, false);
        return new SWEMonthlySchoolSummaryCardAdapter.SWEMonthlySchoolSummaryCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SWEMonthlySchoolSummaryCardAdapter.SWEMonthlySchoolSummaryCardViewHolder) holder).setMonthlySummaryDetails(listOfSWEMonthlySchoolSummarys.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfSWEMonthlySchoolSummarys.size();
    }

    private class SWEMonthlySchoolSummaryCardViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNoTabletUsedAtDrinkingStation1;
        private TextView txtNoStudentsTaughtSafeWaterPrinciples;
        private TextView txtDate;
        private TextView txtMonthlySchoolSummaryOnlineStatus;
        private String uuidSWEMonthlySchoolSummary;

        public SWEMonthlySchoolSummaryCardViewHolder(View view) {
            super(view);
            txtNoTabletUsedAtDrinkingStation1 = (TextView) itemView.findViewById(R.id.txtNoTabletUsedAtDrinkingStation1);
            txtNoStudentsTaughtSafeWaterPrinciples = (TextView) itemView.findViewById(R.id.txtNoStudentsTaughtSafeWaterPrinciples);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            txtMonthlySchoolSummaryOnlineStatus = (TextView) itemView.findViewById(R.id.txtMonthlySchoolSummaryOnlineStatus);
            CardView monthlySchoolSummaryCard = (CardView) itemView.findViewById(R.id.monthlySchoolSummaryCard); // creating a CardView and assigning a value.

            monthlySchoolSummaryCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Selected txtDate: " + txtDate.getText());
                    if(operation.contentEquals("CREATE")){

                    }else if(operation.contentEquals("VIEW")){
                        startViewSWEMonthlySchoolSummaryActivity(uuidSWEMonthlySchoolSummary);
                    }else if(operation.contentEquals("UPDATE")){
                        //startUpdateSWEMonthlyTotalSummaryActivity(uuidSWEMonthlyTotalSummary);
                    }

                }
            });
        }

        void setMonthlySummaryDetails(SWEMonthlySchoolSummary sweMonthlySchoolSummary, int position) {
            txtNoTabletUsedAtDrinkingStation1.setText(sweMonthlySchoolSummary.getNoTabletUsedAtDrinkingStation1().toString());
            txtNoStudentsTaughtSafeWaterPrinciples.setText(sweMonthlySchoolSummary.getNoStudentsTaughtSafeWaterPrinciples().toString());
            String dateStr = sweMonthlySchoolSummary.getDate().toString();
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

            txtMonthlySchoolSummaryOnlineStatus.setVisibility(View.INVISIBLE);
            uuidSWEMonthlySchoolSummary = sweMonthlySchoolSummary.getId();

            Amplify.API.query(
                    ModelQuery.get(SWEMonthlySchoolSummary.class, uuidSWEMonthlySchoolSummary),
                    response -> {
                        if(response.getData()!=null && (response.getData()).getId().contentEquals(uuidSWEMonthlySchoolSummary)){
                            Activity activity = (Activity) context;
                            activity.runOnUiThread(new Runnable() {
                                public void run() {
                                    txtMonthlySchoolSummaryOnlineStatus.setVisibility(View.VISIBLE);
                                }
                            });
                            Log.i("bwfsurveybeta", (response.getData()).getNamebwe() +"meeting is on the cloud");
                        }
                    },
                    error -> {
                        Log.e("bwfsurveybeta", error.toString(), error);
                    }
            );
        }
    }

    private void startViewSWEMonthlySchoolSummaryActivity(String uuidSWEMonthlySchoolSummary) {
        Intent i = new Intent(context, ViewSWEMonthlySchoolSummaryActivity.class);
        i.putExtra("UUID", uuidSWEMonthlySchoolSummary);
        context.startActivity(i);
        ((Activity)context).finish();
    }

}
