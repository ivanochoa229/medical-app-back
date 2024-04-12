package com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Address;

public interface AddressRepository extends CrudRepository<Address , Long>{
    Optional<Address> findByStateAndNumberAndStreet(String state, Integer number, String street);
}
