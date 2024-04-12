package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@Getter @Setter
public class Person {   

    @NotBlank
    @Size(min=3, max = 15)
    private String name;
    @NotEmpty
    @Size(min=2, max = 15)
    private String lastname;
    @NotBlank
    @Size(min=7, max = 8)
    private String dni;
    @NotBlank
    @Size(min=10, max = 10)
    private String phone;
    


    public Person() {
    }

    public Person(String name, String lastname, String dni) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;

    }
}
