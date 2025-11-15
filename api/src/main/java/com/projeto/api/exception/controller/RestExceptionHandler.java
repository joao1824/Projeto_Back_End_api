package com.projeto.api.exception.controller;

import com.projeto.api.exception.exceptions.EventIdNotFoundException;
import com.projeto.api.exception.models.RestErrorMensage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EventIdNotFoundException.class)
    private ResponseEntity<RestErrorMensage> eventIdNotFound(EventIdNotFoundException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMensage);
    };


}
