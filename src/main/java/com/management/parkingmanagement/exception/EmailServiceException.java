package com.management.parkingmanagement.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailServiceException(final String message) {
        super(message);
    }
}
