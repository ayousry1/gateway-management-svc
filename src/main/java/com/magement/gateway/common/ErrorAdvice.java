package com.magement.gateway.common;

import com.magement.gateway.common.exceptions.DeviceNotFoundException;
import com.magement.gateway.common.exceptions.GatewayNotFoundException;
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(errors.toString().
                replace("[", "").replace("]", "")));
    }

    @ExceptionHandler
    public ResponseEntity<ResponseMessage> handle(GatewayNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ResponseMessage> handle(DeviceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));
    }
}
