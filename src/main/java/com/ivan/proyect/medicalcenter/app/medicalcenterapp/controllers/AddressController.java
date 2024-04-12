package com.ivan.proyect.medicalcenter.app.medicalcenterapp.controllers;

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

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Address;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.AddressService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/center/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    List<Address> findAll(){
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Address> optionalAddres = addressService.findById(id);
       if(optionalAddres.isPresent()){
        return ResponseEntity.ok().body(optionalAddres.orElseThrow());
       }
       return null;
    }

    @PostMapping
    ResponseEntity<?> save(@Valid  @RequestBody Address address, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        } 
        return ResponseEntity.ok(addressService.save(address));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid @RequestBody Address address, BindingResult result,@PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        } 
        Optional<Address> addressOptional = addressService.update(id, address);
        if(addressOptional.isPresent()){
            return ResponseEntity.ok(addressOptional.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Address> addressOptional = addressService.delete(id);
        if(addressOptional.isPresent()){
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
