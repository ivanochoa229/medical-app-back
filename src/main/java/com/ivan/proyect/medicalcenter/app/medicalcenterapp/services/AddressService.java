package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services;

import java.util.List;
import java.util.Optional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Address;

public interface AddressService {
    List<Address> findAll();
    Address save(Address address);
    Optional<Address> findById(Long id);
    Optional<Address> update(Long id, Address address);
    Optional<Address> delete(Long id);
}

