package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {
    @NotBlank
    @Size(min=4, max = 12)
    private String username;
    @NotEmpty
    @Email
    private String email;
    private boolean admin;
    private boolean doctor;
}
