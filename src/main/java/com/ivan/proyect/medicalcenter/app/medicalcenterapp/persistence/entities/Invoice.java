package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invoices")
@Getter @Setter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private Integer total;

    public Invoice(){
        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();
        LocalDateTime fecha = LocalDateTime.of(hoy, ahora);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm");
        String fechaFormateada = fecha.format(formatter);
        date = fechaFormateada;
    }

    public Invoice(String date, Integer total) {
        this();
        this.date = date;
        this.total = total;
    }
}
