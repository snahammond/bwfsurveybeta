package com.example.bwfsurveybeta;

public class Interchange {
    private String name;
    private int interchangeNumber;
    private Question question;
    private Answer answer;
    private int positionOnRecyler;

    public Interchange(String name, int interchangeNumber, Question question, Answer answer) {
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
}
