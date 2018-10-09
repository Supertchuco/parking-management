package com.management.parkingmanagement.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParkingSessionServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ParkingSessionServiceException(final String message) {
        super(message);
    }
}
