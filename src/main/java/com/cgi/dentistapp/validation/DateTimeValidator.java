package com.cgi.dentistapp.validation;

import com.cgi.dentistapp.entity.DentistVisitEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeValidator implements ConstraintValidator<DateTimeConstraint, DentistVisitEntity> {

    @Override
    public void initialize(DateTimeConstraint dentistVisitDateTimeConstraint) {
    }

    @Override
    public boolean isValid(DentistVisitEntity dentistVisitEntity, ConstraintValidatorContext constraintValidatorContext) {
        if (dentistVisitEntity.getVisitDate() == null || dentistVisitEntity.getVisitTime() == null) {
            return true;
        }
        // if chosen date is today and chosen time has already passed, then invalid
        return dentistVisitEntity.getVisitDate().compareTo(LocalDate.now()) != 0 ||
                dentistVisitEntity.getVisitTime().compareTo(LocalTime.now()) >= 1;
    }
}
