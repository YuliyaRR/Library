package com.project.library.validator.annotations;

import com.project.library.validator.impl.ValidStringValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= ValidStringValidator.class)
public @interface ValidString {
    String message() default "Information not entered";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
