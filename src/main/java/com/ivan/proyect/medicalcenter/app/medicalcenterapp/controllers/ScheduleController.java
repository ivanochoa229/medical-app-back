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

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Schedule;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.ScheduleRequest;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.ScheduleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/center/schedules")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @GetMapping
    List<Schedule> findAll(){
        return scheduleService.findAll();
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody Schedule schedule, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.ok().body(scheduleService.save(schedule));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid @RequestBody ScheduleRequest schedule, BindingResult result,@PathVariable Long id){
        if(result.hasErrors()){
            return validation(result);
        }
        Optional<Schedule> optionalSchedule = scheduleService.update(id, schedule);
        if(optionalSchedule.isPresent()){
            return ResponseEntity.ok().body(optionalSchedule.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Schedule> optionalSchedule = scheduleService.delete(id);
        if(optionalSchedule.isPresent()){
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
