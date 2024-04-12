package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services;

import java.util.List;
import java.util.Optional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.ConsultoringRoom;

public interface RoomService {
    List<ConsultoringRoom> findAll();
    ConsultoringRoom save(ConsultoringRoom consultoringRoom);
    Optional<ConsultoringRoom> findById(Long id);
    Optional<ConsultoringRoom> update(Long id, ConsultoringRoom consultoringRoom);
    Optional<ConsultoringRoom> delete(Long id);
}
