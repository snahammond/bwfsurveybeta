package com.example.bwfsurveybeta;

import java.util.ArrayList;

public class Answer {
    private AnswerDef answerDef;
    private ArrayList<AnswerValue> answerValArrayList;
    private Object Ans;


    public Answer(AnswerDef answerDef, ArrayList<AnswerValue> answerValArrayList) {
        this.answerDef = answerDef;
        this.answerValArrayList = answerValArrayList;
    }

    public AnswerDef getAnswerDef() {
        return answerDef;
    }

    public void setAnswerDef(AnswerDef answerDef) {
        this.answerDef = answerDef;
    }

    public ArrayList<AnswerValue> getAnswerValArrayList() {
        return answerValArrayList;
    }

    public void setAnswerValArrayList(ArrayList<AnswerValue> answerValArrayList) {
        this.answerValArrayList = answerValArrayList;
    }

    public Object getAns() {
        return Ans;
    }

    public void setAns(Object ans) {
        Ans = ans;
    }
}
