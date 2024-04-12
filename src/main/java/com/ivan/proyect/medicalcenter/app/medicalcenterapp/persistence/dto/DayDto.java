package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class DayDto {

    private Long id;

    private String start;

    private String end;

    private String dayName;
}
