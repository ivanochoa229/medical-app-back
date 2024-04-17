package com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Day;

public interface DayRepository extends JpaRepository<Day, Long>{
    List<Day> findByDoctorId(Long id);
}
