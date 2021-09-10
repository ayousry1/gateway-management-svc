package com.magement.gateway.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorAdvice {
    @ExceptionHandler
    public ResponseEntity<ResponseMessage> handle(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        ResponseMessage msg = new ResponseMessage(errors.toString().replace("[", "")
                .replace("]", ""));
        return new ResponseEntity<>(msg, null, HttpStatus.BAD_REQUEST);
    }
}
