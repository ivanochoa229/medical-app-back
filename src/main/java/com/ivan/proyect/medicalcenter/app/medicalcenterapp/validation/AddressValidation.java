package com.ivan.proyect.medicalcenter.app.medicalcenterapp.validation;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Address;


@Component
public class AddressValidation implements Validator{
     @Override
    public boolean supports(@NonNull Class<?> clazz) {
       return Address.class.isAssignableFrom(clazz);
    }


    @SuppressWarnings("null")
    @Override
    public void validate(@NonNull Object target,@NonNull Errors errors) {
        @SuppressWarnings("unused")
        Address address = (Address) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "");
    }
}
