package com.example;

/**
 * Created by joe-work on 3/15/15.
 */
public class QuestionResponse {

    public int questionId;
    public int answer;
    public int[] acceptableAnswers;
    public int importance;

    public QuestionResponse(){

    }

    public QuestionResponse(final int id, final int answer, final int[] acceptableAnswers, final int importance){
        this.questionId = id;
        this.answer = answer;
        this.acceptableAnswers = acceptableAnswers;
        this.importance = importance;
    }
}
