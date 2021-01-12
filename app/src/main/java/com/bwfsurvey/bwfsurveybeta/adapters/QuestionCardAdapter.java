package com.bwfsurvey.bwfsurveybeta.adapters;

import android.content.Context;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.AnswerType;

import java.util.ArrayList;
import java.util.List;

public class QuestionCardAdapter /*extends RecyclerView.Adapter<RecyclerView.ViewHolder>*/{
    /*
    private ArrayList<Question> questions;
    private ArrayList<Question> _retQuestionsWithAns;
    private Context context;
    private static int ANSTYPE_TEXT = 1;
    private static int ANSTYPE_ENUM = 2;
    private static int ANSTYPE_NUMBER = 3;
    private static int ANSTYPE_ENUMDROPDOWN = 4;

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
        }else if (viewType == ANSTYPE_ENUMDROPDOWN){ // for spinner answer card
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card_spinner, parent, false);
            return new QuestionSpinnerAnsViewHolder(view);
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
        }else if(getItemViewType(position) == ANSTYPE_ENUMDROPDOWN){
            ((QuestionSpinnerAnsViewHolder) holder).setQuestionSpinnerAnsDetails(questions.get(position),position);
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (questions.get(position).getAnsType()== AnswerType.TEXTVALUE) {
            return ANSTYPE_TEXT;
        } else if(questions.get(position).getAnsType()==AnswerType.ENUMVALUE){
            return ANSTYPE_ENUM;
        }else if(questions.get(position).getAnsType()==AnswerType.NUMBERVALUE){
            return ANSTYPE_NUMBER;
        }else if(questions.get(position).getAnsType()==AnswerType.ENUMDROPDOWNVALUE){
            return ANSTYPE_ENUMDROPDOWN;
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

    class QuestionSpinnerAnsViewHolder extends RecyclerView.ViewHolder {

        private TextView txtQuestion;
        private Spinner spinnerAnswer;
        private Question question;
        private List<CharSequence> possibleAnss;
        private ArrayAdapter<CharSequence> adapter;

        public List<CharSequence> getPossibleAnss() {
            return possibleAnss;
        }

        public void setPossibleAnss(List<CharSequence> possibleAnss) {
            this.possibleAnss = possibleAnss;
        }

        public Question getQuestion() {
            return question;
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

        public ArrayAdapter<CharSequence> getAdapter() {
            return adapter;
        }

        public void setAdapter(ArrayAdapter<CharSequence> adapter) {
            this.adapter = adapter;
        }

        QuestionSpinnerAnsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            spinnerAnswer = itemView.findViewById(R.id.spinnerAnswer);
        }

        void setQuestionSpinnerAnsDetails(Question question,int position) {
            this.question = question;
            txtQuestion.setText(question.getQuestionText());
            //make CharSequence for arrayadapter from possible answer
            possibleAnss = new ArrayList<>();
            for(PossibleAns possibleAns : question.getPossibleAnss()){
                possibleAnss.add(possibleAns.getValue());
            }
            adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, possibleAnss);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAnswer.setAdapter(adapter);
            spinnerAnswer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if(question.getQuestionNum() == 1){
                            String countrySelected = (String)parent.getItemAtPosition(position);
                            String countryName = "";

                            //get the actual value of the country not the description, as countrySelectednly contains the description
                            for(PossibleAns possibleAns : question.getPossibleAnss()){
                                if(possibleAns.getValue().contentEquals(countrySelected))
                                    countryName = possibleAns.getName();
                            }

                            //trying to get the community view
                            RecyclerView recycler = (RecyclerView) parent.getParent().getParent().getParent();
                            QuestionSpinnerAnsViewHolder viewHolderCommunity = (QuestionSpinnerAnsViewHolder) recycler.findViewHolderForAdapterPosition(1);
                            if(viewHolderCommunity!=null){
                                //get the question that cantains all possible answers
                                Question communityQuestion = viewHolderCommunity.getQuestion();

                                //get the adapter for the spinner
                                ArrayAdapter<CharSequence> communityAdapter = viewHolderCommunity.getAdapter();

                                //select only the answer that matches the country selected
                                List<CharSequence> possibleAnss = new ArrayList<>();
                                for(PossibleAns possibleAns : communityQuestion.getPossibleAnss()){
                                    if(possibleAns.getParent_value().contentEquals(countryName)){
                                        Log.i("Tutorial", "community question posibleAns "+ possibleAns.getName() +possibleAns.getValue()+possibleAns.getParent_name()+possibleAns.getParent_value());
                                        possibleAnss.add(possibleAns.getValue());
                                    }
                                }

                                //notify the adapter to change the answers for community
                                communityAdapter.clear();
                                communityAdapter.addAll(possibleAnss);
                                communityAdapter.notifyDataSetChanged();

                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
            );

     */
            /*
            spinnerAnswer.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {}

                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    _retQuestionsWithAns.get(position).setAns((String)s.toString()); ;
                }
            });
             */
    /*
        }
    }

    public ArrayList<Question> retrieveData()
    {
        return _retQuestionsWithAns;
    }

     */
}
