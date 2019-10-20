package com.cgi.dentistapp.validation;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DTOOverlappingDateTimeValidator implements ConstraintValidator<DTOOverlappingDateTimeConstraint, DentistVisitDTO> {
    @Autowired
    private DentistVisitService dentistVisitService;

    @Override
    public void initialize(DTOOverlappingDateTimeConstraint dtoOverlappingDateTimeConstraint) {
    }

    @Override
    public boolean isValid(DentistVisitDTO dentistVisitDTO, ConstraintValidatorContext constraintValidatorContext) {
        return !dentistVisitService.dateTimeCombinationExists(dentistVisitDTO.getVisitDate(),
                dentistVisitDTO.getVisitTime(), dentistVisitDTO.getDentistId());
    }
}
