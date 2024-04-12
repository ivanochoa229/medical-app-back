package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services;

import java.util.List;
import java.util.Optional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.HealthInsurence;


public interface InsurenceService {
    List<HealthInsurence> findAll();
    HealthInsurence save(HealthInsurence healthInsurence);
    Optional<HealthInsurence> findById(Long id);
    Optional<HealthInsurence> delete(Long id);
}
