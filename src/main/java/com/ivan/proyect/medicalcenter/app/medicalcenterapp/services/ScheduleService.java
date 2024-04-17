package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services;

import java.util.List;
import java.util.Optional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Schedule;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.ScheduleRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {
    List<Schedule> findAll();
    Schedule save(Schedule schedule);
    Optional<Schedule> update(Long id, ScheduleRequest schedule);
    Optional<Schedule> delete(Long id);
    Optional<Schedule> findById(Long id);
    Page<Schedule> findAll(Pageable pageable);
    Schedule disableById(Long id);
}
