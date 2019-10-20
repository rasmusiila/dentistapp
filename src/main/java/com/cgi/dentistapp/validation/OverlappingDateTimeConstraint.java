package com.cgi.dentistapp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = OverlappingDateTimeValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OverlappingDateTimeConstraint {
    String message() default "Date and time combination already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
