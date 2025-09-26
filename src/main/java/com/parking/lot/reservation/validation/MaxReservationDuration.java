package com.parking.lot.reservation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MaxReservationDurationValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxReservationDuration {
    String message() default "Reservation duration cannot exceed 24 hours";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int hours() default 24; // This method is now defined and matches the validator.
}
