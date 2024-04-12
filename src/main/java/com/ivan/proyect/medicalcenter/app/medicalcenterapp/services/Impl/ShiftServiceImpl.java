package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.ShiftDTO;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Doctor;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Patient;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Shift;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.ShiftRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.DoctorService;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.PatientService;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.ShiftService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ShiftServiceImpl implements ShiftService{

    @Autowired
    ShiftRepository shiftRepository;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @Transactional(readOnly = true)
    @Override
    public List<Shift> findAll() {
        return (List<Shift>) shiftRepository.findAll();
    }

    @Transactional
    @Override
    public Shift save(Shift shift) {
        if (shift.getDoctor() != null && shift.getDoctor().getId() != null) {
        // Buscar el doctor existente por su ID
        Doctor existingDoctor = doctorService.findById(shift.getDoctor().getId())
        .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + shift.getDoctor().getId()));
        // Asignar el doctor existente al turno
        shift.setDoctor(existingDoctor);
        }
        if(shift.getPatient() != null && shift.getDoctor().getId() != null){
            Patient existingPatient = patientService.findByIdService(shift.getDoctor().getId())
            .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + shift.getDoctor().getId()));
            shift.setPatient(existingPatient);
        }
        return shiftRepository.save(shift);
    }
    @Transactional(readOnly = true)
    @SuppressWarnings("null")
    @Override
    public Optional<Shift> findById(Long id) {
        Optional<Shift> optionalShift = shiftRepository.findById(id);
        if (optionalShift.isPresent()) {
            return Optional.of(optionalShift.get());
        }
        return optionalShift;
    }
    @Transactional
    @SuppressWarnings("null")
    @Override
    public Optional<Shift> delete(Long id) {
        Optional<Shift> optionalShift = shiftRepository.findById(id);
        optionalShift.ifPresent(s -> {
            shiftRepository.delete(s);
        });
        return optionalShift;
    }
    @Transactional
    @SuppressWarnings("null")
    @Override
    public Optional<Shift> update(Long id, Shift shift) {
        System.out.println(shift);
        Optional<Shift> optionalShift = shiftRepository.findById(id);
        if (optionalShift.isPresent()) {
            Shift newShift = optionalShift.orElseThrow();
            newShift.setDay(shift.getDay());
            newShift.setHour(shift.getHour());
            newShift.setSymptoms(shift.getSymptoms());
            Optional<Doctor> optionalDoctor = doctorService.findById(shift.getDoctor().getId());
            if (optionalDoctor.isPresent()) {
                newShift.addDoctor(optionalDoctor.get());
            }            
            Optional<Patient> optionalPatient = patientService.findByIdService(shift.getPatient().getId());
            if (optionalPatient.isPresent()) {
                newShift.addPatient(optionalPatient.get());
            }
            return Optional.of(shiftRepository.save(newShift));
        }
        return optionalShift;
    }

    @Override
    public ShiftDTO findDTO(Shift shift) {
        ShiftDTO shiftDTO = new ShiftDTO();
        shiftDTO.setPatientName(shift.getPatient().getLastname()+", " +shift.getPatient().getName());
        shiftDTO.setDoctorName(shift.getDoctor().getLastname() + ", " + shift.getDoctor().getName());
        shiftDTO.setRoomName(shift.getDoctor().getConsultoringRoom().getName());
        shiftDTO.setHour(shift.getHour());
        shiftDTO.setDay(shift.getDay());
        return shiftDTO;    
    }
    

}
