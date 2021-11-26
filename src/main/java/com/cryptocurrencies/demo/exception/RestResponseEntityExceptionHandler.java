package com.cryptocurrencies.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CurrencyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
   public ResponseEntity<String> handleCurrencyNotFoundException(CurrencyNotFoundException ex) {
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
   }
}
