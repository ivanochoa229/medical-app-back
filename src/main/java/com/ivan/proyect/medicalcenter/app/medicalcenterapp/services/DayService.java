package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services;

import java.util.List;
import java.util.Optional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Day;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.DayRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DayService {
    List<Day> findAll();
    List<Day> findAllByDoctorId(Long id);
    Page<Day> findAll(Pageable pageable);
    Day save(Day day);
    Optional<Day> findById(Long id);
    Optional<Day> update(Long id, DayRequest day);
    Optional<Day> delete(Long id);
    Day disableById(Long id);
}
