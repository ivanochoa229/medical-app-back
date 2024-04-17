package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
// import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rooms")
@Getter @Setter
public class ConsultoringRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 1)
    @Column(unique = true)
    private String name;

    @OneToOne(mappedBy = "consultoringRoom")
    @JoinColumn(name = "id_doctor")
    @JsonIgnore
    private Doctor doctor;
}
