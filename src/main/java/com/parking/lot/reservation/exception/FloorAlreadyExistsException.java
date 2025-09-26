package com.parking.lot.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FloorAlreadyExistsException extends RuntimeException {
    public FloorAlreadyExistsException(String message) {
        super(message);
    }
}