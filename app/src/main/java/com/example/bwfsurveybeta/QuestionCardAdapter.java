package com.example.bwfsurveybeta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.datastore.generated.model.Sex;

import java.util.ArrayList;
import java.util.List;

public class QuestionCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Question> questions;
    private ArrayList<Question> _retQuestionsWithAns;
    private Context context;
    private static int ANSTYPE_TEXT = 1;
    private static int ANSTYPE_ENUM = 2;
    private static int ANSTYPE_NUMBER = 3;

    public QuestionCardAdapter(InitialSurveyActivity mainActivity, ArrayList<Question> questions) {
        this.questions = questions;
        this._retQuestionsWithAns = questions;
        this.context = mainActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == ANSTYPE_TEXT) { // for string answer card
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card_string, parent, false);
            return new QuestionTextAnsViewHolder(view);
        } else if (viewType == ANSTYPE_ENUM){ // for enum answer card
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card_enum, parent, false);
            return new QuestionEnumAnsViewHolder(view);
        }else if (viewType == ANSTYPE_NUMBER){ // for number answer card
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card_number, parent, false);
            return new QuestionNumberAnsViewHolder(view);
        }else{
            return null;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ANSTYPE_TEXT) {
            ((QuestionTextAnsViewHolder) holder).setQuestionTextAnsDetails(questions.get(position),position);
        } else if (getItemViewType(position) == ANSTYPE_ENUM){
            ((QuestionEnumAnsViewHolder) holder).setQuestionEnumAnsDetails(questions.get(position),position);
        }else if(getItemViewType(position) == ANSTYPE_NUMBER){
            ((QuestionNumberAnsViewHolder) holder).setQuestionNumberAnsDetails(questions.get(position),position);
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (questions.get(position).getAnsType()==AnsType.TEXTVALUE) {
            return ANSTYPE_TEXT;
        } else if(questions.get(position).getAnsType()==AnsType.ENUMVALUE){
            return ANSTYPE_ENUM;
        }else if(questions.get(position).getAnsType()==AnsType.NUMBERVALUE){
            return ANSTYPE_NUMBER;
        }else{
            return 0;
        }

    }

    class QuestionTextAnsViewHolder extends RecyclerView.ViewHolder {

        private TextView txtQuestion;
        private TextView editAnswer;

        QuestionTextAnsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            editAnswer = itemView.findViewById(R.id.editAnswer);
        }

        void setQuestionTextAnsDetails(Question question,int position) {
            txtQuestion.setText(question.getQuestionText());
            editAnswer.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {}

                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    _retQuestionsWithAns.get(position).setAns((String)s.toString()); ;
                }
            });
        }
    }

    class QuestionNumberAnsViewHolder extends RecyclerView.ViewHolder {

        private TextView txtQuestion;
        private TextView editAnswer;

        QuestionNumberAnsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            editAnswer = itemView.findViewById(R.id.editAnswer);
        }

        void setQuestionNumberAnsDetails(Question question,int position) {
            txtQuestion.setText(question.getQuestionText());
            editAnswer.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {}

                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int myNum = 0;
                    try {
                        myNum = Integer.parseInt(s.toString());
                    } catch(NumberFormatException nfe) {
                        System.out.println("Could not parse " + nfe);
                    }
                    _retQuestionsWithAns.get(position).setAns((Integer)myNum);
                }
            });
        }
    }

    class QuestionEnumAnsViewHolder extends RecyclerView.ViewHolder{

        private TextView txtQuestion;
        private RadioGroup radioEnum;
        private RadioButton radio_1;
        private RadioButton radio_2;
        private RadioButton radio_3;
        private RadioButton radio_4;
        private RadioButton radio_5;
        private RadioButton radio_6;
        private RadioButton radio_7;
        private RadioButton radio_8;
        private RadioButton radio_9;
        private RadioButton radio_10;



        QuestionEnumAnsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            radioEnum = (RadioGroup) itemView.findViewById(R.id.radioEnum);
            radio_1 = (RadioButton)itemView.findViewById(R.id.radio_1);
            radio_2 = (RadioButton)itemView.findViewById(R.id.radio_2);
            radio_3 = (RadioButton)itemView.findViewById(R.id.radio_3);
            radio_4 = (RadioButton)itemView.findViewById(R.id.radio_4);
            radio_5 = (RadioButton)itemView.findViewById(R.id.radio_5);
            radio_6 = (RadioButton)itemView.findViewById(R.id.radio_6);
            radio_7 = (RadioButton)itemView.findViewById(R.id.radio_7);
            radio_8 = (RadioButton)itemView.findViewById(R.id.radio_8);
            radio_9 = (RadioButton)itemView.findViewById(R.id.radio_9);
            radio_10 = (RadioButton)itemView.findViewById(R.id.radio_10);
        }

        void setQuestionEnumAnsDetails(Question question, int position) {
            txtQuestion.setText(question.getQuestionText());
            Log.i("Tutorial", "question "+ question.getQuestionText());
            addRadioButtons(question.getPossibleAnss(),position);
        }

        public void addRadioButtons(ArrayList<PossibleAns> possibleAnss,int position) {

            if(possibleAnss.size()>0){
                radio_1.setVisibility(View.VISIBLE);
                radio_1.setText( possibleAnss.get(0).getValue());
                radio_1.setHint(possibleAnss.get(0).getName());
                radio_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedRadioEnumStrValue = ((RadioButton)v).getHint().toString();
                        _retQuestionsWithAns.get(position).setAns(selectedRadioEnumStrValue);
                    }
                });
            }

            if(possibleAnss.size()>1){
                radio_2.setVisibility(View.VISIBLE);
                radio_2.setText( possibleAnss.get(1).getValue());
                radio_2.setHint(possibleAnss.get(1).getName());
                radio_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedRadioEnumStrValue = ((RadioButton)v).getHint().toString();
                        _retQuestionsWithAns.get(position).setAns(selectedRadioEnumStrValue);
                    }
                });
            }

            if(possibleAnss.size()>2){
                radio_3.setVisibility(View.VISIBLE);
                radio_3.setText( possibleAnss.get(2).getValue());
                radio_3.setHint(possibleAnss.get(2).getName());
                radio_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedRadioEnumStrValue = ((RadioButton)v).getHint().toString();
                        _retQuestionsWithAns.get(position).setAns(selectedRadioEnumStrValue);
                    }
                });
            }
            if(possibleAnss.size()>3){
                radio_4.setVisibility(View.VISIBLE);
                radio_4.setText( possibleAnss.get(3).getValue());
                radio_4.setHint(possibleAnss.get(3).getName());
                radio_4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedRadioEnumStrValue = ((RadioButton)v).getHint().toString();
                        _retQuestionsWithAns.get(position).setAns(selectedRadioEnumStrValue);
                    }
                });
            }
            if(possibleAnss.size()>4){
                radio_5.setVisibility(View.VISIBLE);
                radio_5.setText( possibleAnss.get(4).getValue());
                radio_5.setHint(possibleAnss.get(4).getName());
                radio_5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedRadioEnumStrValue = ((RadioButton)v).getHint().toString();
                        _retQuestionsWithAns.get(position).setAns(selectedRadioEnumStrValue);
                    }
                });
            }

            if(possibleAnss.size()>5){
                radio_6.setVisibility(View.VISIBLE);
                radio_6.setText( possibleAnss.get(5).getValue());
                radio_6.setHint(possibleAnss.get(5).getName());
                radio_6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedRadioEnumStrValue = ((RadioButton)v).getHint().toString();
                        _retQuestionsWithAns.get(position).setAns(selectedRadioEnumStrValue);
                    }
                });
            }

            if(possibleAnss.size()>6){
                radio_7.setVisibility(View.VISIBLE);
                radio_7.setText( possibleAnss.get(6).getValue());
                radio_7.setHint(possibleAnss.get(6).getName());
                radio_7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedRadioEnumStrValue = ((RadioButton)v).getHint().toString();
                        _retQuestionsWithAns.get(position).setAns(selectedRadioEnumStrValue);
                    }
                });
            }

            if(possibleAnss.size()>7){
                radio_8.setVisibility(View.VISIBLE);
                radio_8.setText( possibleAnss.get(7).getValue());
                radio_8.setHint(possibleAnss.get(7).getName());
                radio_8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedRadioEnumStrValue = ((RadioButton)v).getHint().toString();
                        _retQuestionsWithAns.get(position).setAns(selectedRadioEnumStrValue);
                    }
                });
            }

            if(possibleAnss.size()>8){
                radio_9.setVisibility(View.VISIBLE);
                radio_9.setText( possibleAnss.get(8).getValue());
                radio_9.setHint(possibleAnss.get(8).getName());
                radio_9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedRadioEnumStrValue = ((RadioButton)v).getHint().toString();
                        _retQuestionsWithAns.get(position).setAns(selectedRadioEnumStrValue);
                    }
                });
            }

            if(possibleAnss.size()>9){
                radio_10.setVisibility(View.VISIBLE);
                radio_10.setText( possibleAnss.get(9).getValue());
                radio_10.setHint(possibleAnss.get(9).getName());
                radio_10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedRadioEnumStrValue = ((RadioButton)v).getHint().toString();
                        _retQuestionsWithAns.get(position).setAns(selectedRadioEnumStrValue);
                    }
                });
            }
        }


    }

    public ArrayList<Question> retrieveData()
    {
        return _retQuestionsWithAns;
    }
}
