package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Diagnostic;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.DiagnosticRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.DiagnosticService;

@Service
public class DiagnosticServiceImpl implements DiagnosticService{

    @Autowired
    DiagnosticRepository diagnosticRepository;
    @Transactional(readOnly = true)
    @Override
    public List<Diagnostic> findAll() {
        return (List<Diagnostic> ) diagnosticRepository.findAll();
    }
    @Transactional
    @SuppressWarnings("null")
    @Override
    public Diagnostic save(Diagnostic diagnostic) {
        return diagnosticRepository.save(diagnostic);
    }
    @Transactional
    @SuppressWarnings("null")
    @Override
    public Optional<Diagnostic> delete(Long id) {
        Optional<Diagnostic> optionalDiagnostic = diagnosticRepository.findById(id);
        optionalDiagnostic.ifPresent( d -> {
            diagnosticRepository.delete(d);
        });
        return optionalDiagnostic;
    }
    @Transactional(readOnly = true)
    @SuppressWarnings("null")
    @Override
    public Optional<Diagnostic> findById(Long id) {
        Optional<Diagnostic> optionalDiagnostic = diagnosticRepository.findById(id);
        if(optionalDiagnostic.isPresent()){
            return Optional.of(optionalDiagnostic.orElseThrow());
        }
        return null;
    }
    @Transactional
    @SuppressWarnings("null")
    @Override
    public Optional<Diagnostic> update(Long id, Diagnostic diagnostic) {
        Optional<Diagnostic> optionalDiagnostic = diagnosticRepository.findById(id);
        if(optionalDiagnostic.isPresent()){
            Diagnostic newDiagnostic = optionalDiagnostic.get();
            newDiagnostic.setTreatmeant(diagnostic.getTreatmeant());
            newDiagnostic.setDescription(diagnostic.getDescription());
            return Optional.of(diagnosticRepository.save(newDiagnostic));
        }
        return optionalDiagnostic;
    }

}
