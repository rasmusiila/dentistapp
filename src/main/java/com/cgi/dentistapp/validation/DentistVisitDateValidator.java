package com.cgi.dentistapp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DentistVisitDateValidator implements ConstraintValidator<DentistVisitDateConstraint, LocalDate> {

    @Override
    public void initialize(DentistVisitDateConstraint dentistVisitDateConstraint) {
    }

    @Override
    public boolean isValid(LocalDate visitDate, ConstraintValidatorContext constraintValidatorContext) {
        if (visitDate == null) {
            return true; // This seems weird but this validator does not care about null values
        }
        return visitDate.compareTo(LocalDate.now()) > -1 && visitDate.compareTo(LocalDate.now().plusYears(1)) < 1;
    }
}
