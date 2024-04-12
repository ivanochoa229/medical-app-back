package com.ivan.proyect.medicalcenter.app.medicalcenterapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.UserDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.security.User;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.UserRequest;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/center/users")
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping
    List<UserDto> list(){
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        } 
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result){
        user.setAdmin(false);
        return create(user,result);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid @RequestBody UserRequest user, BindingResult result, @PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        } 
        Optional<UserDto> optionalUser = userService.update(id, user);
        if(optionalUser.isPresent()){
            return ResponseEntity.ok().body(optionalUser.get());
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
