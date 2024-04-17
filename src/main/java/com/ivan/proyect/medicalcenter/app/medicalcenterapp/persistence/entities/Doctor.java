package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities;

import java.util.HashSet;
import java.util.Set;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.security.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter @Setter
public class Doctor extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String registration;
    @NotEmpty
    private String especiality;
    private Boolean emmergency;

    @Transient
   
    @Pattern(regexp = "\\d{1,2}:\\d{1,2}")
    private String start;
    @Transient
    
    @Pattern(regexp = "\\d{1,2}:\\d{1,2}")
    private String end;

    @OneToOne
    @JoinColumn(name = "room_id")
    private ConsultoringRoom consultoringRoom;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<Day> days;

    @OneToOne
    private User user;

    public Doctor() {
        days = new HashSet<>();
        emmergency = false;
    }
       
    public Doctor(String name, String lastname, String dni, String registration, String especiality) {
        super(name, lastname, dni);
        this.emmergency = false;
        this.registration = registration;
        this.especiality = especiality;
    }

    public void addDays(Day day){
        this.days.add(day);
        day.setDoctor(this);
    }

    public void removeDays(Day day){
        day.setDoctor(null);
        this.days.remove(day);
    }

    public void addUser(User user){
        this.setUser(user);
    }

    public void removeUser(){
        this.setUser(null);
    }


    public void addConsultoringRoom(ConsultoringRoom consultoringRoom){
        this.setConsultoringRoom(consultoringRoom);
        consultoringRoom.setDoctor(this);
    }

    public void removeConsultoringRoom(){
        consultoringRoom.setDoctor(null);
        this.setConsultoringRoom(null);
    }

    @Override
    public String toString() {
        return "Doctor [id=" + id + ", registration=" + registration + ", especiality=" + especiality + ", emmergency="
                + emmergency + "]";
    }
  
}
