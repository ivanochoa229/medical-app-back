package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.mapper;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.DayDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Day;

public class DayMapperDto {
    private Day day;
    private DayMapperDto(){}
    public static DayMapperDto builder(){
        return new DayMapperDto();
    }
    public DayMapperDto setDay(Day day){
        this.day = day;
        return this;
    }
    public DayDto build(){
        if (day == null) {
            throw new RuntimeException("You must pass the entity Day");
        }else{
            return new DayDto(day.getId(), day.getStart(), day.getEnd(), day.getEnd());
        }
    }
}
