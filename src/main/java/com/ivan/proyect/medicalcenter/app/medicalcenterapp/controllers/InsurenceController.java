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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.HealthInsurence;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.InsurenceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/center/insurences")
public class InsurenceController {
    @Autowired
    InsurenceService insurenceService;

    @GetMapping
    public List<HealthInsurence> findall(){
        return insurenceService.findAll();
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody HealthInsurence insurence, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        } 
        return ResponseEntity.status(HttpStatus.CREATED).body(insurenceService.save(insurence));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@Valid @PathVariable Long id){
        Optional<HealthInsurence> optionalInsurence = insurenceService.delete(id);
        if(optionalInsurence.isPresent()){
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
