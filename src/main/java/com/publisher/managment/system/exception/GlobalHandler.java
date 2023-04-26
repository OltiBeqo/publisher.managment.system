package com.publisher.managment.system.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ExceptionResponse resp = new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BadRequestException.class })
    protected ResponseEntity<Object> handleBadRequestException(RuntimeException ex, WebRequest request) {
        ExceptionResponse resp = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return handleExceptionInternal(ex, resp, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request) {
        ExceptionResponse resp = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        ExceptionResponse resp = new ExceptionResponse(HttpStatus.FORBIDDEN, ex.getMessage());
        return new ResponseEntity<Object>(resp, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ HttpClientErrorException.MethodNotAllowed.class })
    public ResponseEntity<Object> handleMethodNotAllowedException(Exception ex, WebRequest request) {
        ExceptionResponse resp = new ExceptionResponse(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
        return new ResponseEntity<Object>(resp, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }


}
