package com.example.reversed.exceptions;

import lombok.Getter;

@Getter
public class TaskException extends Exception{

    private ExceptionState state;

    public TaskException(ExceptionState state) {
        this.state = state;
    }
}
