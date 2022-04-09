package com.example.reversed.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionState {

//    SUCCESS,
    EMAIL_EXISTS("Email already exists", HttpStatus.CONFLICT),
    EMAIL_PASSWORD_INCORRECT("Email or password is incorrect", HttpStatus.UNAUTHORIZED),
    NOT_AUTH("Not authorized", HttpStatus.FORBIDDEN),
    NOT_EXISTS("Couldn't get what you're looking for", HttpStatus.NOT_FOUND),
    ALREADY_EXISTS("You're trying to override an existing task", HttpStatus.CONFLICT),
    INVALID_TOKEN("Invalid token", HttpStatus.UNAUTHORIZED);


    //TODO - add more and validations to the service

    // TODO - now - there is no spring validation running because error.. fix it

    private String description;
    private HttpStatus status;

    ExceptionState(String description, HttpStatus status) {
        this.description = description;
        this.status = status;
    }
}
