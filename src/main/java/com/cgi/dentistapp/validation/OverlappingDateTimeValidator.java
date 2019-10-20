package com.cgi.dentistapp.validation;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OverlappingDateTimeValidator implements ConstraintValidator<OverlappingDateTimeConstraint, DentistVisitEntity> {
    @Autowired
    private DentistVisitService dentistVisitService;

    @Override
    public void initialize(OverlappingDateTimeConstraint overlappingDateTimeConstraint) {
    }

    @Override
    public boolean isValid(DentistVisitEntity dentistVisitEntity, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(dentistVisitService);
        return dentistVisitService.dateTimeCombinationExists(dentistVisitEntity.getVisitDate(),
                dentistVisitEntity.getVisitTime(), dentistVisitEntity.getDentist().getId());
    }
}
