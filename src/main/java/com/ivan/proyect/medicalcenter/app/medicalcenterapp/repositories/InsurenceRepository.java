package com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.HealthInsurence;

public interface InsurenceRepository extends CrudRepository<HealthInsurence, Long>{
    boolean existsByName(String name);
    Optional<HealthInsurence> findByname(String name);
}
