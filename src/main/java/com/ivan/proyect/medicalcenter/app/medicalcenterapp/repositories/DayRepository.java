package com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Day;

public interface DayRepository extends CrudRepository<Day, Long>{
    List<Day> findByDoctorId(Long id);
}
