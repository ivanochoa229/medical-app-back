package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Invoice;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.InvoiceRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{
    @Autowired
    InvoiceRepository invoiceRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Invoice> findAll() {
        return (List<Invoice>) invoiceRepository.findAll();
    }
    @Transactional
    @SuppressWarnings("null")
    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
    @Transactional
    @SuppressWarnings("null")
    @Override
    public Optional<Invoice> delete(Long id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        optionalInvoice.ifPresent(invoice -> {
            invoiceRepository.delete(invoice);
        });
        return optionalInvoice;
    }
    @Transactional(readOnly = true)
    @SuppressWarnings("null")
    @Override
    public Optional<Invoice> findById(Long id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isPresent()) {
            return optionalInvoice;
        }
        return null;
    }
    @Transactional
    @SuppressWarnings("null")
    @Override
    public Optional<Invoice> update(Long id, Invoice invoice) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isPresent()) {
            Invoice newInvoice = optionalInvoice.get();
            newInvoice.setDate(invoice.getDate());
            newInvoice.setTotal(invoice.getTotal());
            return Optional.of(invoiceRepository.save(newInvoice));
        }
        return optionalInvoice;
    }

}
