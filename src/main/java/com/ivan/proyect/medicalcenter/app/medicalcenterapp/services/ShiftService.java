package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services;

import java.util.List;
import java.util.Optional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.ShiftDTO;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Shift;

public interface ShiftService {
    List<Shift> findAll();
    Shift save(Shift shift);
    Optional<Shift> findById(Long id);
    Optional<Shift> delete(Long id);
    Optional<Shift> update(Long id, Shift shift);
    public ShiftDTO findDTO(Shift shift);
}
