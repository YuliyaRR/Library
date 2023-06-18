package com.project.library.validator.annotations;

import com.project.library.validator.impl.ValidPublicationYearValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= ValidPublicationYearValidator.class)
public @interface ValidPublicationYear {
    String message() default "Incorrect year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
