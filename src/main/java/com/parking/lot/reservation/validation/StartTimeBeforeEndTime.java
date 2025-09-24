package com.parking.lot.reservation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StartTimeBeforeEndTimeValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface StartTimeBeforeEndTime {
    String message() default "Start time must be before end time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}