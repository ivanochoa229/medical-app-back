package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Schedule;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.ScheduleRequest;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.ScheduleRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.ScheduleService;


@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Schedule> findAll() {
        return (List<Schedule>) scheduleRepository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    @Transactional
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @SuppressWarnings("null")
    @Override
    @Transactional
    public Optional<Schedule> update(Long id, ScheduleRequest schedule) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if(optionalSchedule.isPresent()){
            Schedule newSchedule = optionalSchedule.orElseThrow();
            newSchedule.setAvailable(schedule.getAvailable());
            return Optional.of(scheduleRepository.save(newSchedule));
        }
        return optionalSchedule;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional
    public Optional<Schedule> delete(Long id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        optionalSchedule.ifPresent(p-> {
            scheduleRepository.delete(p);
        });
        return optionalSchedule;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Optional<Schedule> findById(Long id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if(optionalSchedule.isPresent()){
            return optionalSchedule;
        }
        return null;
    }

}
