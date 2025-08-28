package dev.robert.spring_boot.animal_shelter_spring.adoption.time_validation;

import java.time.Duration;

import dev.robert.spring_boot.animal_shelter_spring.adoption.Adoption;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VisitDurationConstrValidator implements ConstraintValidator<VisitDurationConstr, Adoption> {

    @Override
    public boolean isValid(Adoption entity, ConstraintValidatorContext context) {
      //Make sure visits are atleast 10 minutes
        Duration duration = Duration.between(entity.getStartTime(), entity.getEndTime());
        long minutes = duration.toMinutes();
        return minutes >= 10;
    }
}
