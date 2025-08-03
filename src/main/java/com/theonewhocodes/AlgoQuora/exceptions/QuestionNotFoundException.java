package com.theonewhocodes.AlgoQuora.exceptions;


public class QuestionNotFoundException extends RuntimeException {

    private String message;

    public QuestionNotFoundException(String message) {
        super(message);
    }
}
