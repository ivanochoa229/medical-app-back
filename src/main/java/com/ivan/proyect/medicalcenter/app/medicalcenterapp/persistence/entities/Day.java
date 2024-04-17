package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.util.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "days")
@Getter
@Setter
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Pattern(regexp = "\\d{1,2}:\\d{1,2}")
    private String start;
    @NotEmpty
    @Pattern(regexp = "\\d{1,2}:\\d{1,2}")
    private String end;

    @NotEmpty
    @Column(name = "day_name")
    private String dayName;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private Doctor doctor;

    @OneToMany(mappedBy = "day", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Set<Schedule> schedules = new HashSet<>();

    private Status status;

    public Day() {
    }

    public Day(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public Day(@NotEmpty @Pattern(regexp = "\\d{1,2}:\\d{1,2}") String start,
            @NotEmpty @Pattern(regexp = "\\d{1,2}:\\d{1,2}") String end, @NotEmpty String dayName) {
        this.start = start;
        this.end = end;
        this.dayName = dayName;
    }

    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
        schedule.setDay(this);
    }

    public void removeSchedule(Schedule schedule) {
        schedule.setDay(null);
        this.schedules.remove(schedule);
    }

    public String setDay() {
        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        return dayName;
    }

    @Override
    public String toString() {
        return "Day [id=" + id + ", start=" + start + ", end=" + end + ", dayName=" + dayName + "]";
    }
}
