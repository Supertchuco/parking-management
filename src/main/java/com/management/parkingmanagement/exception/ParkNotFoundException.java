package com.management.parkingmanagement.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParkNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ParkNotFoundException(final String message) {
        super(message);
    }
}
