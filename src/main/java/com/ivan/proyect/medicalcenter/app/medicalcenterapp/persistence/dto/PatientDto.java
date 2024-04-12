package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PatientDto {
    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private String phone;
    private String healthInsurence;
    private String address;
    private String username;
    private String email;
}
