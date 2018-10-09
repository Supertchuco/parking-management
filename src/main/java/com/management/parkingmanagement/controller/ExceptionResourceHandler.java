package com.management.parkingmanagement.controller;

import com.management.parkingmanagement.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionResourceHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleAllExceptions() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailServiceException.class)
    public final ResponseEntity<ExceptionResponse> handleEmailServiceException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error during save transaction information, please contact the support for more information",
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ParkingSessionServiceException.class)
    public final ResponseEntity<ExceptionResponse> handleParkingSessionServiceException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error during delete all transactions information on virtual database, please contact the support for more information",
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ParkIsNotActiveException.class)
    public final ResponseEntity<ExceptionResponse> handleParkIsNotActiveException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error during delete all transactions information on virtual database, please contact the support for more information",
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ParkNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleParkNotFoundException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error during delete all transactions information on virtual database, please contact the support for more information",
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleVehicleNotFoundException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error during delete all transactions information on virtual database, please contact the support for more information",
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
