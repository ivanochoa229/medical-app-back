package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services;

import java.util.List;
import java.util.Optional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Diagnostic;

public interface DiagnosticService {
    List<Diagnostic> findAll();
    Diagnostic save(Diagnostic diagnostic);
    Optional<Diagnostic> delete(Long id);
    Optional<Diagnostic> findById(Long id);
    Optional<Diagnostic> update(Long id, Diagnostic diagnostic);
}
