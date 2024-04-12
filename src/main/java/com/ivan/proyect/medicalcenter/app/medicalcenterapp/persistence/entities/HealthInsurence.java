package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "insurences")
@Getter @Setter
public class HealthInsurence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotEmpty
    @Size(min=5, max = 15)
    private String name;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "healthInsurence")
    private Set<Patient> patient;

    public HealthInsurence() {
        patient = new HashSet<>();
    }

    public HealthInsurence(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addPatient(Patient patient){
        this.addPatient(patient);
        patient.setHealthInsurence(this);
    }

    public void removePatient(Patient patient){
        patient.setHealthInsurence(null);
        this.addPatient(null);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((patient == null) ? 0 : patient.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HealthInsurence other = (HealthInsurence) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (patient == null) {
            if (other.patient != null)
                return false;
        } else if (!patient.equals(other.patient))
            return false;
        return true;
    }
    
    
}
