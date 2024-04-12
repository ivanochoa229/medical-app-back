package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatientRequest {
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
    @NotNull
    private String healthInsurence;
    @NotNull
    private String address;
}
