package com.example.wallet.controller;

import com.example.wallet.exception.InvalidWalletBalanceException;
import com.example.wallet.exception.UnknownWalletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class DefaultRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidWalletBalanceException.class, UnknownWalletException.class})
    public ResponseEntity<String> externalApiHandler(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        log.error("MethodArgumentNotValidException: {}.", ex.getMessage());

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> String.format("%s : %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.joining("\n"));

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
