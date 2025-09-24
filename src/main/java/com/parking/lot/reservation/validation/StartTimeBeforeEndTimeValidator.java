package com.parking.lot.reservation.validation;

import com.parking.lot.reservation.dto.ReservationRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartTimeBeforeEndTimeValidator implements ConstraintValidator<StartTimeBeforeEndTime, ReservationRequestDto> {
    @Override
    public boolean isValid(ReservationRequestDto dto, ConstraintValidatorContext context) {
        if (dto.getStartTime() == null || dto.getEndTime() == null) {
            return true;
        }
        return dto.getStartTime().isBefore(dto.getEndTime());
    }
}