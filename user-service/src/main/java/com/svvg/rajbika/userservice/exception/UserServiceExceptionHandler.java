package com.svvg.rajbika.userservice.exception;

import com.svvg.rajbika.sharedservice.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserServiceExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleCreateUserException(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseHandler.responseBuilder("Error creating the user. Few parameters were not correct.", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), errorMap);
    }

}
