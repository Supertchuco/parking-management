package com.management.parkingmanagement.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParkServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ParkServiceException(final String message) {
        super(message);
    }
}
