package com.management.parkingmanagement.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PendentParkSessionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PendentParkSessionException(final String message) {
        super(message);
    }
}
