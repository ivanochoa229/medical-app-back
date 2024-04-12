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

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.ConsultoringRoom;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/center/rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping
    List<ConsultoringRoom> findAll(){
        return roomService.findAll();
    }

    @PostMapping
    ResponseEntity<?> save(@Valid  @RequestBody ConsultoringRoom consultoringRoom, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        } 
        return ResponseEntity.ok(roomService.save(consultoringRoom));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid @RequestBody ConsultoringRoom consultoringRoom, BindingResult result,@PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        } 
        Optional<ConsultoringRoom> optionalRoom = roomService.update(id, consultoringRoom);
        if(optionalRoom.isPresent()){
            return ResponseEntity.ok().body(optionalRoom.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(Long id){
        Optional<ConsultoringRoom> optionalRoom = roomService.delete(id);
        if(optionalRoom.isPresent()){
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
