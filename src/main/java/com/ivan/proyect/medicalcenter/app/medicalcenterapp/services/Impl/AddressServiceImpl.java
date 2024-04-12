package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Address;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.AddressRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Address> findAll() {
        return (List<Address>) addressRepository.findAll();
    }
    @SuppressWarnings("null")
    @Transactional
    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }
    @SuppressWarnings("null")
    @Transactional(readOnly = true)
    @Override
    public Optional<Address> findById(Long id) {
        Optional<Address> optionalAddres = addressRepository.findById(id);
        if(optionalAddres.isPresent()){
            return optionalAddres;
        }
        return null;
    }
    @SuppressWarnings("null")
    @Transactional
    @Override
    public Optional<Address> update(Long id, Address address) {
        Optional<Address> optionalAddres = addressRepository.findById(id);
        if(optionalAddres.isPresent()){
            Address newAddres = optionalAddres.orElseThrow();
            newAddres.setNumber(address.getNumber());
            newAddres.setState(address.getState());
            newAddres.setStreet(address.getStreet());
            return Optional.of(addressRepository.save(newAddres));
        }
        return optionalAddres;
    }
    @SuppressWarnings("null")
    @Transactional
    @Override
    public Optional<Address> delete(Long id) {
        Optional<Address> optionalAddres = addressRepository.findById(id);
        optionalAddres.ifPresent(addres ->{
            addressRepository.delete(addres);
        });
        return optionalAddres;
    }

}
