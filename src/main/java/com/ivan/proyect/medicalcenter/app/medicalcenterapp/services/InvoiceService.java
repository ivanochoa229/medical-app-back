package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services;

import java.util.List;
import java.util.Optional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Invoice;

public interface InvoiceService {
    List<Invoice> findAll();
    Invoice save(Invoice invoice);
    Optional<Invoice> delete(Long id);
    Optional<Invoice> findById(Long id);
    Optional<Invoice> update(Long id, Invoice invoice);
}


