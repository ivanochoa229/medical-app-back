package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.HealthInsurence;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.InsurenceRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.InsurenceService;





@Service
public class InsurenceServiceImpl implements InsurenceService{

    @Autowired
    InsurenceRepository insurenceRepository;    

    @Override
    @Transactional(readOnly = true)
    public List<HealthInsurence> findAll() {
        return (List<HealthInsurence>) insurenceRepository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    @Transactional
    public HealthInsurence save(HealthInsurence healthInsurence) {
        return insurenceRepository.save(healthInsurence);
    }
    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Optional<HealthInsurence> findById(Long id) {
        Optional<HealthInsurence> optionalInsurence = insurenceRepository.findById(id);
        if(optionalInsurence.isPresent()){
            return optionalInsurence;
        }
        return null;
    }
    @Transactional
    @SuppressWarnings("null")
    @Override
    public Optional<HealthInsurence> delete(Long id) {
        Optional<HealthInsurence> optionalInsurence = insurenceRepository.findById(id);
        optionalInsurence.ifPresent(p -> {
            insurenceRepository.delete(p);
        });
        return optionalInsurence;
    }

}
