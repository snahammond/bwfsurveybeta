package com.bwfsurvey.bwfsurveybeta.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bwfsurvey.bwfsurveybeta.activities.InitialSurveyActivity;
import com.bwfsurvey.bwfsurveybeta.types.Question;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {

    private ArrayList<Question> questions;
    private Context context;


    public CardAdapter(InitialSurveyActivity mainActivity, ArrayList<Question> questions) {
        this.context = mainActivity;
        this.questions = questions;
    }

    @NonNull
    @Override
    public CardAdapter.CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_question, parent, false);

        return new CardHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        Question planet = questions.get(position);
        holder.setDetails(planet);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class CardHolder extends RecyclerView.ViewHolder {
        private TextView txtQuestion;

        CardHolder(View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
        }

        void setDetails(Question question) {
            txtQuestion.setText(question.getQuestionText());

        }
    }
}
