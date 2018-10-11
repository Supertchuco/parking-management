package com.management.parkingmanagement.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParkingSessionNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ParkingSessionNotFoundException(final String message) {
        super(message);
    }
}
