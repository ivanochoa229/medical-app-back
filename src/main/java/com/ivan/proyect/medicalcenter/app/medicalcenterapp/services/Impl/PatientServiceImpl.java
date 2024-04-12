package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.PatientDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Address;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.HealthInsurence;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Patient;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.security.User;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.mapper.PatientMapperDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.PatientRequest;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.util.Role;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.AddressRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.InsurenceRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.PatientRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.RoleRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.UserRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.PatientService;

import jakarta.validation.Valid;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    InsurenceRepository insurenceRepository;

    @Autowired
    private RoleRepository roleRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PatientDto> findAll() {
        List<Patient> patients = (List<Patient>) patientRepository.findAll();
        return patients.stream().map(p -> PatientMapperDto.builder().setPatient(p).build())
                .collect(Collectors.toList());
    }

    @SuppressWarnings("null")
    @Transactional
    @Override
    public PatientDto save(@Valid Patient patient) {
        User user = patient.getUser();
        setRole(user);
        user.setPatient(patient);
        userRepository.save(user);
        addressRepository.save(patient.getAddress());
        return PatientMapperDto.builder().setPatient(patientRepository.save(patient)).build();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("null")
    @Override
    public Optional<PatientDto> findById(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            return Optional.of(PatientMapperDto.builder().setPatient(optionalPatient.get()).build());
        }
        return null;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("null")
    @Override
    public Optional<Patient> findByIdService(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            return optionalPatient;
        }
        return null;
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public Optional<PatientDto> update(Long id, PatientRequest patient) {

        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient newPatient = optionalPatient.orElseThrow();
            newPatient.setName(patient.getName());
            newPatient.setLastname(patient.getLastname());
            newPatient.setDni(patient.getDni());
            newPatient.setPhone(patient.getPhone());
            setAtributtes(id, patient, newPatient);
            return Optional.of(PatientMapperDto.builder().setPatient(patientRepository.save(newPatient)).build());
        }
        return Optional.empty();
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public Optional<Patient> delete(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        optionalPatient.ifPresent(patient -> {
            Optional<User> optionalUser = userRepository.findById(patient.getUser().getId());
            if (optionalUser.isPresent()) {
                patient.removeUser();
                patientRepository.delete(patient);
                userRepository.delete(optionalUser.get());
            }
        });
        return optionalPatient;
    }

    public User setRole(User user) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> optionaRoleUser = roleRepository.findByName("ROLE_PAT");
        optionaRoleUser.ifPresent(roles::add);
        user.setRoles(roles);
        user.setPassword(user.getPassword());
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    @SuppressWarnings("null")
    public void setAtributtes(Long id, PatientRequest patient, Patient newPatient) {
        Optional<HealthInsurence> optionalInsurence = insurenceRepository.findByname(patient.getHealthInsurence());
        if (optionalInsurence.isPresent()) {
            newPatient.removeInsurence();
            newPatient.addInsurence(optionalInsurence.get());
        }
        String[] addressAux = patient.getAddress().split(",");
        String[] add = addressAux[1].split(":");
        Optional<Address> optionalAddress = addressRepository.findByStateAndNumberAndStreet(addressAux[0],
                Integer.parseInt(add[1]), add[0]);
        if (!optionalAddress.isPresent()) {
            addressRepository.delete(newPatient.getAddress());
            Address newAddress = new Address(add[0], addressAux[0], Integer.parseInt(add[1]));
            newPatient.setAddress(newAddress);
        }
    }
}
