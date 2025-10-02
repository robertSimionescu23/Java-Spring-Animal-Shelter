package dev.robert.spring_boot.animal_shelter_spring.visits.time_validation;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Value;

import dev.robert.spring_boot.animal_shelter_spring.visits.Visit;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProgramConstrValidator implements ConstraintValidator<ProgramConstr, Visit> {

    @Value("${app.admin.programStart}")
    private LocalTime programStartTime;

    @Value("${app.admin.programEnd}")
    private LocalTime programEndTime;

    @Override
    public boolean isValid(Visit entity, ConstraintValidatorContext context) {
        if (entity.getStartTime() == null || entity.getEndTime() == null) {
            return true; // Not Null validation can handle this instead
        }

        return entity.getStartTime().isAfter(programStartTime) && entity.getEndTime().isBefore(programEndTime);
    }
}
