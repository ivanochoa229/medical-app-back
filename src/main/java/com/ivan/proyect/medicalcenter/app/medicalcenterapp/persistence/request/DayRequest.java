package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class DayRequest {
    @NotEmpty
    @Pattern(regexp = "\\d{1,2}:\\d{1,2}")
    private String start;
    @NotEmpty
    @Pattern(regexp = "\\d{1,2}:\\d{1,2}")
    private String end;
}
