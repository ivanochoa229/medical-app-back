package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class DoctorDto {
    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private String phone;
    private String username;
    private String email;
    private String room;
    private String registration;
    private String especiality;
    private Boolean emmergency;
    private boolean admin;
}
