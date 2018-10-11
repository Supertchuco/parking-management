package com.management.parkingmanagement.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VehicleServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VehicleServiceException(final String message) {
        super(message);
    }
}
