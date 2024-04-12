package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter @Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min=5, max = 15)
    private String street;

    @NotEmpty
    @Size(min=5, max = 15)
    private String state;
    
    @NotNull
    @Min(value = 0)
    private Integer number;

    // @OneToOne
    // @JoinColumn(name = "id_patient")
    // @JsonIgnore
    // private Patient patient;

    public Address() {

    }
 
    

    public Address(String street, String state, Integer number) {
        this.street = street;
        this.state = state;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address [street=" + street + ", state=" + state + ", number=" + number ;
    }

    
}
