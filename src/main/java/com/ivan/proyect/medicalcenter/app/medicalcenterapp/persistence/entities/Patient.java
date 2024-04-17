package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities;



import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.security.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "patients")
@Getter @Setter
public class Patient extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;
   
    @ManyToOne
    @JoinColumn(name = "insurence_id")
    private HealthInsurence healthInsurence;

    @OneToOne
    private User user;

    public Patient(){}

    public Patient(String name, String lastname, String dni) {
        super(name, lastname, dni);
    }

    public void setAddress(Address address){
        this.address = address;
    }

    public void removeAddress(){
        this.address = null;
    }

    public void removeInsurence(){
        this.healthInsurence = null;
    }
    public void addInsurence(HealthInsurence healthInsurence){
        this.healthInsurence = healthInsurence;
    }

    public void addUser(User user){
        this.setUser(user);
       
    }

    public void removeUser(){
        
        this.setUser(null);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Patient other = (Patient) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", address=" + address + ", healthInsurence=" + healthInsurence + "]";
    }

    
    
}
