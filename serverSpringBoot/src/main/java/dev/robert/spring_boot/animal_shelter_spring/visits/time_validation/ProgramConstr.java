package dev.robert.spring_boot.animal_shelter_spring.visits.time_validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = ProgramConstrValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ProgramConstr {

    String message() default "The visit must respect working hours ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
