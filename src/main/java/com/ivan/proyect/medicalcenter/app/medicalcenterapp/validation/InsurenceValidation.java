package com.ivan.proyect.medicalcenter.app.medicalcenterapp.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.HealthInsurence;

public class InsurenceValidation implements Validator{

    @SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return HealthInsurence.class.isAssignableFrom(clazz);
    }

    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
        @SuppressWarnings("unused")
        HealthInsurence insurence = (HealthInsurence) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "");
    }

}
