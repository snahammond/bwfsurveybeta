package com.bwfsurvey.bwfsurveybeta;

public class ViewOnlyInterchange implements Comparable<ViewOnlyInterchange>{
    private String name;
    private int interchangeNumber;
    private String question;
    private String answer;

    public ViewOnlyInterchange(String name, int interchangeNumber, String question, String answer) {
        this.name = name;
        this.interchangeNumber = interchangeNumber;
        this.question = question;
        this.answer = answer;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int compareTo(ViewOnlyInterchange o) {
        return this.getInterchangeNumber() - o.getInterchangeNumber();
    }
}
