package com.hng.stageOne.exception.exceptionHandler;

import com.hng.stageOne.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Map;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<?> internalServerErrorException(InternalErrorException exception){
        ExceptionResponse<Map<String, String>> exceptionResponse = new ExceptionResponse<>();
        exceptionResponse.setTimestamp(Instant.now());
        exceptionResponse.setMessage("Internal Error!");
        exceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionResponse.setData(null);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<?> cityNotFoundException(CityNotFoundException exception){
        ExceptionResponse<Map<String, String>> exceptionResponse = new ExceptionResponse<>();
        exceptionResponse.setTimestamp(Instant.now());
        exceptionResponse.setMessage("City Not Found!");
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setData(null);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
