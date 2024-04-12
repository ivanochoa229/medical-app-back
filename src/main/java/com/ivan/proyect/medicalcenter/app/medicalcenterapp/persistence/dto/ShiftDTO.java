package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ShiftDTO {
    private String patientName;
    private String doctorName;
    private String roomName;
    private String hour;
    private String day;
}
