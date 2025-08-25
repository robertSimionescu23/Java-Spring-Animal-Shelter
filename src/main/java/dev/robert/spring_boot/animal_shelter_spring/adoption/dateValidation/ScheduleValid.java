package dev.robert.spring_boot.animal_shelter_spring.adoption.dateValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TimeScheduleValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ScheduleValid {
    String message() default "End time must be after the start time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
