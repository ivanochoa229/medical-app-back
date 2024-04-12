package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.mapper;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.DoctorDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Doctor;

public class DoctorMapperDto {
    private Doctor doctor;

    private DoctorMapperDto() {
    }

    public static DoctorMapperDto builder() {
        return new DoctorMapperDto();
    }

    public DoctorMapperDto setDoctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    public DoctorDto build() {
        if (doctor == null) {
            throw new RuntimeException("You must pass the entity Doctor");
        } else {

            return new DoctorDto(doctor.getId(), doctor.getName(), doctor.getLastname(), doctor.getDni(),
                    doctor.getPhone(), doctor.getUser().getUsername(), doctor.getUser().getEmail(),
                    doctor.getConsultoringRoom().getName(), doctor.getRegistration(), doctor.getEspeciality(), doctor.getUser().isAdmin(), doctor.getEmmergency());
        }
    }
}
