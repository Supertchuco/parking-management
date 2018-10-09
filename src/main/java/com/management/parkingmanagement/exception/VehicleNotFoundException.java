package com.management.parkingmanagement.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VehicleNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VehicleNotFoundException(final String message) {
        super(message);
    }
}
