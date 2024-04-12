package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.ConsultoringRoom;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.RoomRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.RoomService;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired 
    RoomRepository roomRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ConsultoringRoom> findAll() {
       return (List<ConsultoringRoom>) roomRepository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    @Transactional
    public ConsultoringRoom save(ConsultoringRoom consultoringRoom) {
        return roomRepository.save(consultoringRoom);
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Optional<ConsultoringRoom> findById(Long id) {
        Optional<ConsultoringRoom> optionalConsultoring = roomRepository.findById(id);
        if(optionalConsultoring.isPresent()){
            return optionalConsultoring;
        }
        return null;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional
    public Optional<ConsultoringRoom> update(Long id, ConsultoringRoom consultoringRoom) {
        Optional<ConsultoringRoom> optionalConsultoring = roomRepository.findById(id);
        if(optionalConsultoring.isPresent()){
            ConsultoringRoom newConsultoringRoom = optionalConsultoring.orElseThrow();
            newConsultoringRoom.setName(consultoringRoom.getName());
            return Optional.of(roomRepository.save(newConsultoringRoom));    
        }
        return optionalConsultoring;
    }
    
    @SuppressWarnings("null")
    @Override
    @Transactional
    public Optional<ConsultoringRoom> delete(Long id) {
        Optional<ConsultoringRoom> optionalConsultoring = roomRepository.findById(id);
        optionalConsultoring.ifPresent(p -> {
            roomRepository.delete(p);
        });
        return optionalConsultoring;
    }

}
