package com.example.bwfsurveybeta;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.AnswerType;

import java.util.ArrayList;
import java.util.List;

public class InterchangeCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Interchange> interchanges;
    private ArrayList<Interchange> _retInterchangesWithAns;
    private Context context;

    private static int ANSTYPE_TEXT = 1;
    private static int ANSTYPE_ENUM = 2;
    private static int ANSTYPE_NUMBER = 3;
    private static int ANSTYPE_ENUMDROPDOWN = 4;

    public InterchangeCardAdapter(InitialSurveyActivity mainActivity, ArrayList<Interchange> interchanges) {
        this.interchanges = interchanges;
        this._retInterchangesWithAns = interchanges;
        this.context = mainActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == ANSTYPE_TEXT) { // for interchange card with string answer
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card_string, parent, false);
            return new InterchangeTextAnsViewHolder(view);
        } else if (viewType == ANSTYPE_ENUM){ // for interchange card with enum answer
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card_enum, parent, false);
            return new InterchangeEnumAnsViewHolder(view);
        }else if (viewType == ANSTYPE_NUMBER){ // for interchange card with number answer
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card_number, parent, false);
            return new InterchangeNumberAnsViewHolder(view);
        }else if (viewType == ANSTYPE_ENUMDROPDOWN){ // for interchange card with spinner answer
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card_spinner, parent, false);
            return new InterchangeSpinnerAnsViewHolder(view);
        }else{
            return null;
        }
    }

    class InterchangeTextAnsViewHolder extends RecyclerView.ViewHolder {

        private TextView txtQuestion;
        private TextView editAnswer;

        InterchangeTextAnsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            editAnswer = itemView.findViewById(R.id.editAnswer);
        }

        void setQuestionTextAnsDetails(Interchange interchange,int position) {
            txtQuestion.setText(interchange.getQuestion().getQuestionText());
            editAnswer.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {}

                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    _retInterchangesWithAns.get(position).getAnswer().setAns((String)s.toString()); ;
                }
            });
        }
    }

    class InterchangeNumberAnsViewHolder extends RecyclerView.ViewHolder {

        private TextView txtQuestion;
        private TextView editAnswer;

        InterchangeNumberAnsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            editAnswer = itemView.findViewById(R.id.editAnswer);
        }

        void setQuestionNumberAnsDetails(Interchange interchange,int position) {
            txtQuestion.setText(interchange.getQuestion().getQuestionText());
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
                    _retInterchangesWithAns.get(position).getAnswer().setAns((Integer)myNum);
                }
            });
        }
    }

    class InterchangeEnumAnsViewHolder extends RecyclerView.ViewHolder{

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



        InterchangeEnumAnsViewHolder(@NonNull View itemView) {
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

        void setQuestionEnumAnsDetails(Interchange interchange, int position) {
            txtQuestion.setText(interchange.getQuestion().getQuestionText());
            Log.i("Tutorial", "question "+ interchange.getQuestion().getQuestionText());
            addRadioButtons(interchange.getAnswer().getAnswerValArrayList(),position);
        }

        public void addRadioButtons(ArrayList<AnswerValue> possibleAnss,int position) {

            if(possibleAnss.size()>0){
                radio_1.setVisibility(View.VISIBLE);
                radio_1.setText( possibleAnss.get(0).getValue());
                radio_1.setHint(possibleAnss.get(0).getName());
                radio_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedRadioEnumStrValue = ((RadioButton)v).getHint().toString();
                        _retInterchangesWithAns.get(position).getAnswer().setAns(selectedRadioEnumStrValue);
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
                        _retInterchangesWithAns.get(position).getAnswer().setAns(selectedRadioEnumStrValue);
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
                        _retInterchangesWithAns.get(position).getAnswer().setAns(selectedRadioEnumStrValue);
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
                        _retInterchangesWithAns.get(position).getAnswer().setAns(selectedRadioEnumStrValue);
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
                        _retInterchangesWithAns.get(position).getAnswer().setAns(selectedRadioEnumStrValue);
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
                        _retInterchangesWithAns.get(position).getAnswer().setAns(selectedRadioEnumStrValue);
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
                        _retInterchangesWithAns.get(position).getAnswer().setAns(selectedRadioEnumStrValue);
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
                        _retInterchangesWithAns.get(position).getAnswer().setAns(selectedRadioEnumStrValue);
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
                        _retInterchangesWithAns.get(position).getAnswer().setAns(selectedRadioEnumStrValue);
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
                        _retInterchangesWithAns.get(position).getAnswer().setAns(selectedRadioEnumStrValue);
                    }
                });
            }
        }
    }

    class InterchangeSpinnerAnsViewHolder extends RecyclerView.ViewHolder {

        private TextView txtQuestion;
        private Spinner spinnerAnswer;
        private Interchange interchange;
        private ArrayAdapter<CharSequence> adapter;

        public ArrayAdapter<CharSequence> getAdapter() {
            return adapter;
        }

        public void setAdapter(ArrayAdapter<CharSequence> adapter) {
            this.adapter = adapter;
        }

        InterchangeSpinnerAnsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            spinnerAnswer = itemView.findViewById(R.id.spinnerAnswer);
        }

        void setQuestionSpinnerAnsDetails(Interchange interchange,int position) {
            this.interchange = interchange;
            txtQuestion.setText(interchange.getQuestion().getQuestionText());

            //make CharSequence for arrayadapter from possible answer
            List<CharSequence> possibleAnss = new ArrayList<>();
            for(AnswerValue possibleAns : interchange.getAnswer().getAnswerValArrayList()){
                possibleAnss.add(possibleAns.getDesc());
            }
            adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, possibleAnss);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAnswer.setAdapter(adapter);
            spinnerAnswer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //get the item that was selected
                        String selectedAnswerValueDescStr = (String)parent.getItemAtPosition(position);
                        Log.i("Tutorial", "selectedAnswerValueDescStr " +selectedAnswerValueDescStr);

                        AnswerValue answerValueSelected = null;
                        //look for the answer value object this selected str value and see if it has children
                        for(AnswerValue answerValue : interchange.getAnswer().getAnswerValArrayList()){
                            if(selectedAnswerValueDescStr.contentEquals(answerValue.getDesc())){
                                //this is the Answer value selected
                                answerValueSelected = answerValue;
                            }
                        }
                        Log.i("Tutorial", "answerValueSelected " +answerValueSelected);

                        if(answerValueSelected!=null){
                            if(!answerValueSelected.getChildname().isEmpty()){
                                //this answer value has a child with the name answerValueSelected.getChildname()
                                Log.i("Tutorial", "answerValueSelected has children " );
                                //try to get the child view by getting the recycler
                                RecyclerView recycler = (RecyclerView) parent.getParent().getParent().getParent();

                                int childPos = -1;
                                List<CharSequence> possibleAnssChild = new ArrayList<>();
                                for(Interchange interchange : interchanges){
                                    if(interchange.getName() == answerValueSelected.getChildname()){
                                        //this is the interface the has the, get its position on the recyler
                                        childPos = interchange.getPositionOnRecyler();
                                        Log.i("Tutorial", "childPos on recycler is " +childPos);
                                        //select only the answer values of this child that child that has parent
                                        for(AnswerValue answerValue : interchange.getAnswer().getAnswerValArrayList()){
                                            if(answerValue.getParentvalue().contentEquals(answerValueSelected.getValue())){
                                                possibleAnssChild.add(answerValue.getValue());
                                                Log.i("Tutorial", "child posibleAns "+ answerValue.getName() +answerValue.getValue()+answerValue.getParentvalue()+answerValue.getParentvalue());
                                            }
                                        }
                                    }
                                }

                                //trying to get the community view
                                InterchangeSpinnerAnsViewHolder viewHolderChild = (InterchangeSpinnerAnsViewHolder) recycler.findViewHolderForAdapterPosition(childPos);
                                if(viewHolderChild!=null){
                                    Log.i("Tutorial", "Got child view holder on the recycler");

                                    //get the adapter for the spinner
                                    ArrayAdapter<CharSequence> childAdapter = viewHolderChild.getAdapter();
                                    if(childAdapter!=null){
                                        Log.i("Tutorial", "Got child adapter from view holder");
                                        //notify the adapter to change the answers for child
                                        childAdapter.clear();
                                        childAdapter.addAll(possibleAnssChild);
                                        childAdapter.notifyDataSetChanged();
                                    }

                                }

                            }
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
            );


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

        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ANSTYPE_TEXT) {
            ((InterchangeTextAnsViewHolder) holder).setQuestionTextAnsDetails(interchanges.get(position),position);
        } else if (getItemViewType(position) == ANSTYPE_ENUM){
            ((InterchangeEnumAnsViewHolder) holder).setQuestionEnumAnsDetails(interchanges.get(position),position);
        }else if(getItemViewType(position) == ANSTYPE_NUMBER){
            ((InterchangeNumberAnsViewHolder) holder).setQuestionNumberAnsDetails(interchanges.get(position),position);
        }else if(getItemViewType(position) == ANSTYPE_ENUMDROPDOWN){
            ((InterchangeSpinnerAnsViewHolder) holder).setQuestionSpinnerAnsDetails(interchanges.get(position),position);
        }
    }

    @Override
    public int getItemCount() {
        return interchanges.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (interchanges.get(position).getAnswer().getAnswerDef().getType()== AnswerType.TEXTVALUE) {
            return ANSTYPE_TEXT;
        } else if(interchanges.get(position).getAnswer().getAnswerDef().getType()==AnswerType.ENUMVALUE){
            return ANSTYPE_ENUM;
        }else if(interchanges.get(position).getAnswer().getAnswerDef().getType()==AnswerType.NUMBERVALUE){
            return ANSTYPE_NUMBER;
        }else if(interchanges.get(position).getAnswer().getAnswerDef().getType()==AnswerType.ENUMDROPDOWNVALUE){
            return ANSTYPE_ENUMDROPDOWN;
        }else{
            return 0;
        }

    }
}
