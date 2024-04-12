package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private boolean admin;
    private boolean doctor;

    
}
