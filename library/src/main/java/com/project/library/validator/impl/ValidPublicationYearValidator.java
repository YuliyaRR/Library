package com.project.library.validator.impl;

import com.project.library.validator.annotations.ValidPublicationYear;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidPublicationYearValidator implements ConstraintValidator<ValidPublicationYear, Integer> {
        @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();

        return value < currentYear && value > 0;
    }
}
