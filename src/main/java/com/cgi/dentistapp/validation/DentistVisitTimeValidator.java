package com.cgi.dentistapp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;

public class DentistVisitTimeValidator implements ConstraintValidator<DentistVisitTimeConstraint, LocalTime> {

    @Override
    public void initialize(DentistVisitTimeConstraint dentistVisitTimeConstraint) {
    }

    @Override
    public boolean isValid(LocalTime visitTime, ConstraintValidatorContext constraintValidatorContext) {
        if (visitTime == null) {
            return true; // This seems weird but this validator does not care about null values, we have NotNull for that
        }
        return visitTime.compareTo(LocalTime.of(8, 0)) > -1 &&
                visitTime.compareTo(LocalTime.of(18, 0)) < 1;
    }
}
