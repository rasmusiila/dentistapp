package com.cgi.dentistapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class DentistExceptionController {
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> exception(ConstraintViolationException exception) {
        StringBuilder responseBody = new StringBuilder("Exception: ConstraintViolationException\n");
        exception.getConstraintViolations().forEach(
                violation -> responseBody.append("\t").append(violation.getMessage()).append("\n")
        );
        // TODO: maybe you should make a dedicated html page for displaying these errors

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        return new ResponseEntity<>(responseBody, headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> exception(IllegalArgumentException exception) {
        StringBuilder responseBody = new StringBuilder("Exception: IllegalArgumentException\n");
        responseBody.append("\t").append(exception.getMessage()).append("\n");
        // TODO: maybe you should make a dedicated html page for displaying these errors

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        return new ResponseEntity<>(responseBody, headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<Object> exception(IllegalStateException exception) {
        StringBuilder responseBody = new StringBuilder("Exception: IllegalStateException\n");
        responseBody.append("\t").append(exception.getMessage()).append("\n");
        // TODO: maybe you should make a dedicated html page for displaying these errors

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        return new ResponseEntity<>(responseBody, headers, HttpStatus.NOT_FOUND);
    }
}
