package com.publisher.managment.system.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;
@Data
public class ExceptionResponse {
        private HttpStatus status;
        private Object message;
        private Date timestamp;

        public ExceptionResponse(HttpStatus status, Object message) {
            this.status = status;
            this.message = message;
            this.timestamp = new Date();
        }
}
