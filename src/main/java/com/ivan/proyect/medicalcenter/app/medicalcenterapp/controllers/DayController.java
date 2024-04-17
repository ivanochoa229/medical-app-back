package com.ivan.proyect.medicalcenter.app.medicalcenterapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Day;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.DayRequest;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.DayService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/center/days")
public class DayController {

    @Autowired
    DayService dayService;

    @GetMapping
    public ResponseEntity<Page<Day>> findAll(Pageable pageable){
        Page<Day> dayPage = dayService.findAll(pageable);
        if(dayPage.hasContent()){
            return ResponseEntity.ok(dayPage);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Day> optionalDay = dayService.findById(id);
        if(optionalDay.isPresent()){
            return ResponseEntity.ok().body(optionalDay.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/doctor/{id}")
    List<Day> findByDoctorId(@PathVariable Long id){
        return (List<Day>) dayService.findAllByDoctorId(id);
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody Day day, BindingResult result){
        if(result.hasErrors()){
            validation(result);
        }
        return ResponseEntity.ok().body(dayService.save(day));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid @RequestBody DayRequest day, BindingResult result, @PathVariable Long id){
        Optional<Day> optionalDay = dayService.update(id, day);
        if(optionalDay.isPresent()){
            return ResponseEntity.ok().body(optionalDay.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}/disabled")
    ResponseEntity<Day> disableById(@PathVariable Long id){
        Day day = dayService.disableById(id);
        return ResponseEntity.status(HttpStatus.OK).body(day);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Day> optionalDay = dayService.delete(id);
        if(optionalDay.isPresent()){
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
