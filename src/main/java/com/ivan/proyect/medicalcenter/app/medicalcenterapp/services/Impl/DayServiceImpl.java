package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.Impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Day;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Doctor;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Schedule;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.DayRequest;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.DayRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.DoctorRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.ScheduleRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.DayService;

@Service
public class DayServiceImpl implements DayService{

    @Autowired 
    DayRepository dayRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Day> findAll() {
        return (List<Day>) dayRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Day> findAllByDoctorId(Long id) {
        return (List<Day>) dayRepository.findByDoctorId(id);
    }

  
    @SuppressWarnings("null")
    @Override
    @Transactional
    public Day save(Day day) {
        determinate(day);
        return dayRepository.save(day);
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Optional<Day> findById(Long id) {
        Optional<Day> optionalDay = dayRepository.findById(id);
        if(optionalDay.isPresent()){
            return optionalDay;
        }
        return null;
    }
    
   

    @SuppressWarnings("null")
    @Override
    @Transactional
    public Optional<Day> update(Long id, DayRequest day) {
        Optional<Day> optionalDay = dayRepository.findById(id);
        if (optionalDay.isPresent()) {
            Day newDay = optionalDay.get(); // Using get() as optionalDay.isPresent() is already checked
            if (newDay.getStart() != day.getStart() || newDay.getEnd() != day.getEnd()) {
                //newDay.getSchedules().forEach(scheduleRepository::delete);
                //newDay.setSchedules(null);
                newDay.setEnd(day.getEnd());
                newDay.setStart(day.getStart());
                determinate(newDay);
            }
            return Optional.of(dayRepository.save(newDay)); // Corrected return statement
        }
        return optionalDay; // Return the original optional if day is not present
    }

    @SuppressWarnings("null")
    @Override
    @Transactional
    public Optional<Day> delete(Long id) {
        Optional<Day> optionalDay = dayRepository.findById(id);
        if(optionalDay.isPresent()){
           optionalDay.
           orElseThrow()
           .getSchedules()
           .stream()
           .forEach(p -> {
            scheduleRepository.delete(p);
           });
           Doctor doc = optionalDay.get().getDoctor();
           doc.removeDays(optionalDay.get());
           dayRepository.delete(optionalDay.orElseThrow());
        }
        return optionalDay;
    }


    @Transactional
    public Day determinate(Day day) {
        String[] auxStart = day.getStart().split(":");
        Integer start = Integer.parseInt(auxStart[0]);
        String[] auxEnd = day.getEnd().split(":");
        Integer end = (Integer.parseInt(auxEnd[0]) - Integer.parseInt(auxStart[0]))*2 + 1;
        Boolean flag = auxEnd[1].equals("00") ? true : false;
        //day.setSchedules(new HashSet<>());
        for(int i = 0; i < end; i++){
            Schedule newSchedule = new Schedule();
            if(flag){
                newSchedule.setHour(start + ":" + "00");
                day.addSchedule(newSchedule);
                scheduleRepository.save(newSchedule);
                flag = false;
            }else{
                newSchedule.setHour(start + ":" + "30");
                day.addSchedule(newSchedule);
                scheduleRepository.save(newSchedule);
                flag = true;
                start++;
            }
        }
        return day;
    }

}
