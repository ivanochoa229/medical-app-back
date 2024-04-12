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

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Shift;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.ShiftService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/center/shifts")
public class ShiftController {
    @Autowired
    ShiftService shiftService;

    @GetMapping
    List<Shift> findAll(){
        return shiftService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Shift> optionalShift = shiftService.findById(id);
        if (optionalShift.isPresent()) {
            return ResponseEntity.ok().body(optionalShift.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody Shift shift, BindingResult result){
        if(result.hasErrors()){
            validation(result);
        }
        shiftService.save(shift);
        return ResponseEntity.ok(shiftService.findDTO(shift));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid @RequestBody Shift shift, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            validation(result);
        }
        Optional<Shift> optionalShift = shiftService.update(id, shift);
        if (optionalShift.isPresent()) {
            return ResponseEntity.ok().body(optionalShift.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delResponseEntity(@PathVariable Long id){
        Optional<Shift> optionalShift = shiftService.delete(id);  
        if (optionalShift.isPresent()) {
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
