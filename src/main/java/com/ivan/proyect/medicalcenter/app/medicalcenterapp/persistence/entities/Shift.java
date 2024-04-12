package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shifts")
@Getter @Setter
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String day;
    private String hour;
    private Boolean emmergency;
    private String symptoms;

    @OneToOne
    private Doctor doctor;
    
    @OneToOne
    private Patient patient;

    @OneToOne
    private Diagnostic diagnostic;

    @OneToOne
    private Invoice invoice;

    public Shift(){
        emmergency = false;
    }

    public void addDoctor(Doctor doctor){
        this.setDoctor(doctor);
    }

    public void removeDoctor(){
        this.setPatient(null);
    }

    public void addPatient(Patient patient){
        this.setPatient(patient);
    }

    public void removePatient(){
        this.setPatient(null);
    }

    public void addInvoice(Invoice invoice){
        this.setInvoice(invoice);
    }

    public void removeInvoice(){
        this.setInvoice(null);
    }


    
}
