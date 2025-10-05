package dev.robert.spring_boot.animal_shelter_spring.visits.time_validation;

import dev.robert.spring_boot.animal_shelter_spring.visits.Visit;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ScheduleStartValidConstrValidator implements ConstraintValidator<ScheduleStartValidConstr, Visit> {

    @Override
    public boolean isValid(Visit entity, ConstraintValidatorContext context) {
        if (entity.getStartTime() == null || entity.getEndTime() == null) {
            return true; // Not Null validation can handle this instead
        }

        return entity.getEndTime().isAfter(entity.getStartTime());
    }
}
