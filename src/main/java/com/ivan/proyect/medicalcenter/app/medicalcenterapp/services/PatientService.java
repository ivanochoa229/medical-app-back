package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services;

import java.util.List;
import java.util.Optional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.PatientDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Patient;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.PatientRequest;



public interface PatientService {
    List<PatientDto> findAll();
    PatientDto save(Patient patient);
    Optional<PatientDto> findById(Long id);
    Optional<Patient> findByIdService(Long id);
    Optional<PatientDto> update(Long id, PatientRequest patient);
    Optional<Patient> delete(Long id);
}
