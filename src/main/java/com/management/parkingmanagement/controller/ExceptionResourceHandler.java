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
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error to send e-mail invoice to client",
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ParkingSessionNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleParkingSessionNotFoundException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error, park session not found",
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParkingSessionServiceException.class)
    public final ResponseEntity<ExceptionResponse> handleParkingSessionServiceException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Intern error, please contact support",
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ParkIsNotActiveException.class)
    public final ResponseEntity<ExceptionResponse> handleParkIsNotActiveException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error, currently park is not active",
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParkNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleParkNotFoundException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error, park not found",
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParkServiceException.class)
    public final ResponseEntity<ExceptionResponse> handleParkServiceException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Intern error, please contact support",
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PendentParkSessionException.class)
    public final ResponseEntity<ExceptionResponse> handlePendentParkSessionException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error, this client has pendent(s) park sessions, please contact support",
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleVehicleNotFoundException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error,  vehicle not found",
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VehicleServiceException.class)
    public final ResponseEntity<ExceptionResponse> handleVehicleServiceException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Intern error, please contact support",
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
