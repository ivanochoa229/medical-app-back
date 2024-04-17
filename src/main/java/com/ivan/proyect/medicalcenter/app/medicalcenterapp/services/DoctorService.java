package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services;

import java.util.List;
import java.util.Optional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.DoctorDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Doctor;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.DoctorRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorService {
    List<DoctorDto> findAll();
    Page<DoctorDto> findAll(Pageable pageable);
    DoctorDto save(Doctor doctor);
    Optional<Doctor> delete(Long id);
    Optional<Doctor> findById(Long id);
    public Optional<DoctorDto> findByIdDto(Long id);
    Optional<DoctorDto> update(Long id, DoctorRequest doctor);
    DoctorDto disableById(Long id);
}
