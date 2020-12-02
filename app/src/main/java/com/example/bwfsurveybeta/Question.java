package com.example.bwfsurveybeta;


import com.amplifyframework.datastore.generated.model.AnswerType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Question  implements Comparable<Question>{
    private String questionText;
    private AnswerType ansType;
    private String ansTypeName;
    private ArrayList<PossibleAns> possibleAnss;
    private int questionNum;
    private Object Ans;


    public Question(String questionText, String ansType, String ansTypeName, ArrayList<PossibleAns> possibleAnss, int questionNum) {
        super();
        this.questionText = questionText;
        this.ansType = AnswerType.valueOf(ansType);
        this.ansTypeName = ansTypeName;
        this.possibleAnss = possibleAnss;
        this.questionNum = questionNum;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public AnswerType getAnsType() {
        return ansType;
    }

    public void setAnsType(String ansType) {
        this.ansType = AnswerType.valueOf(ansType);
    }

    public String getAnsTypeName() {
        return ansTypeName;
    }

    public void setAnsTypeName(String ansTypeName) {
        this.ansTypeName = ansTypeName;
    }

    public ArrayList<PossibleAns> getPossibleAnss() {
        return possibleAnss;
    }

    public void setPossibleAnss(ArrayList<PossibleAns> possibleAnss) {
        this.possibleAnss = possibleAnss;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public Object getAns() {
        return Ans;
    }

    public void setAns(Object ans) {
        Ans = ans;
    }


    @Override
    public int compareTo(Question o) {
        return this.getQuestionNum() - o.getQuestionNum();
    }
}
