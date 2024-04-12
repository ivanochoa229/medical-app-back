package com.ivan.proyect.medicalcenter.app.medicalcenterapp.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Invoice;

@Component
public class InvoiceValidation implements Validator{

    @SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return Invoice.class.isAssignableFrom(clazz);
    }
    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
        @SuppressWarnings("unused")
        Invoice invoice = (Invoice) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "");
    }

}
