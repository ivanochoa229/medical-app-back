package com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
}
