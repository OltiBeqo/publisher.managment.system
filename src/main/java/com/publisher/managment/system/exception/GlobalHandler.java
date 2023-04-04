package com.publisher.managment.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
@ControllerAdvice
public class GlobalHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorFormat> handleError(RuntimeException e, HttpServletRequest httpServletRequest){
        ErrorFormat errorFormat = new ErrorFormat(e.getMessage(), LocalDateTime.now());
        if(e instanceof BadRequestException){
            return new ResponseEntity<>(errorFormat, HttpStatus.BAD_REQUEST);
        }
        if (e instanceof NotFoundException){
            return new ResponseEntity<>(errorFormat,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(errorFormat,HttpStatus.INTERNAL_SERVER_ERROR);


    }
}
