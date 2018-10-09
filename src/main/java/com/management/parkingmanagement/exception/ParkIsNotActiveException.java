package com.management.parkingmanagement.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParkIsNotActiveException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ParkIsNotActiveException(final String message) {
        super(message);
    }
}
