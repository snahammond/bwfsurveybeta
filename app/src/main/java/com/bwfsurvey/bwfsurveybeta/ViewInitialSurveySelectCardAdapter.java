package com.bwfsurvey.bwfsurveybeta;

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

import com.amplifyframework.datastore.generated.model.InitialSurvey;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class ViewInitialSurveySelectCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<InitialSurvey> listOfInitialSurveys;
    private Context context;

    public ViewInitialSurveySelectCardAdapter(ViewInitialSurveySelectActivity viewInitialSurveySelectActivity, ArrayList<InitialSurvey> listOfInitialSurveys) {
        this.listOfInitialSurveys = listOfInitialSurveys;
        this.context = viewInitialSurveySelectActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.family_card, parent, false);
        return new ViewInitialSurveyCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewInitialSurveyCardViewHolder) holder).setInitialSurveySelectCardDetails(listOfInitialSurveys.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfInitialSurveys.size();
    }

    private class ViewInitialSurveyCardViewHolder extends RecyclerView.ViewHolder {
        private TextView txtFamilySurveyId;
        private TextView txtCountry;
        private TextView txtCommunity;
        private TextView txtHeadHousehold;
        private String uuidInitialSurvey;

        public ViewInitialSurveyCardViewHolder(View view) {
            super(view);
            txtFamilySurveyId = (TextView) itemView.findViewById(R.id.txtFamilySurveyId);
            txtCountry = (TextView) itemView.findViewById(R.id.txtCountry);
            txtCommunity = (TextView) itemView.findViewById(R.id.txtCommunity);
            txtHeadHousehold = (TextView) itemView.findViewById(R.id.txtHeadHousehold);
            CardView initialSurveyCard = (CardView) itemView.findViewById(R.id.familyCard); // creating a CardView and assigning a value.

            initialSurveyCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Selected family: " + txtHeadHousehold.getText());
                    Intent i = new Intent(context, ViewInitialSurveyActivity.class);
                    i.putExtra("UUID", uuidInitialSurvey);
                    context.startActivity(i);
                    ((Activity)context).finish();
                }
            });


        }

        void setInitialSurveySelectCardDetails(InitialSurvey initialSurvey,int position) {
            txtFamilySurveyId.setText(Integer.toString(initialSurvey.getSurveyId()));
            txtCountry.setText(initialSurvey.getCountry());
            txtCommunity.setText(initialSurvey.getCommunity());
            txtHeadHousehold.setText(initialSurvey.getHeadHouseholdName());
            uuidInitialSurvey = initialSurvey.getId();
        }
    }
}
