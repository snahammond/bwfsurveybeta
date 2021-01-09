package com.bwfsurvey.bwfsurveybeta;

import com.amplifyframework.datastore.generated.model.AnswerType;

public class Interchange implements Comparable<Interchange>{
    private String name;
    private int interchangeNumber;
    private Question question;
    private Answer answer;
    private int positionOnRecyler;
    private Validation validation;

    public Interchange(String name, int interchangeNumber, Question question, Answer answer, Validation validation) {
        this.name = name;
        this.interchangeNumber = interchangeNumber;
        this.question = question;
        this.answer = answer;
    }

    public Interchange(String name, int interchangeNumber) {
        this.name = name;
        this.interchangeNumber = interchangeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInterchangeNumber() {
        return interchangeNumber;
    }

    public void setInterchangeNumber(int interchangeNumber) {
        this.interchangeNumber = interchangeNumber;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public int getPositionOnRecyler() {
        return positionOnRecyler;
    }

    public void setPositionOnRecyler(int positionOnRecyler) {
        this.positionOnRecyler = positionOnRecyler;
    }

    public Validation getValidation() {
        return validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    public boolean isValid(){
        if(this.validation!=null){
            if(!this.validation.isMandatory())
                return true;
            else{
                //if the interchange is mandatory, check if it has a value
                if(this.getAnswer().getAnswerDef().getType()== AnswerType.TEXTVALUE){
                    if(this.getAnswer().getAns()!=null ){
                        if(!this.getAnswer().getAns().toString().isEmpty())
                            return true;
                    }
                }
                if(this.getAnswer().getAnswerDef().getType()== AnswerType.NUMBERVALUE){
                    if(this.getAnswer().getAns()!=null ){
                        if(this.getValidation().getDefaultValue()!=null){
                            if(!this.getValidation().getDefaultValue().toString().isEmpty()){
                                int minValue = 0;
                                try {
                                    minValue = Integer.parseInt(this.getValidation().getDefaultValue().toString());
                                }catch (Exception x){
                                    minValue = 0;
                                }
                                if(this.getAnswer().getAns()!=null ){
                                    int userAns = 0;
                                    try {
                                        userAns = Integer.parseInt(this.getAnswer().getAns().toString());
                                    }catch (Exception x){
                                        userAns = 0;
                                    }
                                    if(minValue<userAns)
                                        return true;
                                }
                            }else{
                                return true;
                            }
                        }
                    }
                }else if(this.getAnswer().getAns()!=null){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int compareTo(Interchange o) {
        return this.getInterchangeNumber() - o.getInterchangeNumber();
    }
}
