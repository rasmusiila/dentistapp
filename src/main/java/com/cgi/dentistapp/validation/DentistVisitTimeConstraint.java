package com.cgi.dentistapp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DentistVisitTimeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DentistVisitTimeConstraint {
    String message() default "Invalid time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
