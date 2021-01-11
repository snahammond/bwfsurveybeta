package com.bwfsurvey.bwfsurveybeta;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.InitialSurvey;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class ViewOnlyInterchangeCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private AppCompatActivity context;
    private ArrayList<ViewOnlyInterchange> viewOnlyInterchanges;

    public ViewOnlyInterchangeCardAdapter(AppCompatActivity context, ArrayList<ViewOnlyInterchange> viewOnlyInterchanges) {
        this.viewOnlyInterchanges = viewOnlyInterchanges;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewonlyinterchange_card, parent, false);
        return new ViewOnlyInterchangeCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewOnlyInterchangeCardViewHolder) holder).setQuestionTextAnsDetails(viewOnlyInterchanges.get(position),position);
    }

    @Override
    public int getItemCount() {
        return viewOnlyInterchanges.size();
    }

    private class ViewOnlyInterchangeCardViewHolder extends RecyclerView.ViewHolder {
        private TextView interchangeNumber;
        private TextView txtQuestion;
        private TextView txtAnswer;

        public ViewOnlyInterchangeCardViewHolder(View view) {
            super(view);
            interchangeNumber = itemView.findViewById(R.id.interchangeNumber);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            txtAnswer = itemView.findViewById(R.id.txtAnswer);
        }

        void setQuestionTextAnsDetails(ViewOnlyInterchange viewOnlyInterchange,int position) {
            interchangeNumber.setText(Integer.toString(viewOnlyInterchange.getInterchangeNumber()));
            txtQuestion.setText(viewOnlyInterchange.getQuestion());
            txtAnswer.setText(viewOnlyInterchange.getAnswer());
        }
    }
}
