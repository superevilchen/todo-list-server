package com.example.reversed.advice;

import com.example.reversed.exceptions.TaskException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class TaskControllerAdvice {

    @ExceptionHandler(value = {TaskException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiException handle(TaskException e){
        return new ApiException(e.getState().name(), e.getState().getDescription());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiException handle(MethodArgumentNotValidException e){
        return new ApiException("invalid input", e.getMessage());
    }

    // TODO - fix this sql handler

    @ExceptionHandler(value = {SQLException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiException handle(SQLException e){
        return new ApiException("oy oy oy", e.getMessage());
    }


}
