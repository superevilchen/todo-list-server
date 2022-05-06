package com.example.reversed.advice;

import com.example.reversed.exceptions.ExceptionState;
import com.example.reversed.exceptions.TaskException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;

@RestControllerAdvice
public class TaskControllerAdvice {

    @ExceptionHandler(value = {MissingRequestHeaderException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiException handleMissingHeaders(MissingRequestHeaderException e){
        return new ApiException("Todo Application",e.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiException handleMissingHeaders(MethodArgumentTypeMismatchException e){
        return new ApiException("Todo Application",e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiException handle404() {
        return new ApiException("Todo Application","Page Not Found 4-0-4");
    }

    @ExceptionHandler(value = {TaskException.class})
    public ResponseEntity<?> handleErrors(TaskException e) {
        ExceptionState errMsg = e.getState();
        HttpStatus status = errMsg.getStatus();
        ApiException apiException = new ApiException(errMsg.name(), e.getMessage());
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiException handle(MethodArgumentNotValidException e){
        return new ApiException("invalid input", e.getMessage());
    }

    @ExceptionHandler(value = {SQLException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiException handle(SQLException e){
        return new ApiException("Todo Application", e.getMessage());
    }


}
