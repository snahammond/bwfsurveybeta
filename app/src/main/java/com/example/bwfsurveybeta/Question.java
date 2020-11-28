package com.example.bwfsurveybeta;


import java.util.ArrayList;
import java.util.List;

public class Question {
    private String questionText;
    private AnsType ansType;
    private String ansTypeName;
    private ArrayList<PossibleAns> possibleAnss;
    private Object Ans;

    public Question(String questionText, String ansType, String ansTypeName, ArrayList<PossibleAns> possibleAnss) {
        this.questionText = questionText;
        this.ansType = AnsType.valueOf(ansType);
        this.ansTypeName = ansTypeName;
        this.possibleAnss = possibleAnss;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public AnsType getAnsType() {
        return ansType;
    }

    public void setAnsType(String ansType) {
        this.ansType = AnsType.valueOf(ansType);
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

    public Object getAns() {
        return Ans;
    }

    public void setAns(Object ans) {
        Ans = ans;
    }
}
