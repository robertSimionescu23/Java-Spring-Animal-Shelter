package dev.robert.spring_boot.animal_shelter_spring.adoption.dateValidation;

import dev.robert.spring_boot.animal_shelter_spring.adoption.Adoption;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeScheduleValidator implements ConstraintValidator<ScheduleValid, Adoption> {

    @Override
    public boolean isValid(Adoption entity, ConstraintValidatorContext context) {
        if (entity.getStartTime() == null || entity.getEndTime() == null) {
            return true; // Not Null validation can handle this instead
        }
        return entity.getEndTime().isAfter(entity.getStartTime());
    }
}
