package com.ivan.proyect.medicalcenter.app.medicalcenterapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.DoctorDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Doctor;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.DoctorRequest;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.DoctorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/center/doctors")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping
    List<DoctorDto> findAll(){
        return doctorService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Doctor> optionalDoctor = doctorService.findById(id);
        if (optionalDoctor.isPresent()) {
            return ResponseEntity.ok().body(optionalDoctor.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody Doctor doctor, BindingResult result){
        if(result.hasErrors()){
            validation(result);
        }
        return ResponseEntity.ok().body(doctorService.save(doctor));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid @RequestBody DoctorRequest doctor, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            validation(result);
        }
        Optional<DoctorDto> optionalDoctor = doctorService.update(id, doctor);
        if (optionalDoctor.isPresent()) {
            return ResponseEntity.ok().body(optionalDoctor.orElseThrow());
        }  else{
            return ResponseEntity.notFound().build();
        }
    }
    
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Doctor> optionalDoctor = doctorService.delete(id);
        if (optionalDoctor.isPresent()) {
            return ResponseEntity.ok().build();
        }  else{
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
