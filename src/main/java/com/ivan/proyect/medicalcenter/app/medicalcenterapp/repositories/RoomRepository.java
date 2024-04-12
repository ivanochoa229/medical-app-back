package com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.ConsultoringRoom;

public interface RoomRepository extends CrudRepository<ConsultoringRoom, Long>{
    Optional<ConsultoringRoom> findByName(String name);
}
