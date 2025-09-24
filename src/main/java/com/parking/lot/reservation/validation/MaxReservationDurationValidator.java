package com.parking.lot.reservation.validation;

import com.parking.lot.reservation.dto.ReservationRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Duration;

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
        Duration duration = Duration.between(dto.getStartTime(), dto.getEndTime());
        return duration.toHours() <= maxHours;
    }
}