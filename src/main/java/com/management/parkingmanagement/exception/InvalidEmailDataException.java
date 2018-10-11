package com.management.parkingmanagement.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidEmailDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidEmailDataException(final String message) {
        super(message);
    }
}
