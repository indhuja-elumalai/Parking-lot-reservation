package com.parking.lot.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SlotAlreadyReservedException extends RuntimeException {
    public SlotAlreadyReservedException(String message) {
        super(message);
    }
}