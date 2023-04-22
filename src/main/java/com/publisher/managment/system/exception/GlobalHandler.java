package com.publisher.managment.system.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        ExceptionResponse resp = new ExceptionResponse(HttpStatus.NOT_FOUND,ex.getMessage());
        return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(BadRequestException.class)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse resp = new ExceptionResponse(HttpStatus.BAD_REQUEST,getRequiredFields(ex));
        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex, HttpServletRequest request){
        ExceptionResponse resp = new ExceptionResponse(HttpStatus.FORBIDDEN, ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.FORBIDDEN);
    }
//    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse resp = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
        return new ResponseEntity<>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String,String> getRequiredFields(MethodArgumentNotValidException ex){
        Map<String,String> errors= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e ->{
            errors.put(e.getField(),e.getDefaultMessage());
        });
        return errors;
    }
}
