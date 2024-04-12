package com.ivan.proyect.medicalcenter.app.medicalcenterapp.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Schedule;

@Component
public class ScheduleValidation implements Validator{

    @SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return Schedule.class.isAssignableFrom(clazz);
    }

    @Override
    @SuppressWarnings("null")
    public void validate(Object target, Errors errors) {
        @SuppressWarnings("unused")
        Schedule schedule = (Schedule) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "");
    }

}
