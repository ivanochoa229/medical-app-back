package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.Impl;

import java.util.List;
import java.util.Optional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.exception.ObjectNotFoundException;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        schedule.setStatus(Status.ENABLED);
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
    @Transactional(readOnly = true)
    @Override
    public Page<Schedule> findAll(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    @Override
    public Schedule disableById(Long id) {
        Schedule scheduleDB = scheduleRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Schedule not found with id " +id));
        scheduleDB.setStatus(Status.DISABLED);
        return scheduleRepository.save(scheduleDB);
    }

}
