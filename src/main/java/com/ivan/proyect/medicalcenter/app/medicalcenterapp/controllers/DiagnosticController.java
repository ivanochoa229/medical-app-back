package com.ivan.proyect.medicalcenter.app.medicalcenterapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Diagnostic;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.DiagnosticService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/center/diagnostics")
public class DiagnosticController {
    @Autowired
    DiagnosticService diagnosticService;

    @GetMapping
    List<Diagnostic> findAll(){
        return diagnosticService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Diagnostic> optionalDiagnostic = diagnosticService.findById(id);
        if(optionalDiagnostic.isPresent()){
            return ResponseEntity.ok().body(optionalDiagnostic.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody Diagnostic diagnostic, BindingResult result){
        if(result.hasErrors()){
            validation(result);
        }
        return ResponseEntity.ok(diagnosticService.save(diagnostic));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid @RequestBody Diagnostic diagnostic, BindingResult result, @PathVariable Long id){
        if ((result.hasErrors())) {
            validation(result);
        }
        Optional<Diagnostic> optionalDiagnostic = diagnosticService.update(id, diagnostic);
        if (optionalDiagnostic.isPresent()) {
            return ResponseEntity.ok().body(optionalDiagnostic.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Diagnostic> optionalDiagnostic = diagnosticService.delete(id);
        if (optionalDiagnostic.isPresent()) {
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "The field " + err.getField() + " "+ err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
