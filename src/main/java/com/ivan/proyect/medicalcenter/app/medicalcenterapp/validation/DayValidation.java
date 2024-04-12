package com.ivan.proyect.medicalcenter.app.medicalcenterapp.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Day;

@Component
public class DayValidation implements Validator{

    @SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return Day.class.isAssignableFrom(clazz);
    }

    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
        @SuppressWarnings("unused")
        Day day = (Day) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "");
    }

}
