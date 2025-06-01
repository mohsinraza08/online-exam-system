package com.exam.onlineexamsystem.dto;

import com.exam.onlineexamsystem.model.exam.Question;

public class QuestionResult {
    private Question question;
    private String givenAnswer;
    private boolean correct;

    public QuestionResult(Question question, String givenAnswer, boolean correct) {
        this.question = question;
        this.givenAnswer = givenAnswer;
        this.correct = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public boolean isCorrect() {
        return correct;
    }
}
