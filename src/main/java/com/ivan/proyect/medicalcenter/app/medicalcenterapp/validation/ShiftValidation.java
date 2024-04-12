package com.ivan.proyect.medicalcenter.app.medicalcenterapp.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Shift;

@Component
public class ShiftValidation implements Validator{
    
    @SuppressWarnings("null")
    @Override
    public boolean supports( Class<?> clazz) {
        return Shift.class.isAssignableFrom(clazz);
    }
    
    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
        @SuppressWarnings("unused")
        Shift shift = (Shift) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "");
    }

}
