package com.cgi.dentistapp.validation;

import com.cgi.dentistapp.dto.DentistVisitDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalTime;

public class DTODateTimeValidator implements ConstraintValidator<DTODateTimeConstraint, DentistVisitDTO> {
    @Override
    public void initialize(DTODateTimeConstraint dentistVisitDTODateTimeConstraint) {
    }

    @Override
    public boolean isValid(DentistVisitDTO dentistDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (dentistDTO.getVisitDate() == null || dentistDTO.getVisitTime() == null) {
            return true;
        }
        // if chosen date is today and chosen time has already passed, then invalid
        return dentistDTO.getVisitDate().compareTo(LocalDate.now()) != 0 ||
                dentistDTO.getVisitTime().compareTo(LocalTime.now()) >= 1;
    }
}
