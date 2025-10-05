package dev.robert.spring_boot.animal_shelter_spring.visits.time_validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = VisitDurationConstrValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitDurationConstr {
    String message() default "Visit duration must be atleast 10 minutes";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
