package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "schedules")
@Getter @Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean available;
    @NotEmpty
    @Pattern(regexp = "\\d{1,2}:\\d{1,2}")
    private String hour;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "day_id")
    private Day day;

    public Schedule(){
        //days = new HashSet<>();
        available = true;
    }

    public Schedule(String hour) {
        this();
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "Schedule [available=" + available + ", hour=" + hour + "]";
    }

    
}
