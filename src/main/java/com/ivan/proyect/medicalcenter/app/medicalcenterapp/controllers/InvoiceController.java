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

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Invoice;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.InvoiceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/center/invoices")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    @GetMapping
    List<Invoice> findAll(){
        return invoiceService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Invoice> optionalInvoice = invoiceService.findById(id);
        if (optionalInvoice.isPresent()) {
            return ResponseEntity.ok().body(optionalInvoice.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody Invoice invoice, BindingResult result){
        if (result.hasErrors()) {
            validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.save(invoice));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid @RequestBody Invoice invoice, BindingResult result, @PathVariable Long id){
        Optional<Invoice> optionalInvoice = invoiceService.update(id, invoice);
        if (optionalInvoice.isPresent()) {
            return ResponseEntity.ok().body(optionalInvoice.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Invoice> optionalInvoice = invoiceService.delete(id);
        if (optionalInvoice.isPresent()) {
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
