package com.ivan.proyect.medicalcenter.app.medicalcenterapp.validation;


import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Patient;


@Component
public class PatientValidation implements Validator{

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
       return Patient.class.isAssignableFrom(clazz);
    }


    @SuppressWarnings("null")
    @Override
    public void validate(@NonNull Object target,@NonNull Errors errors) {
        @SuppressWarnings("unused")
        Patient patient = (Patient) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "");
    }
}
