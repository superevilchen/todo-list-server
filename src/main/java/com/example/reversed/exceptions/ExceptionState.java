package com.example.reversed.exceptions;

public enum ExceptionState {

//    SUCCESS,
    NOT_EXISTS("Couldn't get what you're looking for"),
    ALREADY_EXISTS("You're trying to override an existing task");

    //TODO - add more and validations to the service

    // TODO - now - there is no spring validation running because error.. fix it

    private String description;

    ExceptionState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
