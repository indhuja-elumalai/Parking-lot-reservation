package com.parking.lot.reservation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.parking.lot.reservation.dto.ReservationRequestDto;

import java.time.temporal.ChronoUnit;

public class MaxReservationDurationValidator implements ConstraintValidator<MaxReservationDuration, ReservationRequestDto> {

    private int maxHours;

    @Override
    public void initialize(MaxReservationDuration constraintAnnotation) {
        this.maxHours = constraintAnnotation.hours();
    }

    @Override
    public boolean isValid(ReservationRequestDto dto, ConstraintValidatorContext context) {
        if (dto.getStartTime() == null || dto.getEndTime() == null) {
            return true;
        }

        long durationInHours = ChronoUnit.HOURS.between(dto.getStartTime(), dto.getEndTime());
        if (durationInHours > maxHours) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Reservation duration cannot exceed " + maxHours + " hours.").addConstraintViolation();
            return false;
        }
        return true;
    }
}