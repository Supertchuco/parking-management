package com.management.parkingmanagement.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotStoppedStatusOnPayloadException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotStoppedStatusOnPayloadException(final String message) {
        super(message);
    }
}
