package com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
