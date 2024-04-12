package com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>{

}
