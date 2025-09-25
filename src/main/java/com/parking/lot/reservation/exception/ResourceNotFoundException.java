package com.parking.lot.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when a requested resource (e.g., a Floor or Slot)
 * is not found in the system.
 * This automatically sets the HTTP status code to 404 (Not Found).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
