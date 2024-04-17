package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.util.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@MappedSuperclass

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
    @Enumerated(EnumType.STRING)
    private Status status;

    public Person() {
    }

    public Person(String name, String lastname, String dni) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    
}
