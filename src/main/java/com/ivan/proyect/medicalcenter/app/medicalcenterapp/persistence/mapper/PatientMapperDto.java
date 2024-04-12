package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.mapper;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.PatientDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Address;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Patient;

public class PatientMapperDto {
    private Patient patient;
    private PatientMapperDto(){

    }
    public static PatientMapperDto builder(){
        return new PatientMapperDto();
    }
    public PatientMapperDto setPatient(Patient patient){
        this.patient = patient;
        return this;
    }
    public PatientDto build(){
        if(patient == null){
            throw new RuntimeException("You must pass the entity Patient");
        }else{
            Address ad = patient.getAddress();
            String address = ad.getState().concat(",").concat(ad.getStreet()).concat(":").concat(String.valueOf(ad.getNumber()));
            return new PatientDto(patient.getId(), patient.getName(), patient.getLastname(), patient.getDni(), patient.getPhone(), patient.getHealthInsurence().getName(), address, patient.getUser().getUsername(), patient.getUser().getEmail());
        }
    }
}
