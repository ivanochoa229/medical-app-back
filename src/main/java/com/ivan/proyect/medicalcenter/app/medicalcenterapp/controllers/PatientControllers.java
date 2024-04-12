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

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.PatientDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Patient;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.PatientRequest;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/center/patients")
public class PatientControllers {

    @Autowired
    public PatientService service;


    @GetMapping
    List<PatientDto> getUsers(){
       return service.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id){
       Optional<PatientDto> optinalPatient = service.findById(id);
       if(optinalPatient.isPresent()){
            return ResponseEntity.ok(optinalPatient.orElseThrow());
       }
       return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Patient patient, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        } 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(patient));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updatePatient(@Valid @RequestBody PatientRequest patient, BindingResult result,@PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<PatientDto> optinalPatient = service.update(id, patient);
        if(optinalPatient.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optinalPatient.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePatient(@PathVariable Long id){
        Optional<Patient>  optionalPatient = service.delete(id); 
        if(optionalPatient.isPresent()){
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
